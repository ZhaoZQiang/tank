package com.zzq.tank;

import com.zzq.cor.ColliderChain;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author zhaoziqiang
 * @Description: Facade （门面模式）
 * @date 2020/7/22 17:52
 */
public class GameModel implements Serializable {
    public static final int TANK_SIZE = PropertyMgr.getInt("initialTankCount");

    private static final GameModel INSTANCE = new GameModel();
    static {
        INSTANCE.init();
    }
    public static GameModel getInstance() {
        return INSTANCE;
    }
    //主站坦克
    Tank myTank;
    List<GameObject> objects = new ArrayList<>();
    ColliderChain chain = new ColliderChain();
    private void init() {
        myTank=new Tank(200, 500, Dir.UP, false, Group.GOOD);
        //初始化敌方坦克
        for (int i = 0; i < TANK_SIZE; i++) {
            new Tank(10 + i * 80, 30, Dir.DOWN, true, Group.BAD);
        }
        //初始化墙
//        new Wall(100, 300, 100, 50);
//        new Wall(500, 300, 100, 50);
//        new Wall(300, 600, 50, 100);
//        new Wall(600, 200, 100, 50);

    }

    public Tank getMyTank() {
        return myTank;
    }

    private GameModel() {

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

        //碰撞检测-调停者模式+责任链
        for (int i = 0; i < objects.size(); i++) {
            GameObject o1 = objects.get(i);
            for (int j = i + 1; j < objects.size(); j++) {
                GameObject o2 = objects.get(j);
                if (!chain.collide(o1, o2)) break;
            }
        }

    }

    /***
     *
     * @Description: 存盘
     * @return void 
     * @Author: bjzhaoziqiang 
     * @Date: 2020/8/23 15:39
     */ 
    public void save() {
        File file = new File("d:/tank/tank.data");
        ObjectOutputStream os=null;
        try {
           os = new ObjectOutputStream(new FileOutputStream(file));
            os.writeObject(myTank);
            os.writeObject(objects);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /***
     *
     * @Description:  加载
     *
     * @return void
     * @Author: bjzhaoziqiang
     * @Date: 2020/8/23 15:42
     */
    public void load() {
        File file = new File("d:/tank/tank.data");
        ObjectInputStream ois=null;
        try {
            ois = new ObjectInputStream(new FileInputStream(file));
            myTank= (Tank) ois.readObject();
            objects= (List) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
