/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache.setter;

/**
 *
 * @author maxim
 * @param <TData>
 */
public interface CacheSetter<TData> {
    void setData(String key, TData data);
}
