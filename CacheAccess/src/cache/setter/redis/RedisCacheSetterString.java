/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache.setter.redis;

import cache.setter.CacheSetterFormatter;


public class RedisCacheSetterString extends RedisCacheSetter<String> {

    public RedisCacheSetterString(String serverName, int port) {
        super(serverName, port, new CacheSetterFormatterString());
    }

    @Override
    public void setData(String key, String data) {
        this.getServer().append(key, data);
    }
    
}
