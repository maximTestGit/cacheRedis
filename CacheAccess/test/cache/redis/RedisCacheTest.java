/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache.redis;

import cache.base.interfaces.Cache;
import cache.base.interfaces.CacheSetter;
import cache.partitions.CacheSetterSplitter;
import cache.serialize.DataSerializerJson;
import java.lang.reflect.Array;
import java.util.Arrays;
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
    public void testSetData1() {
        System.out.println("setData");
        Cache.Key key = new Cache.Key();
        key.outerKey = "testSetData:1";

        Date date = new Date();
        long id = date.getTime();
        RedisTestData data = new RedisTestData();
        data.intData = id;
        data.dateData = date;

        CacheSetter<Object> instance = new RedisCache<>(
                "localhost", 6379,
                new CacheSetterSplitterSimpleObj(),
                null,
                new DataSerializerJson()
        );
        instance.setData(key, data);
    }

    @Test
    public void testSetData2() {
        System.out.println("setData");
        Cache.Key key = new Cache.Key();
        key.outerKey = "testSetData:2";
        key.innerKeys = new String[]{"date", "int"};

        Date date = new Date();
        long id = date.getTime();
        RedisTestData data = new RedisTestData();
        data.intData = id;
        data.dateData = date;

        CacheSetter<RedisTestData> instance = new RedisCache<>(
                "localhost", 6379,
                new CacheSetterSplitterRedisTestData(),
                null,
                new DataSerializerJson()
        );
        instance.setData(key, data);
    }

    public class RedisTestData {

        public Long intData;
        public Date dateData;

    }

    public class CacheSetterSplitterSimpleObj implements CacheSetterSplitter<Object> {

        @Override
        public Cache.KeyValues<Object> split(Cache.Key key, Object data) {
            Cache.KeyValues<Object> result = new Cache.KeyValues<>(key);
            result.values = (Object[]) Array.newInstance(Object.class, 1);
            result.values[0] = data;
            return result;
        }

    }

    public class CacheSetterSplitterRedisTestData implements CacheSetterSplitter<RedisTestData> {

        @Override
        public Cache.KeyValues<RedisTestData> split(Cache.Key key, RedisTestData data) {
            Cache.KeyValues<RedisTestData> result = new Cache.KeyValues<>(key);
            result.values = (RedisTestData[]) Array.newInstance(RedisTestData.class, 2);
            int iDate = Arrays.binarySearch(key.innerKeys, "date");
            result.values[iDate] = new RedisTestData();
            result.values[iDate].dateData = data.dateData;
            int iInt = Arrays.binarySearch(key.innerKeys, "int");
            result.values[iInt] = new RedisTestData();
            result.values[iInt].intData = data.intData;
            return result;
        }

    }

    public class CacheSetterSplitterRedisTestStringData implements CacheSetterSplitter<String> {

        @Override
        public Cache.KeyValues<String> split(Cache.Key key, String data) {
            Cache.KeyValues<String> result = new Cache.KeyValues<>(key);
            result.values = (String[]) Array.newInstance(String.class, 1);
            result.values[0] = data;
            return result;
        }
    }

}
