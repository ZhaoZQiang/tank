package com.zzq.tank;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author zhaoziqiang
 * @Description: 资源初始化管理类
 * @date 2020/7/14 11:39
 */
public class ResourceMgr {
    public static BufferedImage goodTankU, goodTankR, goodTankD, goodTankL;
    public static BufferedImage badTankU, badTankR, badTankD, badTankL;
    public static BufferedImage bulletU, bulletR, bulletD, bulletL;
    public static BufferedImage[] explodes = new BufferedImage[16];

    private ResourceMgr(){}
    //静态内部类
    public static class ResourceMgrHolder{
        private static final ResourceMgr  resourceMgr=new ResourceMgr();
    }

    public static ResourceMgr getResourceMgr(){
        return ResourceMgrHolder.resourceMgr;
    }

    static {
        try {
            //主战坦克
            goodTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            goodTankR = ImageUtil.rotateImage(goodTankU, 90);
            goodTankD = ImageUtil.rotateImage(goodTankU, 180);
            goodTankL = ImageUtil.rotateImage(goodTankU, -90);
            //敌军坦克
            badTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
            badTankR = ImageUtil.rotateImage(badTankU, 90);
            badTankD = ImageUtil.rotateImage(badTankU, 180);
            badTankL = ImageUtil.rotateImage(badTankU, -90);
            //子弹
            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bulletR = ImageUtil.rotateImage(bulletU, 90);
            bulletD = ImageUtil.rotateImage(bulletU, 180);
            bulletL = ImageUtil.rotateImage(bulletU, -90);
            //爆炸
            for (int i = 0; i < explodes.length; i++) {
                explodes[i] =
                    ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i + 1) + ".gif"));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
