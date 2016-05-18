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

    public void setServiceDAO(ServiceDAO serviceDAO) {
        this.serviceDAO = serviceDAO;
    }
}
