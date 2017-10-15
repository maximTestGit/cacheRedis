/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache.transform;

import cache.base.interfaces.Cache;


public class CacheGetterTransformerSimpleObj implements CacheGetterTransformer<Object,Object> {

    @Override
    public Cache.KeyValues<Object> transform(Cache.KeyValues<Object> data) {
        return data;
    }
    
}
