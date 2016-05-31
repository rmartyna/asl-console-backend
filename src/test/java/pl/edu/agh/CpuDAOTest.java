package pl.edu.agh;

import org.apache.log4j.Logger;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.edu.agh.beans.Cpu;
import pl.edu.agh.dao.CpuDAO;

import java.util.Arrays;
import java.util.List;

/**
 * This software may be modified and distributed under the terms
 *  of the BSD license.  See the LICENSE.txt file for details.
 */

public class CpuDAOTest {

    private static ApplicationContext applicationContext;

    private static CpuDAO cpuDAO;

    private static final Logger LOGGER = Logger.getLogger(CpuDAOTest.class);

    @BeforeClass
    public static void init() {
        applicationContext = new ClassPathXmlApplicationContext("asl-console-backend-test-application-context.xml");
        cpuDAO = applicationContext.getBean(CpuDAO.class);
    }

    @Test
    public void testCpuDAOListAll() throws Exception {
        List<Cpu> cpuList = cpuDAO.listAll();

        LOGGER.info(Arrays.toString(cpuList.toArray()));
    }

}
