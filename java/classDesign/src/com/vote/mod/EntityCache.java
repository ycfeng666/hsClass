package com.vote.mod;

public class EntityCache {
	/**
     * ���������
     */
    private  Object datas;
 
    /**
     * ��������ʧЧʱ��,Ϊ0��ʾ����ʧЧ
     */
    private  long timeOut;
 
    /**
     * ���ˢ��ʱ��
     */
    private  long lastRefeshTime;
 
    public EntityCache(Object datas, long timeOut, long lastRefeshTime) {
        this.datas = datas;
        this.timeOut = timeOut;
        this.lastRefeshTime = lastRefeshTime;
    }
    public Object getDatas() {
        return datas;
    }
    public void setDatas(Object datas) {
        this.datas = datas;
    }
    public long getTimeOut() {
        return timeOut;
    }
    public void setTimeOut(long timeOut) {
        this.timeOut = timeOut;
    }
    public long getLastRefeshTime() {
        return lastRefeshTime;
    }
    public void setLastRefeshTime(long lastRefeshTime) {
        this.lastRefeshTime = lastRefeshTime;
    }

}
