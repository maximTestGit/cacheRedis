/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache.partitions;

import cache.base.interfaces.Cache;

/**
 *
 * @author maxim
 */
public interface CacheGetterMerger<TData> {
    TData merge(Cache.KeyValues<TData> partitions);
}
