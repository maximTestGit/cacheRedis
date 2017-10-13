/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache.setter.redis;

import cache.setter.CacheSetterFormatter;


public class CacheSetterFormatterString implements CacheSetterFormatter<String> {

    @Override
    public String Format(String data) {
        return data;
    }
    
}
