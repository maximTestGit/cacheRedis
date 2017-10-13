/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache.redis;

import redis.clients.jedis.Jedis;
/**
 *
 * @author maxim
 */
public class RedisCacheBase {
    
    private Jedis server;
    protected final Jedis getServer () {
        return this.server;
    }
    protected final void setServer (Jedis newServer) {
        this.server = newServer;
    }
    public String serverInfo() {
        String result = this.server.info();
        return result;
    }
    
    public RedisCacheBase(String serverName, int port) {
        Jedis srv = new Jedis(serverName, port);
        this.setServer(srv);
    }

}
