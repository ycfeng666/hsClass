package com.vote.MyHelper;

import java.util.logging.Logger;

import com.vote.dao.CacheManagerImpl;

public class CacheListener {
	Logger logger = Logger.getLogger("cacheLog");
    private CacheManagerImpl cacheManagerImpl;
    public CacheListener(CacheManagerImpl cacheManagerImpl) {
        this.cacheManagerImpl = cacheManagerImpl;
    }
 
    public void startListen() {
        new Thread(){
            public void run() {
                while (true) {
                    for(String key : cacheManagerImpl.getAllKeys()) {
                        if (cacheManagerImpl.isTimeOut(key)) {
                         cacheManagerImpl.clearByKey(key);
                         logger.info(key + "ª∫¥Ê“— ß–ß");
                     }
                    } 
                }
            }  
        }.start();
 
    }
}
