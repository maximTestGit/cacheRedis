/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache.partitions;

import cache.base.interfaces.Cache;
import java.lang.reflect.Array;

/**
 *
 * @author maxim
 */
public class CacheSetterSplitterObjPartition implements CacheSetterSplitter<Object> {

    
    @Override
    public Cache.KeyValues<Object> split(Cache.Key key, Object data) {
        Cache.KeyValues<Object> result = new Cache.KeyValues<>(key);
        result.values = (Object[])Array.newInstance(Object.class, 1);
        result.values[0] = data;
        return result;
    }
    
}
