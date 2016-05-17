package pl.edu.agh;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import pl.edu.agh.beans.Service;
import pl.edu.agh.dao.ServiceDAO;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.sql.SQLException;
import java.util.List;

public class ServiceConnection implements InitializingBean {

    private ServiceDAO serviceDAO;

    private static final Logger LOGGER = Logger.getLogger(ServiceConnection.class);

    @Override
    public void afterPropertiesSet() throws Exception {

        if(serviceDAO == null)
            throw new IllegalArgumentException("Service dao cannot be null");
    }

    public void sendConfiguration(String serviceHost, String configuration) throws IOException, SQLException {
        LOGGER.info("Sending configuration '" + configuration + "' to host '" + serviceHost + "'");

        List<Service> serviceList = serviceDAO.listAll();
        LOGGER.info(serviceList.get(1));

        Service service = serviceDAO.getByHost(serviceHost);

        Socket socket = null;
        try {
            socket = new Socket(service.getHost(), service.getPort());

            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            out.writeBytes(configuration + "\r\n");

            String result = in.readLine();
            LOGGER.info("Service response: " + result);

            in.close();
            out.close();
        } catch(Exception e) {
            throw new IOException("Could not send configuration", e);
        } finally {
            socket.close();
        }

    }

    public void setServiceDAO(ServiceDAO serviceDAO) {
        this.serviceDAO = serviceDAO;
    }
}
