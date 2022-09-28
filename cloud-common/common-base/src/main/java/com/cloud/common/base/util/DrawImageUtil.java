package com.cloud.common.base.util;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class DrawImageUtil {
    public static File drawImage(String name) throws FileNotFoundException, IOException {
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
        System.out.println((bufferedImage.getHeight() - font.getSize()) / 2);
        paint.drawString(name, (bufferedImage.getWidth() - font.getSize() * name.length()) / 2, (bufferedImage.getHeight() - font.getSize()) / 2);
        //绘制完成保存文件
        ImageIO.write(bufferedImage, "jpg", new FileOutputStream(""));
        return new File(name + ".jpg");
    }

}
