package pl.edu.agh.dao;

import org.springframework.beans.factory.InitializingBean;
import pl.edu.agh.DbConnection;
import pl.edu.agh.beans.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DiskDAO implements InitializingBean {

    private DbConnection dbConnection;

    private Connection connection;

    @Override
    public void afterPropertiesSet() throws Exception {
        connection = dbConnection.getConnection();
    }

    public List<Disk> listAll() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM disk");

        ResultSet result = statement.executeQuery();
        List<Disk> diskList = new ArrayList<Disk>();
        while(result.next())
            diskList.add(new Disk(result));

        for(Disk disk : diskList) {
            disk.setDiskUsageList(getUsageListByDiskId((int) disk.getId()));
            disk.setPartitionList(getPartitionListByDiskId((int) disk.getId()));
        }

        return diskList;
    }

    public Disk getByServiceId(int serviceId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM disk where service_id=?");
        statement.setInt(1, serviceId);

        ResultSet result = statement.executeQuery();
        result.next();
        Disk disk = new Disk(result);

        disk.setDiskUsageList(getUsageListByDiskId((int) disk.getId()));
        disk.setPartitionList(getPartitionListByDiskId((int) disk.getId()));

        return disk;

    }

    public List<DiskUsage> getUsageListByDiskId(int diskId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM disk_usage where disk_id=?");
        statement.setInt(1, diskId);

        ResultSet result = statement.executeQuery();
        List<DiskUsage> diskUsageList = new ArrayList<DiskUsage>();
        while(result.next())
            diskUsageList.add(new DiskUsage(result));

        return diskUsageList;
    }

    public List<Partition> getPartitionListByDiskId(int diskId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM partition where disk_id=?");
        statement.setInt(1, diskId);

        ResultSet result = statement.executeQuery();
        List<Partition> partitionList = new ArrayList<Partition>();
        while(result.next())
            partitionList.add(new Partition(result));

        return partitionList;
    }

    public void setDbConnection(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }


}
