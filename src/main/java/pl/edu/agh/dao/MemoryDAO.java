package pl.edu.agh.dao;

import org.springframework.beans.factory.InitializingBean;
import pl.edu.agh.DbConnection;
import pl.edu.agh.beans.Memory;
import pl.edu.agh.beans.Network;
import pl.edu.agh.beans.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MemoryDAO implements InitializingBean {

    private DbConnection dbConnection;

    private Connection connection;

    @Override
    public void afterPropertiesSet() throws Exception {
        connection = dbConnection.getConnection();
    }

    public List<Memory> listAll() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM memory_usage");

        ResultSet result = statement.executeQuery();
        List<Memory> memoryList = new ArrayList<Memory>();
        while(result.next())
            memoryList.add(new Memory(result));

        return memoryList;
    }

    public List<Memory> getByServiceId(int serviceId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM memory_usage where service_id=?");
        statement.setInt(1, serviceId);

        ResultSet result = statement.executeQuery();
        List<Memory> memoryList = new ArrayList<Memory>();
        while(result.next())
            memoryList.add(new Memory(result));

        return memoryList;

    }

    public void setDbConnection(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }


}
