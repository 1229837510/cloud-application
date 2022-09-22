package com.cloud.movie.controller;

import com.alibaba.fastjson.JSON;
import com.cloud.common.base.result.R;
import com.cloud.movie.dto.LoginDto;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

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
        CloseableHttpClient client = HttpClientBuilder.create().build();
        String cloudUrl = "https://c6eee399-cd76-4a3c-9a89-fb65e8021c33.bspapp.com/loginTest?access_token=%s&openid=%s";
        String url = String.format(cloudUrl, loginDto.getAccessToken(), loginDto.getOpenId());
        HttpGet httpGet = new HttpGet(url);
        CloseableHttpResponse response = null;
        System.out.println("请求url地址："+httpGet.getURI());
        try {
            response = client.execute(httpGet);
            HttpEntity responseEntity = response.getEntity();
            if (!ObjectUtils.isEmpty(responseEntity)){
                System.out.println("响应内容长度为："+responseEntity.getContentLength());
                System.out.println("响应内容为："+ EntityUtils.toString(responseEntity));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return R.ok();
    }
}
