package test;

import com.zzq.tank.ImageUtil;
import com.zzq.tank.PropertyMgr;
import com.zzq.tank.ResourceMgr;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Properties;

/**
 * @author zhaoziqiang
 * @Description:
 * @date 2020/7/14 11:29
 */
public class ImageTest {
    @Test
    public void test() {
        try {
            BufferedImage read = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("images/tankU.gif"));
            Assertions.assertNotNull(read);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void rotateImageTest() {
        try {
            BufferedImage tankU =
                ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
            BufferedImage tankL = ImageUtil.rotateImage(tankU, -90);
            Assertions.assertNotNull(tankL);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void loadConfig() {
        Properties properties = new Properties();

        try {
            properties.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
            Assertions.assertNotNull(properties);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
