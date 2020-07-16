package com.zzq.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;

public class TankFrame extends Frame {
    static final int GAME_WIDTH = 1080, GAME_HEIGHT = 960;
    //主站坦克
    Tank myTank = new Tank(200, 500, Dir.UP, false, this, Group.GOOD);
    //子弹
    List<Bullet> bullets = new ArrayList<Bullet>();
    //敌军坦克
    List<Tank> tanks = new ArrayList<>();
    //    Explode e=new Explode(100,200,this);
    //爆炸类集合
    List<Explode> explodeList = new ArrayList<>();


    public TankFrame() {
        this.setResizable(true);
        this.setTitle("tank war");
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.setVisible(true);
        //重写窗口监听器
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        //重写按键监听器
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
     * @param g
     * @return void
     * @Description 调用画笔
     * @author zhaoziqiang
     * @date 2020/7/13 18:28
     */
    @Override
    public void paint(Graphics g) {
        Color color = g.getColor();
        g.setColor(Color.green);
        g.drawString("子弹数：" + bullets.size(), 20, 50);
        g.setColor(Color.RED);
        g.drawString("敌军坦克数：" + tanks.size(), 100, 50);
        g.setColor(color);

        //我方坦克
        if (myTank != null)
            myTank.paint(g);
        //敌方坦克
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }
        //子弹
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }
        //碰撞检测
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            for (int j = 0; j < tanks.size(); j++) {
                Tank tank = tanks.get(j);
                bullet.collideWith(tank);
            }
            //检测自己
            if (myTank != null)
                bullet.collideWith(myTank);
        }
        //爆炸
        for (int i = 0; i < explodeList.size(); i++) {
            explodeList.get(i).paint(g);
        }
    }

    /**
     * 按键监听类
     */
    class MyKeyListener extends KeyAdapter {
        boolean bL = false;
        boolean bR = false;
        boolean bU = false;
        boolean bD = false;

        /**
         * @param
         * @return void
         * @Description 设置坦克运动方向
         * @author zhaoziqiang
         * @date 2020/7/13 18:28
         */
        public void setMainTankDir() {
            if (myTank == null) return;
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
                //添加坦克运动声效
                new Thread(()->new Audio("audio/tank_move.wav").play()).start();
            }
        }

        /**
         * @param e
         * @return void
         * @Description 按下按键
         * @author zhaoziqiang
         * @date 2020/7/13 18:29
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
         * @param e
         * @return void
         * @Description 释放按键
         * @author zhaoziqiang
         * @date 2020/7/13 18:29
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
                    if (myTank != null)
                        myTank.fire();
                    break;
                default:
                    break;
            }
            setMainTankDir();
        }

    }
}
