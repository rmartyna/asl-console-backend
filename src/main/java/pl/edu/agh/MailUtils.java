package pl.edu.agh;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;

/**
 * This software may be modified and distributed under the terms
 *  of the BSD license.  See the LICENSE.txt file for details.
 */

/**
 * Contains methods which allow to send mail messages
 */
public class MailUtils {

    private JavaMailSenderImpl mailSender;

    /**
     * Sends simple mail message
     */
    public void sendMail(String from, String to, String subject, String content) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
    }

    public void setMailSender(JavaMailSenderImpl mailSender) {
        this.mailSender = mailSender;
    }

    public String getUsername() {
        return mailSender.getUsername();
    }
}
