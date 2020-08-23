package com.zzq.tank;

import java.awt.*;
import java.io.Serializable;

/**
 * @description: 游戏物体父类
 * @author: bjzhaoziqiang
 * @time: 2020/7/25 13:23
 */
public abstract class GameObject implements Serializable {
    public int x;
    public int y;
    public int width;
    public int height;
    public abstract void paint(Graphics g);

}
