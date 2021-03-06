/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cache.redis;

import cache.base.interfaces.Cache;
import cache.base.interfaces.CacheSetter;
import cache.partitions.CacheSetterSplitter;
import cache.partitions.CacheSetterSplitterRedisStringData;
import cache.serialize.DataSerializerJson;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
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
         instance.setData(new Long(id).toString(), data);
    }

    @Test
    public void testSetData2() {
        System.out.println("setData");
        Date date = new Date();
        long id = date.getTime();
        RedisTestData data = new RedisTestData();
        data.intData = id;
        data.dateData = date;

//        Cache.Key key = new Cache.Key();
//        key.outerKey = "testSetData:2:"+id;
//        key.innerKeys = new String[]{"date", "int"};
        CacheSetter<RedisTestData> instance = new RedisCache<>(
                "localhost", 6379,
                new CacheSetterSplitterRedisTestData(),
                null,
                new DataSerializerJson()
        );
        instance.setData(Long.toString(id), data);
    }

    @Test
    public void testSetData3() {
        System.out.println("setData");

        Date date = new Date();
        long id = date.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String data = sdf.format(date);

        CacheSetter<String> instance = new RedisCache<>(
                "localhost", 6379,
                new CacheSetterSplitterRedisStringData("string:"),
                null,
                new DataSerializerJson()
        );
        instance.setData(Long.toString(id), data);
    }

    public class RedisTestData {

        public Long intData;
        public Date dateData;

    }

    public class CacheSetterSplitterSimpleObj implements CacheSetterSplitter<Object> {

        @Override
        public Cache.KeyValues<Object> split(String id, Object data) {
            Cache.KeyValues<Object> result = new Cache.KeyValues<>();
            result.outerKey = "SimpleObj:"+id;
            result.values = (Object[]) Array.newInstance(Object.class, 1);
            result.values[0] = data;
            return result;
        }

    }

    public class CacheSetterSplitterRedisTestData implements CacheSetterSplitter<RedisTestData> {

        @Override
        public Cache.KeyValues<RedisTestData> split(String id, RedisTestData data) {
            Cache.KeyValues<RedisTestData> result = new Cache.KeyValues<>();
            result.outerKey = "RedisTestData:"+id;
            result.innerKeys = new String[]{"date", "int"};
            result.values = (RedisTestData[]) Array.newInstance(RedisTestData.class, 2);
            int iDate = Arrays.binarySearch(result.innerKeys, "date");
            result.values[iDate] = new RedisTestData();
            result.values[iDate].dateData = data.dateData;
            int iInt = Arrays.binarySearch(result.innerKeys, "int");
            result.values[iInt] = new RedisTestData();
            result.values[iInt].intData = data.intData;
            return result;
        }

    }

}
