package com.mmall.pojo;


import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class SystemEmailBlockingQueue {
    private BlockingQueue<Email> queue;
    private Map<Email,AtomicInteger> retryMap;
    private static final int MAX_RETRY_COUNT = 3;
    public SystemEmailBlockingQueue(int maxQueueSize){
        queue = new ArrayBlockingQueue<Email>(maxQueueSize);
        retryMap = new ConcurrentHashMap<Email,AtomicInteger>();
    }
    /**
     * 添加邮件到队列中
     */
    public boolean push(Email email){
        try {
            return queue.offer(email,2,TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            return false;
        }

    }
    /**
     * 将邮件从队列中取出，如果队列中没有邮件则会阻塞
     */
    public Email poll(){
        Email email = null;
        try {
            email = queue.take();
        } catch (InterruptedException e) {
        }
        return email;
    }
    /**
     * 如果发送失败，并且尝试重新发送的次数小于最大重试次数
     */
    public void retry(Email email,boolean sendSucced){
        if(sendSucced){
            //email发送成功，将map中含有这个email清除
            if(retryMap.containsKey(email)){
                retryMap.remove(email);
            }
            return;
        }
        AtomicInteger retryCount = retryMap.get(email);
        if(retryCount==null){
            if(push(email)){
                retryMap.put(email,new AtomicInteger(1));
            }
        }else{
            if(retryCount.incrementAndGet()<MAX_RETRY_COUNT){
                if(!push(email)) {
                    retryMap.remove(email);
                }
            }else{
                retryMap.remove(email);
            }
        }
    }

    public int size(){
        return queue.size();
    }

    public void destroy(){
        queue.clear();
        retryMap.clear();
    }


}
