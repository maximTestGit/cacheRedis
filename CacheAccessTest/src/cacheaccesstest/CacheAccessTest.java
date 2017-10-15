/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cacheaccesstest;

import cache.base.interfaces.Cache;
import cache.base.interfaces.CacheSetter;
import cache.partitions.CacheGetterMergerSimplObj;
import cache.partitions.CacheSetterSplitterSimpleObj;
import cache.redis.RedisCache;
import cache.serialize.DataSerializerJson;
import cache.setter.redis.RedisCacheSetter;
//import cache.setter.redis.RedisCacheSetterJson;
//import cache.setter.redis.RedisCacheSetterString;
import cache.transform.CacheSetterFormatterJson;
import cache.transform.CacheSetterFormatterString;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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
     System.out.println("setData");
        Cache.Key key = new Cache.Key();
        key.outerKey = "testSetData";

        Date date = new Date();
        long id = date.getTime();
        RedisData data = new RedisData();
        data.intData = id;
        data.dateData = date;

        CacheSetter<Object> instance = new RedisCache<Object>(
                "localhost", 6379,
                new CacheSetterSplitterSimpleObj(),
                new CacheGetterMergerSimplObj(),
                new DataSerializerJson()
        );
        instance.setData(key, data);
    }

}
