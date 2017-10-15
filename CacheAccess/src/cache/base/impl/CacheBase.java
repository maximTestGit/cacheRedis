/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache.base.impl;

import cache.partitions.CacheGetterMerger;
import cache.partitions.CacheSetterSplitter;
import cache.transform.CacheGetterTransformer;
import cache.base.interfaces.CacheGetter;
import cache.base.interfaces.CacheSetter;
import cache.transform.CacheSetterFormatter;

/**
 *
 * @author maxim
 * @param <TPersist>
 * @param <TData>
 */
public abstract class CacheBase<TData>
        implements CacheGetter<TData>, CacheSetter<TData> {

//    protected CacheSetterFormatter<TData, TPersist> setterFormatter;
//    protected CacheGetterTransformer<TData, TPersist> getterTransformer;
    
    protected CacheSetterSplitter<TData> setterSplitter;
    protected CacheGetterMerger<TData> getterMerger;

    public CacheBase(
//            CacheSetterFormatter<TData, TPersist> setterFormatter, 
//            CacheGetterTransformer<TData, TPersist> getterTransformer,
            CacheSetterSplitter<TData> setterSplitter,
            CacheGetterMerger<TData> getterMerger) {
        
//        this.setterFormatter = setterFormatter;
//        this.getterTransformer = getterTransformer;
        this.setterSplitter = setterSplitter;
        this.getterMerger = getterMerger;
    }

    @Override
    public TData getData(Key key) {
        KeyValues<TData> partitions = provideData(key);
//        KeyValues<TData> partitions = getterTransformer.transform(values);
        TData result = getterMerger.merge(partitions);
        return result;
    }

    @Override
    public void setData(Key key, TData data) {
        KeyValues<TData> partitions = setterSplitter.split(key, data);
//        KeyValues<TPersist> values = setterFormatter.Format(partitions);
        persistData(partitions);
    }

    protected abstract KeyValues<TData> provideData(Key key);
    protected abstract void persistData(KeyValues<TData> data);
    
    
}
