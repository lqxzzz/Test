package com.mmal.util;

import com.mmall.pojo.Email;
import org.apache.commons.lang3.StringUtils;

import javax.mail.MessagingException;
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class SystemEmailUtils {
    private static Session session = null;
    private static Object lock = new Object();

    public static boolean sendMail(Email mail) {
        if (mail == null) {
            return false;
        }

        try {
            MimeMessage mimeMessage = getMimeMessage(mail);
            Transport.send(mimeMessage);
            return true;
        } catch (MessagingException e) {
//            DevLog.error("Exception happens when send mail ", e);
            return false;
        }
    }

    private static MimeMessage getMimeMessage(Email mail) throws MessagingException {
        MimeMessage mimeMessage = new MimeMessage(getDefaultSession());

        if (StringUtils.isNotEmpty(mail.getMailFrom())) {
            mimeMessage.setFrom(new InternetAddress(mail.getMailFrom()));
        }

        if (StringUtils.isNotEmpty(mail.getMailTo())) {
            for (String mailTo : StringUtils.split(mail.getMailTo(), ';')) {
                mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));
            }
        }

        if (StringUtils.isNotEmpty(mail.getMailCC())) {
            for (String cc : StringUtils.split(mail.getMailCC(), ';')) {
                mimeMessage.addRecipient(Message.RecipientType.CC, new InternetAddress(cc));
            }
        }

        if (StringUtils.isNotEmpty(mail.getSubject())) {
            mimeMessage.setSubject(mail.getSubject(), "GBK");
        }

        if (StringUtils.isNotEmpty(mail.getContent())) {
            mimeMessage.setContent(mail.getContent(), "text/html;charset=GBK");
        }

        return mimeMessage;
    }

    private static Session getDefaultSession() throws MessagingException {
        if (session == null) {
            synchronized (lock) {
                if (session == null) {
                    Properties properties = new Properties();
                    ResourceBundle bundle = PropertyResourceBundle.getBundle("context-emall");
                    String mailServer = bundle.getString("MailServerName");
                    if (StringUtils.isEmpty(mailServer)) {
                        throw new MessagingException("Mail Server not configured");
                    }
                    properties.setProperty("mail.smtp.host", mailServer);
/*String senderName = bundle.getString("MailSenderName");
if (StringUtils.isNotEmpty(senderName)) {

}
String sendPassword = bundle.getString("MailSenderPassword");*/
                    properties.setProperty("mail.smtp.auth", "false");

                    session = Session.getDefaultInstance(properties);
                }
            }
        }
        return session;
    }
}
