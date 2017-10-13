/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cacheaccesstest;

import cache.setter.redis.RedisCacheSetter;
import cache.setter.redis.RedisCacheSetterString;

/**
 *
 * @author maxim
 */
public class CacheAccessTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        RedisCacheSetter<String> setter;
        setter = new RedisCacheSetterString("localhost", 6379);
        setter.setData("first:test", "first redis data");
    }
    
}
