/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache.getter.redis;

import cache.getter.CacheGetter;
import cache.redis.RedisCacheBase;


public abstract class RedisCacheGetter<TValue> extends RedisCacheBase implements CacheGetter<TValue> {

    public RedisCacheGetter(String serverName, int port) {
        super(serverName, port);
    }

}
