package com.zzq.tank;

import java.io.Serializable;

/**
 * @description: 抽象观察者类
 * @author: bjzhaoziqiang
 * @time: 2020/8/2 0:16
 */
public abstract class FireObservers implements Serializable {
    abstract void fire(FireEvent fireEnent);
}
