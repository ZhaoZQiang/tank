package com.zzq.cor;

import com.zzq.tank.GameObject;
import com.zzq.tank.PropertyMgr;

import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.List;

/**
 * @description: 责任链类
 * @author: bjzhaoziqiang
 * @time: 2020/7/25 15:48
 */
public class ColliderChain implements  Collider{

    List<Collider> colliders = new LinkedList();

    public ColliderChain(){
        String colliders = PropertyMgr.getStr("colliders");
        String[] collidersArr = colliders.split(",");
        for (int i = 0; i <collidersArr.length ; i++) {
            try {
                add((Collider) Class.forName(collidersArr[i]).getDeclaredConstructor().newInstance());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public void add(Collider c){
        colliders.add(c);
    }

    public void remove(Collider c){
        colliders.remove(c);
    }

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        for (int i = 0; i <colliders.size() ; i++) {
            if(!colliders.get(i).collide(o1,o2)){
                return false;
            }
        }
        return true;
    }
}
