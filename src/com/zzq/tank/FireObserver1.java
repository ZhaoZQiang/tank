package com.zzq.tank;

/**
 * @description: 具体观察者1
 * @author: bjzhaoziqiang
 * @time: 2020/8/2 0:17
 */
public class FireObserver1 extends FireObservers{
    @Override
    void fire(FireEvent fireEvent) {
        fireEvent.getSource().fire();
    }
}
