package com.cloud.movie.controller;

import com.cloud.common.base.result.R;
import com.cloud.common.base.util.DrawImageUtil;
import com.cloud.common.base.util.HeaUtil;
import com.cloud.movie.dto.LoginDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;

@RestController
@RequestMapping("/auth")
@Slf4j
public class LoginController {
    /**
     *         StringEntity body = new StringEntity(JSON.toJSONString(loginDto), StandardCharsets.UTF_8);
     *        HttpPost httpPost = new HttpPost(url);
     *         HttpGet httpGet = new HttpGet(url);
     *         httpPost.setEntity(body);
     * @param loginDto
     * @return
     */
    @PostMapping("/login")
    public R login(@RequestBody LoginDto loginDto){
        String signSha256 = HeaUtil.sha256_HMAC("access_token=" + loginDto.getAccessToken() + "&openid=" +
                loginDto.getOpenId(), "your-secret-string");
        CloseableHttpClient client = HttpClientBuilder.create().build();
        String cloudUrl = "https://c6eee399-cd76-4a3c-9a89-fb65e8021c33.bspapp.com/functionName?access_token=%s&openid=%s";
        String url = String.format(cloudUrl, loginDto.getAccessToken(), loginDto.getOpenId());
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        System.out.println("请求url地址："+httpGet.getURI());
        try {
            response = client.execute(httpGet);
            HttpEntity responseEntity = response.getEntity();
            System.out.println(response);
            if (!ObjectUtils.isEmpty(responseEntity)){
                System.out.println("响应内容长度为："+responseEntity.getContentLength());
                System.out.println("响应内容为："+ EntityUtils.toString(responseEntity));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.ok();
    }

    public static void main(String[] args) throws IOException {
        File fileName = DrawImageUtil.drawImage("我是三星我是三星");
        System.out.println(fileName);
    }

    public InputStream drawImage(String name) {
        ImageOutputStream imageOutputStream = null;
        InputStream inputStream = null;
        try {
            // 获取图片的缓冲区，也就是所谓的画布
            BufferedImage bufferedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
            //获取画笔，画笔用于在画布上进行绘制
            Graphics paint = bufferedImage.getGraphics();
            //设置画笔的颜色
            paint.setColor(Color.WHITE);
            //绘制画布的背景色
            paint.fillRect(0, 0, 200, 200);
            Font font = new Font("微软雅黑", Font.BOLD, 40);
            paint.setFont(font);
            //设置画笔的颜色
            paint.setColor(Color.blue);
            //绘制显示的具体内容
            paint.drawString(name, (bufferedImage.getWidth() - font.getSize() * name.length()) / 2, (bufferedImage.getHeight() - font.getSize()) / 2);
            //绘制完成保存文件
            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            imageOutputStream = ImageIO.createImageOutputStream(bs);
            ImageIO.write(bufferedImage, ".jpg", imageOutputStream);
            inputStream = new ByteArrayInputStream(bs.toByteArray());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputStream;
    }
}
