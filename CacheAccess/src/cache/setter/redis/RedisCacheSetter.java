/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache.setter.redis;

import cache.redis.RedisCacheBase;
import cache.setter.CacheSetter;
import cache.setter.CacheSetterFormatter;

public abstract class RedisCacheSetter<TData> extends RedisCacheBase implements CacheSetter<TData> {

    private CacheSetterFormatter<TData> formatter;
    public RedisCacheSetter(String serverName, int port, CacheSetterFormatter<TData> formatter) {
        super(serverName, port);
        this.formatter = formatter;
    }
    @Override
    public void setData(String key, TData data) {
        String saveData = this.formatter.Format(data);
        this.getServer().set(key, saveData);
    }
    
}
