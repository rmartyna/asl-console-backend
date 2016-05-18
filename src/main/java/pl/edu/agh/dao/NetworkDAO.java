package pl.edu.agh.dao;

import org.springframework.beans.factory.InitializingBean;
import pl.edu.agh.DbConnection;
import pl.edu.agh.beans.Network;
import pl.edu.agh.beans.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NetworkDAO implements InitializingBean {

    private DbConnection dbConnection;

    private Connection connection;

    @Override
    public void afterPropertiesSet() throws Exception {
        connection = dbConnection.getConnection();
    }

    public List<Network> listAll() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM network_usage");

        ResultSet result = statement.executeQuery();
        List<Network> networkList = new ArrayList<Network>();
        while(result.next())
            networkList.add(new Network(result));

        return networkList;
    }

    public List<Network> getByServiceId(int serviceId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM network_usage where service_id=?");
        statement.setInt(1, serviceId);

        ResultSet result = statement.executeQuery();
        List<Network> networkList = new ArrayList<Network>();
        while(result.next())
            networkList.add(new Network(result));

        return networkList;

    }

    public Network getNewestByServiceId(int serviceId) throws SQLException {
        List<Network> networkList = getByServiceId(serviceId);
        return networkList.get(networkList.size() - 1);
    }

    public void setDbConnection(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }


}
