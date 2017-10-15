/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache.partitions;

import cache.base.interfaces.Cache;


public class CacheGetterMergerSimplObj implements CacheGetterMerger<Object> {

    @Override
    public Object merge(Cache.KeyValues<Object> partitions) {
        return partitions.values[0];
    }
    
}
