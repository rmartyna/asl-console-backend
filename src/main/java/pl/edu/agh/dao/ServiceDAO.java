package pl.edu.agh.dao;

import org.hibernate.Query;
import org.hibernate.Session;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;
import pl.edu.agh.DbConnection;
import pl.edu.agh.beans.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ServiceDAO implements InitializingBean {

    private DbConnection dbConnection;

    private Connection connection;

    @Override
    public void afterPropertiesSet() throws Exception {
        connection = dbConnection.getConnection();
    }

    public List<Service> listAll() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM service");

        ResultSet result = statement.executeQuery();
        List<Service> serviceList = new ArrayList<Service>();
        while(result.next())
            serviceList.add(new Service(result));

        return serviceList;
    }

    public Service getByHost(String host) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM service WHERE host=?");
        statement.setString(1, host);

        ResultSet result = statement.executeQuery();
        result.next();
        return new Service(result);
    }

    public void setDbConnection(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }


}