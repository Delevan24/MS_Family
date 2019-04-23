package com.bjsxt.base.coll013;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class UseConcurrentMap {

	public static void main(String[] args) {
		ConcurrentHashMap<String, Object> chm = new ConcurrentHashMap<String, Object>();
		chm.put("k1", "v1");
		chm.put("k2", "v2");
		chm.put("k3", "v3");
		chm.putIfAbsent("k4", "vvvv");
		//System.out.println(chm.get("k2"));
		//System.out.println(chm.size());
		
		for(Map.Entry<String, Object> me : chm.entrySet()){
			System.out.println("key:" + me.getKey() + ",value:" + me.getValue());
		}
		
		//分成16个段，也就是最高支持16个线程并发修改操作。每个段是一个小的hash table，他们有自己的锁。只要多个修改发生在不同的段上，他们就可以并发进行。
		//是一种优化的数据结构，通过减小锁的粒度降低锁竞争。并且代码中大多共享变量使用volatile关键字，目的是第一时间获取修改的内容，性能非常好。
		
		HashMap<String,Object> hashmap = new HashMap<String,Object>();
		//hash map 底层数据结构是哈希桶数组
		
	}
}
