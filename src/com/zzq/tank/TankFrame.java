package com.zzq.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class TankFrame extends Frame {
    static final int GAME_WIDTH = 800, GAME_HEIGHT = 800;
    Tank myTank = new Tank(200, 500, Dir.UP, false, this);
    List<Bullet> bullets = new ArrayList<Bullet>();
    List<Tank> tanks=new ArrayList<>();
    public TankFrame() {
        this.setResizable(true);
        this.setTitle("tank war");
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        this.addKeyListener(new MyKeyListener());
    }

    /**
     * 消除闪烁
     *
     * @param g
     */
    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics goffScreen = offScreenImage.getGraphics();
        Color c = goffScreen.getColor();
        goffScreen.setColor(Color.BLACK);
        goffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        goffScreen.setColor(c);
        paint(goffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    /**
     *
     * @Description 调用画笔
     * @author zhaoziqiang
     * @date 2020/7/13 18:28
     * @param g
     * @return void
     */
    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.green);
        g.drawString("子弹数："+bullets.size(),20,50);
        g.setColor(Color.RED);
        g.drawString("敌军坦克数："+tanks.size(),100,50);
        g.setColor(color);

        myTank.paint(g);//我方坦克
        if(tanks.size()>0){//敌方坦克
            Iterator<Tank> it = tanks.iterator();
            while (it.hasNext()){
                Tank next = it.next();
                //判断坦克是否存活
                if(!next.isLive()){
                    it.remove();
                }else{
                    next.paint(g);
                }
            }
        }
        //子弹
        Iterator<Bullet> iterator = bullets.iterator();
        while (iterator.hasNext()){
            Bullet next = iterator.next();
            //判断子弹是否存活
            if (!next.isLive()) {
                iterator.remove();
            } else {
                next.paint(g);
            }
        }
        //碰撞检测
        for (int i = 0; i <bullets.size() ; i++) {
            Bullet bullet = bullets.get(i);
            for (int j = 0; j <tanks.size() ; j++) {
                Tank tank = tanks.get(j);
                bullet.collideWith(tank);
            }

        }
    }

    class MyKeyListener extends KeyAdapter {
        boolean bL = false;
        boolean bR = false;
        boolean bU = false;
        boolean bD = false;

        /**
         *
         * @Description  设置坦克运动方向
         * @author zhaoziqiang
         * @date 2020/7/13 18:28
         * @param
         * @return void
         */
        public void setMainTankDir() {
            if (!bL & !bU & !bR & !bD) {
                myTank.setMoving(false);
            } else {
                myTank.setMoving(true);
                if (bL)
                    myTank.setDir(Dir.LEFT);
                if (bR)
                    myTank.setDir(Dir.RIGHT);
                if (bD)
                    myTank.setDir(Dir.DOWN);
                if (bU)
                    myTank.setDir(Dir.UP);
            }
        }

        /**
         *
         * @Description 按下按键
         * @author zhaoziqiang
         * @date 2020/7/13 18:29
         * @param e
         * @return void
         */
        @Override
        public void keyPressed(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

        /**
         *
         * @Description 释放按键
         * @author zhaoziqiang
         * @date 2020/7/13 18:29
         * @param e
         * @return void
         */
        @Override
        public void keyReleased(KeyEvent e) {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

    }
}
