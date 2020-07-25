package com.zzq.cor;

import com.zzq.tank.GameObject;

public interface Collider {
    boolean collide(GameObject o1, GameObject o2);
}
