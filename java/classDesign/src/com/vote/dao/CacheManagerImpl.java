package com.vote.dao;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

import com.vote.mod.EntityCache;

public class CacheManagerImpl implements ICacheManager {
	private static Map<String, EntityCache> caches = new ConcurrentHashMap<String, EntityCache>();
	/**
     * ���뻺��
     * @param key
     * @param cache
     */
    public void putCache(String key, EntityCache cache) {
        caches.put(key, cache);
    }
 
    /**
     * ���뻺��
     * @param key
     * @param cache
     */
    public void putCache(String key, Object datas, long timeOut) {
        timeOut = timeOut > 0 ? timeOut : 0L;
        putCache(key, new EntityCache(datas, timeOut, System.currentTimeMillis()));
    }
 
    /**
     * ��ȡ��Ӧ����
     * @param key
     * @return
     */
    public EntityCache getCacheByKey(String key) {
        if (this.isContains(key)) {
            return caches.get(key);
        }
        return null;
    }
 
    /**
     * ��ȡ��Ӧ����
     * @param key
     * @return
     */
    public Object getCacheDataByKey(String key) {
        if (this.isContains(key)) {
            return caches.get(key).getDatas();
        }
        return null;
    }
 
    /**
     * ��ȡ���л���
     * @param key
     * @return
     */
    public Map<String, EntityCache> getCacheAll() {
        return caches;
    }
 
    /**
     * �ж��Ƿ��ڻ�����
     * @param key
     * @return
     */
    public boolean isContains(String key) {
        return caches.containsKey(key);
    }
 
    /**
     * ������л���
     */
    public void clearAll() {
        caches.clear();
    }
 
    /**
     * �����Ӧ����
     * @param key
     */
    public void clearByKey(String key) {
        if (this.isContains(key)) {
            caches.remove(key);
        }
    }
 
    /**
     * �����Ƿ�ʱʧЧ
     * @param key
     * @return
     */
    public boolean isTimeOut(String key) {
        if (!caches.containsKey(key)) {
            return true;
        }
        EntityCache cache = caches.get(key);
        long timeOut = cache.getTimeOut();
        long lastRefreshTime = cache.getLastRefeshTime();
        if (timeOut == 0 || System.currentTimeMillis() - lastRefreshTime >= timeOut) {
            return true;
        }
        return false;
    }
 
    /**
     * ��ȡ����key
     * @return
     */
    public Set<String>  getAllKeys() {
        return caches.keySet();
    }

}
