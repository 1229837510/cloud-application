package com.cloud.common.base.util;

import com.sun.org.apache.bcel.internal.generic.NEW;
import org.checkerframework.checker.units.qual.C;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class DrawImageUtil {
    public static File drawImage(String name) {
        try {
            // 获取图片的缓冲区，也就是所谓的画布
            BufferedImage bufferedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
            //获取画笔，画笔用于在画布上进行绘制
            Graphics paint = bufferedImage.getGraphics();
            paint.setColor(new Color(0xE5E3E3));
            //绘制画布的背景色
            paint.fillRect(0, 0, 200, 200);
            Font font = new Font("微软雅黑", Font.BOLD, 40);
            paint.setFont(font);
            //设置画笔的颜色
            paint.setColor(new Color(0x0F0F10));
            //绘制显示的具体内容
            drawString(paint, name, bufferedImage, font);
            //绘制完成保存文件
            ImageIO.write(bufferedImage, "jpg", new FileOutputStream(name + ".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new File(name + ".jpg");
    }

    public static void drawString(Graphics paint, String name, BufferedImage bufferedImage, Font font) {
        int length = name.length();
        int batchSize = 4;
        int round = length / batchSize;
        for (int i = 0; i <= round; i++) {
            int fromIndex = i * batchSize;
            int toIndex = (i < round) ? ((i + 1) * batchSize) : length;
            String splitName = name.substring(fromIndex, toIndex);
            paint.drawString(splitName, (bufferedImage.getWidth() - font.getSize() * splitName.length()) / 2, bufferedImage.getHeight() / 2 + (i * font.getSize() ));
        }
    }
}
