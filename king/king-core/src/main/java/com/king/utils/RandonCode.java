package com.king.utils;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.security.MessageDigest;
import java.util.Random;

import org.apache.commons.lang.StringUtils;

/**
 * Created by wanghuaying on 2016/6/27.
 *
 * 登录 验证码生成
 */
public class RandonCode {
    /**
     * 掩码类型
     */
    public static enum CaptchaType{
        www,admin,broker
    }
    private CaptchaType captchaType;

    /**
     * md5散列后的随机码
     */
    private final String md5RandonCode;

    /**
     * 图片宽度
     */
    private final int imgWidth;

    /**
     * 图片高度
     */
    private final int imgHeight;

    /**
     * 随机码生成字典
     */
    private static final String[] strArr = {"3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "J", "K", "M", "N", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y","Z"};



    private int widthByCaptchaType;

    public CaptchaType getCaptchaType() {
        return captchaType;
    }

    public void setCaptchaType(CaptchaType captchaType) {
        this.captchaType = captchaType;
    }

    public String getMd5RandonCode() {
        return md5RandonCode;
    }

    public int getImgWidth() {
        return imgWidth;
    }

    public int getImgHeight() {
        return imgHeight;
    }

    public static String[] getStrArr() {
        return strArr;
    }

    public int getWidthByCaptchaType() {
        return widthByCaptchaType;
    }

    public void setWidthByCaptchaType(int widthByCaptchaType) {
        this.widthByCaptchaType = widthByCaptchaType;
    }

    public int getHeightByCaptchaType() {
        return heightByCaptchaType;
    }

    public void setHeightByCaptchaType(int heightByCaptchaType) {
        this.heightByCaptchaType = heightByCaptchaType;
    }

    public int getImgRandNumber() {
        return imgRandNumber;
    }

    public String getRandonCode() {
        return randonCode;
    }

    private int heightByCaptchaType;

    /**
     * 随机生成字符数量
     */
    private final int imgRandNumber;

    /**
     * 生成的随机码
     */
    private final String randonCode;




    /**
     * 构造函数
     * @param imgRandNumber 随机生成多少个字符,最少4个字符。
     */
    public RandonCode(int imgRandNumber,CaptchaType captchaType) {
        this.captchaType = captchaType;
        if(imgRandNumber < 4)
        {
            imgRandNumber = 4;
        }
        switch (captchaType){
            case  www:
                this.widthByCaptchaType = 20*imgRandNumber + 25;
                this.heightByCaptchaType = 32;
                break;
            case admin:
                this.widthByCaptchaType = 15*imgRandNumber + 10;
                this.heightByCaptchaType = 32;
                break;
            case broker:
                this.widthByCaptchaType = 15*imgRandNumber + 10;
                this.heightByCaptchaType = 32;
                break;
        }

        this.imgWidth = this.widthByCaptchaType;
        this.imgHeight = this.heightByCaptchaType;
        this.imgRandNumber = imgRandNumber;
        this.randonCode = generateRandonCode();
        this.md5RandonCode = encrypt(randonCode);
    }

    /**
     * 依据字典生成随即码
     * @return 随机码
     */
    private String generateRandonCode(){
        // 生成随机类
        Random random = new Random();
        String sRand = "";
        for (int i = 0; i < imgRandNumber; i++) {
            String rand = String.valueOf(strArr[random.nextInt(strArr.length)]);
            sRand += rand;
        }
        return sRand;
    }

    /**
     * 使用md5散列字符串
     * @param srcStr 输入的字符串
     * @return 加密后的字符串
     */
    private static final String encrypt(String srcStr) {
        try {
            String result = "";
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] bytes = md.digest(srcStr.getBytes("utf-8"));
            for(byte b:bytes){
                String hex = Integer.toHexString(b&0xFF).toUpperCase();
                result += ((hex.length() ==1 ) ? "0" : "") + hex;
            }
            return result;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * 渲染图片
     */
    public BufferedImage render() {
        BufferedImage image = new BufferedImage(imgWidth, imgHeight, BufferedImage.TYPE_INT_RGB);
        return  drawGraphic(image);

    }

    /**
     * 绘制验证码
     * @param image BufferedImage对象
     */
    private BufferedImage drawGraphic(BufferedImage image){
        // 获取图形上下文
        Graphics g = image.createGraphics();
        // 生成随机类
        Random random = new Random();
        // 设定背景色
        g.setColor(new Color(202, 202, 202));
//        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, imgWidth, imgHeight);
        // 设定字体
        g.setFont(new Font("Times New Roman", Font.PLAIN, 18));

        // 随机产生155条干扰线，使图象中的认证码不易被其它程序探测到
        int max = 220;
        int min = 50;
        for (int i = 0; i < 10; i++) {
            int x = random.nextInt(imgWidth);
            int y = random.nextInt(imgHeight);
            int xl = random.nextInt(15);
            int yl = random.nextInt(22);
            g.setColor(getRandColor(random.nextInt(min), random.nextInt(max)%(max-min+1) + min));
            g.drawLine(x, y, x + xl, y + yl);
        }

        g.setFont(new Font("TimesRoman", Font.ITALIC, 25));
        //取随机产生的认证码(img_randNumber位数字)
        for (int i = 0; i < imgRandNumber; i++) {
            String rand = String.valueOf(this.randonCode.charAt(i));
            // 将认证码显示到图象中
            g.setColor(getRandColor(random.nextInt(min), random.nextInt(max)%(max-min+1) + min));
            switch (captchaType){
                case  www:
                    g.drawString(rand, 25 * i + random.nextInt(8), 22+random.nextInt(2));
                    break;
                case admin:
                    g.drawString(rand, 15 * i + random.nextInt(3), 22+random.nextInt(8));
                    break;
                case broker:
                    g.drawString(rand, 15 * i + random.nextInt(3), 22+random.nextInt(8));
                    break;
            }
        }
        // 图象生效
        g.dispose();
        return  image;
    }

    /**
     * 验证码检查
     * @param md5RandomCode  md5散列后的验证码
     * @param inputRandomCode 用户输入的验证码
     * @return 若二者一致，返回true，否则返回false
     */
    public static boolean validate(String md5RandomCode, String inputRandomCode) {
        if (StringUtils.isBlank(md5RandomCode) || StringUtils.isBlank(inputRandomCode))
            return false;
        try {
            inputRandomCode = inputRandomCode.toUpperCase();
            inputRandomCode = encrypt(inputRandomCode);
            return inputRandomCode.equals(md5RandomCode);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 生成随机颜色
     * @param fc
     * @param bc
     * @return 颜色对象
     */
    private Color getRandColor(int fc, int bc) {
        Random random = new Random();
        if (fc > 255)
            fc = 255;
        if (bc > 255)
            bc = 255;
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
