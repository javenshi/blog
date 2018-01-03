package com.centling.redis;


import java.util.List;
import java.util.Map;
import java.util.Set;

public class RedisTest {
    public static void main(String[] args) {
        new RedisClient().setHashComments("abcd","a","a");
        new RedisClient().setHashComments("abcd","b","b");
        new RedisClient().setHashComments("abcd","e","e");
        new RedisClient().setHashComments("abcd","c","c");
        new RedisClient().setHashComments("abcd","d","d");
        Map map= new RedisClient().getCommentsByBlogId("abcd");
      Set<String> set= (Set<String>) map.get("key");
      List<String> list= (List<String>) map.get("value");
      for (String i:set){
          System.out.println("key"+i);
      }
      for (String i:list){
          System.out.println(i);
      }
    }
}
