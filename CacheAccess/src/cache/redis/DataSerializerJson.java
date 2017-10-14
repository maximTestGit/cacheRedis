/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache.redis;

import com.google.gson.Gson;


public class DataSerializerJson implements DataSerializer {

    @Override
    public <TPersist> String serialize(TPersist data) {
        Gson gson = new Gson();
        String result = gson.toJson(data);
        return result;
    }
    
}
