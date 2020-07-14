package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * @author zhaoziqiang
 * @Description:
 * @date 2020/7/14 11:29
 */
public class ImageTest {
    @Test
    public void test(){
        try {
            BufferedImage read = ImageIO.read(this.getClass().getClassLoader().getResourceAsStream("images/tankU.gif"));
            Assertions.assertNotNull(read);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
