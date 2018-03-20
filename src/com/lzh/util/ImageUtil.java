package com.lzh.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import sun.misc.BASE64Encoder;

public class ImageUtil {

    /**
     * @param args
     */
    public static void main(String[] args) {
        try {
            System.out.println(img2Base64("C:\\Users\\Jenkin\\Desktop\\TIM图片20171213101602.png"));
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
    
    /**
     * 将图片缓冲流转换为base64字符串
     * 
     * @param im
     * @return
     * @throws IOException
     */
    public static String img2Base64(BufferedImage im) throws IOException {
        String img64 = "";
        BASE64Encoder enc = new BASE64Encoder();
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(im, "png", bos);
        img64 = enc.encode(bos.toByteArray());
        bos.close();

        return img64.replaceAll("(\r\n|\r|\n|\n\r)", "");
    }

    /**
     * 将图片转换为base64字符串
     * 
     * @param imgPath 图片路径
     * @return
     * @throws IOException
     */
    public static String img2Base64(String imgPath) throws IOException {
        File file = new File(imgPath);
        if (!file.exists()) {
            return "";
        }
        return img2Base64(ImageIO.read(file));
    }

}
