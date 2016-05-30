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

public class BackgroundServiceConnection implements InitializingBean, Runnable, DisposableBean {

    private ServerSocket serverSocket;

    private Integer serverPort;

    private Thread thread;

    private static final Logger LOGGER = Logger.getLogger(BackgroundServiceConnection.class);

    @Override
    public void afterPropertiesSet() throws Exception {

        serverSocket = new ServerSocket(serverPort);
        thread = new Thread(this);
    }

    @Override
    public void destroy() throws Exception {
        thread.interrupt();
        serverSocket.close();
    }

    public void run() {
        while(true) {
            try {
                LOGGER.info("Accepting connections on pot: " + serverPort);
                Socket client = serverSocket.accept();

                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                String message = in.readLine();
                DataOutputStream out = new DataOutputStream(client.getOutputStream());

                LOGGER.info("Daemon message: " + message);
                if(message.equalsIgnoreCase("data")) {
                    out.writeBytes("OK\r\n");
                }
                else if(message.equalsIgnoreCase("conf")) {
                    out.writeBytes("OK\r\n");
                }
                else {
                    LOGGER.error("Invalid message");
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
}
