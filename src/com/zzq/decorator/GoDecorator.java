package com.zzq.decorator;

import com.zzq.tank.GameObject;

import java.awt.*;

/**
 * @description:
 * @author: bjzhaoziqiang
 * @time: 2020/7/26 17:42
 */
public  class GoDecorator extends GameObject {
    protected GameObject go;

    public GoDecorator(GameObject go) {
        this.go = go;
    }

    @Override
    public void paint(Graphics g) {
        this.go.paint(g);
    }
}
