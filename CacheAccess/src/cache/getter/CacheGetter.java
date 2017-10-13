/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache.getter;

/**
 *
 * @author maxim
 * @param <TValue>
 */
public interface CacheGetter<TValue> {
    TValue getValue(String key);
}
