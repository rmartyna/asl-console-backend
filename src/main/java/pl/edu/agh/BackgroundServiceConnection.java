package pl.edu.agh;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This software may be modified and distributed under the terms
 *  of the BSD license.  See the LICENSE.txt file for details.
 */

/**
 * Connects to services that run in pull mode
 */
public class BackgroundServiceConnection implements InitializingBean, Runnable{

    private ServerSocket serverSocket;

    private ServiceDAO serviceDAO;

    private Integer serverPort;

    private static final Logger LOGGER = Logger.getLogger(BackgroundServiceConnection.class);

    @Override
    public void afterPropertiesSet() throws Exception {

        serverSocket = new ServerSocket(serverPort);
        new Thread(this).start();
    }

    /**
     * Compares sent hash to the one in database
     */
    public void run() {
        while(true) {
            try {
                LOGGER.info("Accepting connections on port: " + serverPort);
                Socket client = serverSocket.accept();

                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String message = in.readLine();
                DataOutputStream out = new DataOutputStream(client.getOutputStream());

                LOGGER.info("Daemon message: " + message);

                String[] data = message.trim().split("\\|");
                try {
                    Service service = serviceDAO.getById(Integer.parseInt(data[1]));
                    if(!data[0].equalsIgnoreCase(service.getPassword()))
                        throw new IllegalArgumentException("Daemon password: " + data[0] +
                                " does not match server password: " + service.getPassword());
                    out.writeBytes("OK\r\n");
                } catch(Exception e) {
                    LOGGER.error("Could not find server: " + message.trim(), e);
                    out.writeBytes("ERROR\r\n");
                }

                in.close();
                out.close();
                client.close();

            } catch(Exception e) {
                LOGGER.error("Error connecting with server: " + e.getMessage());
            }
        }
    }

    public void setServerPort(Integer serverPort) {
        this.serverPort = serverPort;
    }

    public void setServiceDAO(ServiceDAO serviceDAO) {
        this.serviceDAO = serviceDAO;
    }
}
