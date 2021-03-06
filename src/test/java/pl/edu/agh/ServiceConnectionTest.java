package pl.edu.agh;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.edu.agh.beans.Service;

/**
 * This software may be modified and distributed under the terms
 *  of the BSD license.  See the LICENSE.txt file for details.
 */

public class ServiceConnectionTest {

    private static ApplicationContext applicationContext;

    private static ServiceConnection serviceConnection;

    @BeforeClass
    public static void init() {
        applicationContext = new ClassPathXmlApplicationContext("asl-console-backend-test-application-context.xml");
        serviceConnection = applicationContext.getBean(ServiceConnection.class);
    }

    @Test
    public void testSendConfigurationValid() throws Exception {
        while (true){}
    }

}
