package com.zzq.tank;

public class Main {
    static  final int TANK_SIZE = 5;
    public static void main(String[] args) throws InterruptedException {
        TankFrame tf = new TankFrame();
        //初始化敌方坦克
        for (int i = 0; i < TANK_SIZE; i++) {
            tf.tanks.add(new Tank(100 + i * 50, 200, Dir.DOWN, false, tf));
        }
        while (true) {
            Thread.sleep(100);
            tf.repaint();
        }
    }
}
