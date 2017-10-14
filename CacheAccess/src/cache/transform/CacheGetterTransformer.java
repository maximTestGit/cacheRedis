/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache.transform;

import cache.base.interfaces.Cache;

/**
 *
 * @author maxim
 * @param <TData>
 * @param <TPersist>
 */
public interface CacheGetterTransformer<TData, TPersist> {
    Cache.KeyValues<TData> transform(Cache.KeyValues<TPersist> data);
}
