package com.zzq.tank;

/**
 * @description: 开火事件 FireEvent
 * @author: bjzhaoziqiang
 * @time: 2020/8/2 0:03
 */
public class FireEvent {
    private Tank tank;

    public Tank getSource() {
        return tank;
    }
    public FireEvent(Tank tank){
        this.tank=tank;
    }
}
