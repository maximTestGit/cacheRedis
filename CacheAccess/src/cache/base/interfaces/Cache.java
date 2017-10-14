/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache.base.interfaces;

/**
 *
 * @author maxim
 * @param <TKey>
 * @param <TPersist>
 * @param <TData>
 */
public interface Cache<TData, TPersist> {

    public class Key {

        public String outerKey;
        public String[] innerKeys;
    }

    public class KeyValues<TPersist> extends Key {
        public TPersist[] values;
    }
}
