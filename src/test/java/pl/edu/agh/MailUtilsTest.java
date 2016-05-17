package pl.edu.agh;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.net.Socket;
import java.util.AbstractCollection;

import static org.junit.Assert.assertTrue;

public class MailUtilsTest {

    private static ApplicationContext applicationContext;

    private static MailUtils mailUtils;

    @BeforeClass
    public static void init() {
        applicationContext = new ClassPathXmlApplicationContext("asl-console-backend-test-application-context.xml");
        mailUtils = applicationContext.getBean(MailUtils.class);
    }

    @Test
    public void sendMail() {
        String from = mailUtils.getUsername();
        String to = mailUtils.getUsername();
        String subject = "Test subject";
        String content = "Test content";

        mailUtils.sendMail(from, to, subject, content);
    }

}
