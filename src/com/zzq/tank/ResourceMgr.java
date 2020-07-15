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
    public static BufferedImage tankU, tankR, tankD, tankL;
    public static BufferedImage bulletU, bulletR, bulletD, bulletL;
    public static BufferedImage[] explodes = new BufferedImage[16];

    static {
        try {
            tankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            tankR = ImageUtil.rotateImage(tankU, 90);
            tankD = ImageUtil.rotateImage(tankU, 180);
            tankL = ImageUtil.rotateImage(tankU, -90);
            bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
            bulletR = ImageUtil.rotateImage(bulletU, 90);
            bulletD = ImageUtil.rotateImage(bulletU, 180);
            bulletL = ImageUtil.rotateImage(bulletU, -90);
            for (int i = 0; i < explodes.length; i++) {
                explodes[i] =
                    ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i + 1) + ".gif"));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
