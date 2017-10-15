/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache.serialize;

import com.google.gson.Gson;


public class DataSerializerJson implements DataSerializer<Object> {

    @Override
    public String serialize(Object data) {
        Gson gson = new Gson();
        String result = gson.toJson(data);
        return result;
    }
   
}
