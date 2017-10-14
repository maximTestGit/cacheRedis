/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache.redis;

import cache.base.impl.CacheBase;
import cache.partitions.CacheGetterMerger;
import cache.partitions.CacheSetterSplitter;
import cache.transform.CacheGetterTransformer;
import cache.transform.CacheSetterFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import redis.clients.jedis.Jedis;

/**
 *
 * @author maxim
 * @param <TData>
 * @param <TPersist>
 */
public class RedisCache<TData, TPersist>
        extends CacheBase<TData, TPersist> {

    protected enum RedisDataType {
        STRING,
        LIST,
        HASH
    }
    private Jedis server;
    protected DataSerializer serializer;

    protected final Jedis getServer() {
        return this.server;
    }

    protected final void setServer(Jedis newServer) {
        this.server = newServer;
    }

    public String serverInfo() {
        String result = this.server.info();
        return result;
    }

    public RedisCache(
            String serverName, int port,
            CacheSetterFormatter<TData, TPersist> setterFormatter,
            CacheGetterTransformer<TData, TPersist> getterTransformer,
            CacheSetterSplitter<TData> setterSplitter,
            CacheGetterMerger<TData> getterMerger,
            DataSerializer serializer) {
        super(setterFormatter, getterTransformer, setterSplitter, getterMerger);
        Jedis srv = new Jedis(serverName, port);
        this.serializer = serializer;
        this.setServer(srv);
    }

    @Override
    protected KeyValues<TPersist> provideData(Key key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void persistData(KeyValues<TPersist> data) throws IllegalArgumentException {
        RedisDataType redisAction = determineRedisType(data);
        switch (redisAction) {
            case STRING:
                persistString(data);
                break;
            case LIST:
                persistList(data);
                break;
            case HASH:
                persistHash(data);
                break;
            default:
                throw new IllegalArgumentException("Unknown RedisDataType: " + redisAction);
        }
    }

    private RedisDataType determineRedisType(KeyValues<TPersist> data) {
        RedisDataType result;
        if (data.innerKeys == null) {
            if (data.values.length == 1) {
                result = RedisDataType.STRING;
            } else {
                result = RedisDataType.LIST;
            }
        } else {
            result = RedisDataType.HASH;

        }
        return result;
    }

    private void persistString(KeyValues<TPersist> data) {
        this.getServer().set(data.outerKey, serializer.serialize(data.values[0]));
    }

    private void persistList(KeyValues<TPersist> data) {
        ArrayList<String> strings = new ArrayList<String>(data.values.length);
        for (int i = 0; i < data.values.length; i++) {
            strings.add(serializer.serialize(data.values[i]));
        }
        String[] stringArray = new String[strings.size()];
        this.getServer().lpush(data.outerKey, stringArray);
    }

    private void persistHash(KeyValues<TPersist> data) {
        Map<String, String> set = new HashMap<>();
        for (int i = 0; i < data.values.length; i++) {
            set.put(data.innerKeys[i], serializer.serialize(data.values[i]));
        }
        this.getServer().hmset(data.outerKey, set);
    }

}
