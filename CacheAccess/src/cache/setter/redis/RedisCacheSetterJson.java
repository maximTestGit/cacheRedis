/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache.setter.redis;

/**
 *
 * @author maxim
 */
public class RedisCacheSetterJson extends RedisCacheSetter<Object> {

    public RedisCacheSetterJson(String serverName, int port) {
        super(serverName, port, new CacheSetterFormatterJson());
    }

}
