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
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ServiceConnection implements InitializingBean, Runnable {

    private ServiceDAO serviceDAO;

    private List<Service> serviceList;

    private Map<Service, ServiceConfiguration> configurationMap = new HashMap<Service, ServiceConfiguration>();

    private Map<Service, Date> dateMap = new HashMap<Service, Date>();

    private ServiceConfigurationDAO serviceConfigurationDAO;

    private static final Logger LOGGER = Logger.getLogger(ServiceConnection.class);

    @Override
    public void afterPropertiesSet() throws Exception {

        new Thread(this).start();
    }

    public void run() {
        while(true) {
            try {
                serviceList = serviceDAO.listAll();

                LOGGER.info("Service list: " + serviceList);
                for (Service service : serviceList) {
                    try {
                        configurationMap.put(service, serviceConfigurationDAO.getByServiceId((int) service.getId()));

                    } catch (Exception e) {
                        LOGGER.error("Error creating maps for service: " + service, e);
                    }
                }

                for(Service service : serviceList) {
                    try {
                        if(configurationMap.get(service) == null) {
                            dateMap.put(service, new Date());
                            connect(service);
                        }
                        else {
                            if(configurationMap.get(service).getMode().equals("pull"))
                                continue;
                            if(dateMap.get(service) == null ||
                                    (dateMap.get(service) != null &&
                                            timeToConnect(dateMap.get(service), configurationMap.get(service).getPollRate()))) {
                                dateMap.put(service, new Date());
                                connect(service);
                            }
                        }

                    } catch(Exception e) {
                        LOGGER.error("Error connecting with daemon", e);
                    }
                }
                Thread.sleep(1000);

            } catch(Exception e) {
                LOGGER.error("Error running service connection", e);
            }
        }

    }

    public boolean timeToConnect(Date lastConnect, int pollRate) {
        if(new Date().getTime() - lastConnect.getTime() > pollRate)
            return true;
        return false;
    }

    public void connect(Service service) {
        try {
            LOGGER.info("Connecting to service: " + service);
            Socket socket = new Socket(service.getHost(), service.getPort());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.writeBytes("data\r\n");
            String result = in.readLine();
            LOGGER.info("Service response: " + result);

            if(!result.equalsIgnoreCase("OK")) {
                LOGGER.error("Invalid response");
                return;
            }

            in.close();
            out.close();
            socket.close();

            socket = new Socket(service.getHost(), service.getPort());

            out = new DataOutputStream(socket.getOutputStream());
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.writeBytes("conf\r\n");
            result = in.readLine();
            LOGGER.info("Service response: " + result);

            if(!result.equalsIgnoreCase("OK")) {
                LOGGER.error("Invalid response");
                return;
            }

            in.close();
            out.close();
            socket.close();

        } catch(Exception e) {
            LOGGER.error("Error connecting with daemon: " + e.getMessage());
        }
    }


    /*
    public void sendConfiguration(String serviceHost, String configuration) throws IOException, SQLException {
        LOGGER.info("Sending configuration '" + configuration + "' to host '" + serviceHost + "'");

        List<Service> serviceList = serviceDAO.listAll();

        Service service = serviceDAO.getByHost(serviceHost);
        LOGGER.info(service.toString());

        Socket socket = null;
        try {
            socket = new Socket("127.0.0.1", service.getPort());
            LOGGER.info("Socket: " + socket);

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.writeBytes(configuration + "\r\n");

            String result = in.readLine();
            LOGGER.info("Service response: " + result);

            in.close();
            out.close();
        } catch(Exception e) {
            LOGGER.error("Could not send configuration", e);
            throw new IOException("Could not send configuration", e);
        } finally {
            try {
                socket.close();
            } catch(Exception e) {
            }
        }

    }

    */

    public void setServiceDAO(ServiceDAO serviceDAO) {
        this.serviceDAO = serviceDAO;
    }


    public void setServiceConfigurationDAO(ServiceConfigurationDAO serviceConfigurationDAO) {
        this.serviceConfigurationDAO = serviceConfigurationDAO;
    }
}
