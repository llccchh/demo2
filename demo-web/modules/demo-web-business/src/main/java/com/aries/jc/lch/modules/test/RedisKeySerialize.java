package com.aries.jc.lch.modules.test;

import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import java.util.Scanner;

/**
 * @author lichanghao6
 */
public class RedisKeySerialize<K,V> {

    static {
        System.out.println("----------------REDIS序列化KEY工具----------------");
    }

    public String scan(){
        System.out.println("\n"+"* 请输入Key：");
        return new Scanner(System.in).next();
    }

    /**
     * @description 处理key
     */
    public String handleKey(K key){
        String returnStr = "";
        final byte[] rawKey = rawKey(key);
        returnStr = new String(rawKey);
        return returnStr;
    }

    /**
     * @description 构造一个序列化器
     */
    RedisSerializer keySerializer() {
        return new JdkSerializationRedisSerializer(this.getClass().getClassLoader());
    }

    /**
     * @description 序列化key
     */
    public byte[] rawKey(K key){
        if (keySerializer() == null && key instanceof byte[]) {
            return (byte[]) key;
        }
        return keySerializer().serialize(key);
    }

    public static void main(String[] args) {
        RedisKeySerialize keySerialize = new RedisKeySerialize();
        while (true) {
            String key = keySerialize.scan();
            String result = keySerialize.handleKey(key);
            System.out.println("* 存于Redis的key为：");
            System.out.println(result);
        }
    }

}
