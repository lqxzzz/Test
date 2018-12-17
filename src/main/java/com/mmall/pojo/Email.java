package com.mmall.pojo;

import java.io.Serializable;
import java.util.Calendar;

public class Email implements Serializable {

    /**
     * 发件人姓名
     */
    private String sender;
    /**
     * 发件人地址
     */
    private String mailFrom;
    /**
     * 收件人地址
     */
    private String mailTo;
    /**
     * 邮件主题
     */
    private String subject;
    /**
     * 邮件内容
     */
    private String content;
    /**
     * 邮件附件
     */
     private String attach;
    /**
     * 邮件发送时间
     */
     private Calendar sendMailTime;
    /**
     * 邮件发送栏目
     */
     private String topic;
    /**
     * 建议内容
     */
     private String advice;
    /**
     * 抄送人
     */
     private String mailCC;

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMailFrom() {
        return mailFrom;
    }

    public void setMailFrom(String mailFrom) {
        this.mailFrom = mailFrom;
    }

    public String getMailTo() {
        return mailTo;
    }

    public void setMailTo(String mailTo) {
        this.mailTo = mailTo;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAttach() {
        return attach;
    }

    public void setAttach(String attach) {
        this.attach = attach;
    }

    public Calendar getSendMailTime() {
        return sendMailTime;
    }

    public void setSendMailTime(Calendar sendMailTime) {
        this.sendMailTime = sendMailTime;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getAdvice() {
        return advice;
    }

    public void setAdvice(String advice) {
        this.advice = advice;
    }

    public String getMailCC() {
        return mailCC;
    }

    public void setMailCC(String mailCC) {
        this.mailCC = mailCC;
    }

    @Override
    public String toString() {
        return "Email{" +
                "sender='" + sender + '\'' +
                ", mailFrom='" + mailFrom + '\'' +
                ", mailTo='" + mailTo + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", attach='" + attach + '\'' +
                ", sendMailTime=" + sendMailTime +
                ", topic='" + topic + '\'' +
                ", advice='" + advice + '\'' +
                ", mailCC='" + mailCC + '\'' +
                '}';
    }
}
