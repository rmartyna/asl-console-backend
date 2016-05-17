package pl.edu.agh;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;

import javax.sql.DataSource;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DbConnection implements InitializingBean {

    private Connection connection;

    private DataSource dataSource;

    private String host;

    private Integer port;

    private static final Logger LOGGER = Logger.getLogger(DbConnection.class);

    @Override
    public void afterPropertiesSet() throws Exception {
        if(dataSource == null)
            throw new IllegalArgumentException("Data source property cannot be null");

        if(host == null)
            throw new IllegalArgumentException("Host property cannot be null");

        if(port == null)
            throw new IllegalArgumentException("Port property cannot be null");

        connection = dataSource.getConnection();
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Connection getConnection() {
        return connection;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(Integer port) {
        this.port = port;
    }
}
