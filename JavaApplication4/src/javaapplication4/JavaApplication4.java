/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication4;

import redis.clients.jedis.Jedis;

/**
 *
 * @author maxim
 */
public class JavaApplication4 {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        try {
            Jedis server = new Jedis("localhost");
            String pingResult = server.ping();
            System.out.println("ping result " + pingResult);
            server.append("ping", pingResult);
        } catch (Exception e) {
            System.out.println("Error ping: " + e);
        }
    }
}

