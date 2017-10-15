/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache.redis;

import cache.base.interfaces.Cache;
import cache.base.interfaces.CacheSetter;
import cache.partitions.CacheGetterMergerSimplObj;
import cache.partitions.CacheSetterSplitterSimpleObj;
import cache.serialize.DataSerializerJson;
import java.util.Date;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author maxim
 */
public class RedisCacheTest {

    public RedisCacheTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getServer method, of class RedisCache.
     */
//    @Test
//    public void testGetServer() {
//        System.out.println("getServer");
//        RedisCache instance = null;
//        Jedis expResult = null;
//        Jedis result = instance.getServer();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setServer method, of class RedisCache.
//     */
//    @Test
//    public void testSetServer() {
//        System.out.println("setServer");
//        Jedis newServer = null;
//        RedisCache instance = null;
//        instance.setServer(newServer);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of serverInfo method, of class RedisCache.
//     */
//    @Test
//    public void testServerInfo() {
//        System.out.println("serverInfo");
//        RedisCache instance = null;
//        String expResult = "";
//        String result = instance.serverInfo();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of provideData method, of class RedisCache.
//     */
//    @Test
//    public void testProvideData() {
//        System.out.println("provideData");
//        Cache.Key key = null;
//        RedisCache instance = null;
//        Cache.KeyValues expResult = null;
//        Cache.KeyValues result = instance.provideData(key);
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of persistData method, of class RedisCache.
//     */
//    @Test
//    public void testPersistData() {
//        System.out.println("persistData");
//        RedisCache instance = null;
//        instance.persistData(null);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of getSerializer method, of class RedisCache.
//     */
//    @Test
//    public void testGetSerializer() {
//        System.out.println("getSerializer");
//        RedisCache instance = null;
//        DataSerializer expResult = null;
//        DataSerializer result = instance.getSerializer();
//        assertEquals(expResult, result);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
//
//    /**
//     * Test of setSerializer method, of class RedisCache.
//     */
//    @Test
//    public void testSetSerializer() {
//        System.out.println("setSerializer");
//        DataSerializer serializer = null;
//        RedisCache instance = null;
//        instance.setSerializer(serializer);
//        // TODO review the generated test code and remove the default call to fail.
//        fail("The test case is a prototype.");
//    }
    @Test
    public void testSetData() {
        System.out.println("setData");
        Cache.Key key = new Cache.Key();
        key.outerKey = "testSetData";

        Date date = new Date();
        long id = date.getTime();
        RedisData data = new RedisData();
        data.intData = id;
        data.dateData = date;

        CacheSetter<Object> instance = new RedisCache<>(
                "localhost", 6379,
                new CacheSetterSplitterSimpleObj(),
                new CacheGetterMergerSimplObj(),
                new DataSerializerJson()
        );
        instance.setData(key, data);
        // TODO review the generated test code and remove the default call to fail.
        //fail("The test case is a prototype.");
    }

    public class RedisData {

        public Long intData;
        public Date dateData;

    }
}
