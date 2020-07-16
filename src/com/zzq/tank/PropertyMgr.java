package com.zzq.tank;

import java.io.IOException;
import java.util.Properties;

/**
 * @author zhaoziqiang
 * @Description:
 * @date 2020/7/16 14:55
 */
public class PropertyMgr {
    static Properties properties=new Properties();
    static {
        try {
            properties.load(PropertyMgr.class.getClassLoader().getResourceAsStream("config"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("properties.load 执行了......");
    }

    public static Integer getInt(String key){
        if(properties==null){
            return null;
        }
        return Integer.parseInt((String)properties.get(key));
    }

    public static String getStr(String key){
        if(properties==null){
            return null;
        }
        return properties.getProperty(key);
    }
}
