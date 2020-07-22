package com.zzq.tank;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoziqiang
 * @Description: Facade （门面模式）
 * @date 2020/7/22 17:52
 */
public class GameModel {
    public static final int TANK_SIZE = PropertyMgr.getInt("initialTankCount");
    //主站坦克
    Tank myTank = new Tank(200, 500, Dir.UP, false, this, Group.GOOD);
    //子弹
    List<Bullet> bullets = new ArrayList<Bullet>();
    //敌军坦克
    List<Tank> tanks = new ArrayList<>();
    //    Explode e=new Explode(100,200,this);
    //爆炸类集合
    List<Explode> explodeList = new ArrayList<>();

    public Tank getMyTank() {
        return myTank;
    }

    public GameModel(){
        //初始化敌方坦克
        for (int i = 0; i < TANK_SIZE; i++) {
            this.tanks.add(new Tank(100 + i * 50, 30, Dir.DOWN, true, this, Group.BAD));
        }
    }

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
}
