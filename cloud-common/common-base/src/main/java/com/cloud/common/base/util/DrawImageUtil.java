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
        File file = null;
        List<String> ls = new ArrayList<>((int) Math.round(name.length() / 4));
        try {
            // 获取图片的缓冲区，也就是所谓的画布
            BufferedImage bufferedImage = new BufferedImage(200, 200, BufferedImage.TYPE_INT_RGB);
            //获取画笔，画笔用于在画布上进行绘制
            Graphics paint = bufferedImage.getGraphics();
            paint.setColor(new Color(0xB0EEEE));
            //绘制画布的背景色
            paint.fillRect(0, 0, 200, 200);
            Font font = new Font("微软雅黑", Font.BOLD, 40);
            paint.setFont(font);
            //设置画笔的颜色
            paint.setColor(new Color(0x53B0EE));
////            //绘制显示的具体内容
//            paint.drawString(name, (bufferedImage.getWidth() - font.getSize() * name.length()) / 2, font.getSize() + 60);
            //绘制完成保存文件
            ImageIO.write(bufferedImage, "jpg", new FileOutputStream(name + ".jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new File(name + ".jpg");
    }

}
