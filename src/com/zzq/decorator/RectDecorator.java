package com.zzq.decorator;


import com.zzq.tank.GameObject;

import java.awt.*;

/**
 * @description: 方框装饰器
 * @author: bjzhaoziqiang
 * @time: 2020/7/26 17:44
 */
public class RectDecorator extends GoDecorator{


    public RectDecorator(GameObject go) {
        super(go);
    }

    @Override
    public void paint(Graphics g) {
        this.x=go.x;
        this.y=go.y;
        go.paint(g);
        Color color = g.getColor();
        g.setColor(Color.RED);
        g.fillRect(x,y,5,5);
        g.setColor(color);
    }

}
