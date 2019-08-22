package cn.com.xiaofabo.hca.epainfocollector.cache;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public class LocalCache {

	private static LinkedHashMap<String, Object> localCacheMap = new LinkedHashMap<>();

	public static Object get(String key) {
		return localCacheMap.get(key);
	}

	public static void set(String key, Object obj) {
		localCacheMap.put(key, obj);
	}

	public static void remove(String key) {
		localCacheMap.remove(key);
	}

	public static void push(String key, Object obj) {
		Object o = localCacheMap.get(key);
		if (o == null) {
			LinkedList<Object> list = new LinkedList<>();
			list.add(obj);
			set(key, list);
		} else {
			if (o instanceof List) {
				@SuppressWarnings("unchecked")
				List<Object> olist = (List<Object>) o;
				olist.add(obj);
				set(key, olist);
			}
		}
	}

	public static LinkedHashMap<String, Object> getCacheMap() {
		return localCacheMap;
	}
}
