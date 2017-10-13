/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cacheaccesstest;

import cache.setter.redis.RedisCacheSetter;
import cache.setter.redis.RedisCacheSetterJson;
import cache.setter.redis.RedisCacheSetterString;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author maxim
 */
public class CacheAccessTest {

            
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Date date = new Date();
        long id = date.getTime();
        RedisData data = new RedisData();
        data.intData = id;
        data.dateData = date;
       
        RedisCacheSetter<String> stringSetter = new RedisCacheSetterString("localhost", 6379);
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        stringSetter.setData("test:string:"+data.intData, "redis data: " +dateFormat.format(data.dateData));
        
        RedisCacheSetter<Object> jsonSetter = new RedisCacheSetterJson("localhost", 6379);
        jsonSetter.setData("test:json:"+id, data);
    }

}
