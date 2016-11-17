package pl.edu.agh;

import org.apache.log4j.Logger;
import org.apache.log4j.or.ThreadGroupRenderer;
import org.springframework.beans.factory.InitializingBean;
import pl.edu.agh.beans.ConsoleConfiguration;
import pl.edu.agh.beans.Service;
import pl.edu.agh.beans.ServiceConfiguration;
import pl.edu.agh.dao.ConsoleConfigurationDAO;
import pl.edu.agh.dao.ServiceConfigurationDAO;
import pl.edu.agh.dao.ServiceDAO;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.security.MessageDigest;
import java.sql.SQLException;
import java.util.*;

/**
 * This software may be modified and distributed under the terms
 *  of the BSD license.  See the LICENSE.txt file for details.
 */

/**
 * Connects to services that run in push mode
 */
public class ServiceConnection implements InitializingBean, Runnable {

    private ServiceDAO serviceDAO;

    private List<Service> serviceList;

    /**
     * Maps from Service to its Configuration
     */
    private Map<Service, ServiceConfiguration> configurationMap = new HashMap<Service, ServiceConfiguration>();

    /**
     * Maps from service to the last date it was communicated with
     */
    private Map<Service, Date> dateMap = new HashMap<Service, Date>();

    private ServiceConfigurationDAO serviceConfigurationDAO;

    private static final Logger LOGGER = Logger.getLogger(ServiceConnection.class);

    @Override
    public void afterPropertiesSet() throws Exception {

        new Thread(this).start();
    }

    /**
     * Loads severs and their configuration, and connects with them
     */
    public void run() {
        while(true) {
            try {
                serviceList = serviceDAO.listAll();

                LOGGER.info("Service list: " + serviceList);
                for (Service service : serviceList) {
                    try {
                        configurationMap.put(service, serviceConfigurationDAO.getByServiceId((int) service.getId()));
                    } catch (Exception e) {
                        LOGGER.error("No service configuration for service: " + service);
                    }
                }


                for(Service service : configurationMap.keySet()) {
                    try {
                        if(dateMap.get(service) == null ||
                                (dateMap.get(service) != null &&
                                        timeToConnect(dateMap.get(service), configurationMap.get(service).getPollRate()))) {
                            dateMap.put(service, new Date());
                            connect(service);
                        }
                    } catch(Exception e) {
                        if(configurationMap.get(service).equals("push"))
                            LOGGER.error("Error connecting with daemon", e);
                    }
                }

                Thread.sleep(1000);

            } catch(Exception e) {
                LOGGER.error("Error running service connection", e);
            }
        }

    }

    /**
     * Checks if we should connect with server again
     */
    public boolean timeToConnect(Date lastConnect, int pollRate) {
        if(new Date().getTime() - lastConnect.getTime() > pollRate)
            return true;
        return false;
    }

    /**
     * 1. Sends data to service: console password | service password | service id
     */
    public void connect(Service service) {
        try {
            LOGGER.info("Connecting to service: " + service);
            Socket socket = new Socket(service.getHost(), service.getPort());
            socket.setSoTimeout(300);
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.writeBytes(service.getPassword() + "|" + service.getId() + "\r\n");
            String response = in.readLine();

            in.close();
            out.close();
            socket.close();

            LOGGER.info("Service response: " + response);

        } catch(Exception e) {
            LOGGER.error("Error connecting with daemon: " + e.getMessage());
        }
    }


    public void setServiceDAO(ServiceDAO serviceDAO) {
        this.serviceDAO = serviceDAO;
    }

    public void setServiceConfigurationDAO(ServiceConfigurationDAO serviceConfigurationDAO) {
        this.serviceConfigurationDAO = serviceConfigurationDAO;
    }
}
