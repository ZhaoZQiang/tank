package com.zzq.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class TankFrame extends Frame {
    int x = 100, y = 100;

    public TankFrame() {
        this.setResizable(true);
        this.setTitle("tank war");
        this.setSize(800, 800);
        this.setVisible(true);
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
//                Runtime.getRuntime().exit(0);
            }
        });
        this.addKeyListener(new MyKeyListener());
    }

    @Override
    public void paint(Graphics g) {
        System.out.println("paint");
        g.fillRect(x, y, 50, 50);
//        x+=10;
//        y+=10;
//        repaint();

    }

    class MyKeyListener extends KeyAdapter {
        boolean bL = false;
        boolean bR = false;
        boolean bU = false;
        boolean bD = false;

        public void move(boolean bU,boolean bD,boolean bR,boolean bL){
            System.out.println("move开始......");
            if(bL&bU){
                x-=10;
                y+=-10;
            }else if(bL&bD){
                x-=10;
                y+=10;
            }else if(bR&bU){
                x+=10;
                y+=-10;
            }else if(bR&bD){
                x+=10;
                y+=10;
            }else if(bR){
                x+=10;
            }else if(bD){
                y+=10;
            }else if(bU){
                y-=10;
            }else{
                x-=10;
            }
            System.out.println("move结束......");

        }
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
                    bD= true;
                    break;
                default:
                    break;
            }
            move(bU,bD,bR,bL);
        }

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
                    bD= false;
                    break;
                default:
                    break;
            }
            move(bU,bD,bR,bL);
        }
    }
}
