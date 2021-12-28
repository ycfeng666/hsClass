package com.vote.dao;

import java.util.Map;
import java.util.Set;

import com.vote.mod.EntityCache;

public interface ICacheManager {
	/**
     * ���뻺��
     * @param key
     * @param cache
     */
    void putCache(String key, EntityCache cache);
 
    /**
     * ���뻺��
     * @param key
     * @param cache
     */
    void putCache(String key, Object datas, long timeOut);
 
    /**
     * ��ȡ��Ӧ����
     * @param key
     * @return
     */
    EntityCache getCacheByKey(String key);
 
    /**
     * ��ȡ��Ӧ����
     * @param key
     * @return
     */
    Object getCacheDataByKey(String key);
 
    /**
     * ��ȡ���л���
     * @param key
     * @return
     */
    Map<String, EntityCache> getCacheAll();
 
    /**
     * �ж��Ƿ��ڻ�����
     * @param key
     * @return
     */
    boolean isContains(String key);
 
    /**
     * ������л���
     */
    void clearAll();
 
    /**
     * �����Ӧ����
     * @param key
     */
    void clearByKey(String key);
 
    /**
     * �����Ƿ�ʱʧЧ
     * @param key
     * @return
     */
    boolean isTimeOut(String key);
 
    /**
     * ��ȡ����key
     * @return
     */
    Set<String> getAllKeys();
}
