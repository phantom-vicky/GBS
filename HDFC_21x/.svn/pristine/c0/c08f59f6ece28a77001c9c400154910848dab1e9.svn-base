package com.isl.gbm.rbibond.util;

import com.isl.gbm.common.util.CommonConstants;
import com.isl.gbm.rbibond.dto.SendMailDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.Session;
import jakarta.mail.Transport;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.util.Properties;

/**

 * @return
 * @author kedhar nath
 */



@Component
public class SendMail {

    @Value("${smtp.host.ip}")
    private String ipAddres;

    @Value("${smtp.host.port}")
    private String portNo;

    public String excete(SendMailDTO sendMailDTO) {

        // Mention the Recipient's email address
        String to = sendMailDTO.getToEmailId();

        // Mention the Sender's email address
        String from = sendMailDTO.getFromEmailId();

        // Mention the CC email address
        String cc=sendMailDTO.getCc();
        String[] emailArray = cc.split(";");
        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", ipAddres);
        properties.put("mail.smtp.port", portNo);

        // Get the Session object and pass username and password
        Session session = Session.getInstance(properties);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(from));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

            // Set CC: header field
            for (String emailcc : emailArray) {
                message.addRecipient(Message.RecipientType.CC, new InternetAddress(emailcc));
            }
            // Set Subject: header field
            message.setSubject(sendMailDTO.getSubject());

            // Now set the actual message
            message.setText(sendMailDTO.getBody());

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
            return CommonConstants.FAILURE;
        }

        return CommonConstants.SUCCESS_CONST;
    }

}
