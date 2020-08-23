package com.zzq.tank;

import java.io.Serializable;

public interface FireStrategy<T> extends Serializable {
    void fire(T a);
}
