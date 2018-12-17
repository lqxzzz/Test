package com.mmall.pojo;

import com.mmal.util.SystemEmailUtils;

public class SystemEmailSender extends Thread{
    private boolean running = true;
    private SystemEmailBlockingQueue queue;

    public SystemEmailSender(SystemEmailBlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (running) {
            try {
                Email mail = queue.poll();


                boolean succeed = SystemEmailUtils.sendMail(mail);
                queue.retry(mail,succeed);
            } catch (ThreadDeath e) {
//                DevLog.error("Thread dead in send mail thread", e);
                break;
            }
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    @Override
    public void destroy() {
//        DevLog.info("System mail send thread destroyed");
    }
}
