package com.zzq.tank;

import com.zzq.cor.BtCollider;
import com.zzq.cor.Collider;
import com.zzq.cor.ColliderChain;
import com.zzq.cor.TtCollider;

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
    List<GameObject> objects = new ArrayList<>();
    ColliderChain chain = new ColliderChain();

    public Tank getMyTank() {
        return myTank;
    }

    public GameModel() {
        //初始化敌方坦克
        for (int i = 0; i < TANK_SIZE; i++) {
            objects.add(new Tank(10 + i * 80, 30, Dir.DOWN, true, this, Group.BAD));
        }
        //初始化墙
        objects.add(new Wall(100,300,100,50 ));
        objects.add(new Wall(500,300,100,50 ));
        objects.add(new Wall(300,600,50,100 ));
        objects.add(new Wall(600,200,100,50 ));

    }

    public void add(GameObject o) {
        objects.add(o);
    }

    public void remove(GameObject o) {
        objects.remove(o);
    }


    public void paint(Graphics g) {

//        Color color = g.getColor();
//        g.setColor(Color.green);
//        g.drawString("子弹数：" + bullets.size(), 20, 50);
//        g.setColor(Color.RED);
//        g.drawString("敌军坦克数：" + tanks.size(), 100, 50);
//        g.setColor(color);

        //我方坦克
        if (myTank != null)
            myTank.paint(g);

        for (int i = 0; i < objects.size(); i++) {
            objects.get(i).paint(g);
        }

        //碰撞检测-策略模式+责任链
        for (int i = 0; i < objects.size(); i++) {
            GameObject o1 = objects.get(i);
            for (int j = i + 1; j < objects.size(); j++) {
                GameObject o2 = objects.get(j);
                if (!chain.collide(o1, o2)) break;
            }
        }

    }
}
