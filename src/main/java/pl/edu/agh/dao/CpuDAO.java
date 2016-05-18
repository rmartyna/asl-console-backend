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

public class CpuDAO implements InitializingBean {

    private DbConnection dbConnection;

    private Connection connection;

    @Override
    public void afterPropertiesSet() throws Exception {
        connection = dbConnection.getConnection();
    }

    public List<Cpu> listAll() throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM cpu");

        ResultSet result = statement.executeQuery();
        List<Cpu> cpuList = new ArrayList<Cpu>();
        while(result.next())
            cpuList.add(new Cpu(result));

        for(Cpu cpu : cpuList) {
            cpu.setCpuFanList(getFanListByCpuId((int) cpu.getId()));
            cpu.setCpuTempList(getTempListByCpuId((int) cpu.getId()));
            cpu.setCpuUsageList(getUsageListByCpuId((int) cpu.getId()));
        }

        return cpuList;
    }

    public Cpu getByServiceId(int serviceId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM cpu where service_id=?");
        statement.setInt(1, serviceId);

        ResultSet result = statement.executeQuery();
        result.next();
        Cpu cpu = new Cpu(result);

        cpu.setCpuFanList(getFanListByCpuId((int) cpu.getId()));
        cpu.setCpuTempList(getTempListByCpuId((int) cpu.getId()));
        cpu.setCpuUsageList(getUsageListByCpuId((int) cpu.getId()));

        return cpu;

    }

    public List<CpuFan> getFanListByCpuId(int cpuId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM cpu_fan where cpu_id=?");
        statement.setInt(1, cpuId);

        ResultSet result = statement.executeQuery();
        List<CpuFan> cpuFanList = new ArrayList<CpuFan>();
        while(result.next())
            cpuFanList.add(new CpuFan(result));

        return cpuFanList;
    }

    public List<CpuTemp> getTempListByCpuId(int cpuId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM cpu_temp where cpu_id=?");
        statement.setInt(1, cpuId);

        ResultSet result = statement.executeQuery();
        List<CpuTemp> cpuTempList = new ArrayList<CpuTemp>();
        while(result.next())
            cpuTempList.add(new CpuTemp(result));

        return cpuTempList;
    }

    public List<CpuUsage> getUsageListByCpuId(int cpuId) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("SELECT * FROM cpu_usage where cpu_id=?");
        statement.setInt(1, cpuId);

        ResultSet result = statement.executeQuery();
        List<CpuUsage> cpuUsageList = new ArrayList<CpuUsage>();
        while(result.next())
            cpuUsageList.add(new CpuUsage(result));

        return cpuUsageList;
    }

    public CpuTemp getNewestTempByServiceId(int serviceId) throws SQLException {
        Cpu cpu = getByServiceId(serviceId);
        return cpu.getCpuTempList().get(cpu.getCpuTempList().size() - 1);
    }

    public CpuUsage getNewestUsageByServiceId(int serviceId) throws SQLException {
        Cpu cpu = getByServiceId(serviceId);
        return cpu.getCpuUsageList().get(cpu.getCpuUsageList().size() - 1);
    }

    public void setDbConnection(DbConnection dbConnection) {
        this.dbConnection = dbConnection;
    }


}
