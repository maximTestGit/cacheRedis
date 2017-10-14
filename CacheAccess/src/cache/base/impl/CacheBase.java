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
public abstract class CacheBase<TData, TPersist>
        implements CacheGetter<TData, TPersist>, CacheSetter<TData, TPersist> {

    protected CacheSetterFormatter<TData, TPersist> setterFormatter;
    protected CacheGetterTransformer<TData, TPersist> getterTransformer;
    
    protected CacheSetterSplitter<TData> setterSplitter;
    protected CacheGetterMerger<TData> getterMerger;

    public CacheBase(
            CacheSetterFormatter<TData, TPersist> setterFormatter, 
            CacheGetterTransformer<TData, TPersist> getterTransformer,
            CacheSetterSplitter<TData> setterSplitter,
            CacheGetterMerger<TData> getterMerger) {
        
        this.setterFormatter = setterFormatter;
        this.getterTransformer = getterTransformer;
        this.setterSplitter = setterSplitter;
        this.getterMerger = getterMerger;
    }

    @Override
    public TData getData(Key key) {
        KeyValues<TPersist> values = provideData(key);
        KeyValues<TData> partitions = getterTransformer.transform(values);
        TData result = getterMerger.merge(partitions);
        return result;
    }

    @Override
    public void setData(Key key, TData data) {
        KeyValues<TData> partitions = setterSplitter.split(data);
        KeyValues<TPersist> values = setterFormatter.Format(partitions);
        persistData(values);
    }

    protected abstract KeyValues<TPersist> provideData(Key key);
    protected abstract void persistData(KeyValues<TPersist> data);
    
    
}
