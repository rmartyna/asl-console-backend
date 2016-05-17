package pl.edu.agh.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CpuUsage {

    private long id;

    private int cpuId;

    private double user;

    private double system;

    private double iowait;

    private Timestamp date;

    public CpuUsage(ResultSet resultSet) throws SQLException {
        id = resultSet.getInt(1);
        cpuId = resultSet.getInt(2);
        user = resultSet.getDouble(3);
        system = resultSet.getDouble(4);
        iowait = resultSet.getDouble(5);
        date = resultSet.getTimestamp(6);
    }

    @Override
    public String toString() {
        return "CpuUsage{" +
                "id=" + id +
                ", cpuId=" + cpuId +
                ", user=" + user +
                ", system=" + system +
                ", iowait=" + iowait +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CpuUsage)) return false;

        CpuUsage cpuUsage = (CpuUsage) o;

        if (id != cpuUsage.id) return false;
        if (cpuId != cpuUsage.cpuId) return false;
        if (Double.compare(cpuUsage.user, user) != 0) return false;
        if (Double.compare(cpuUsage.system, system) != 0) return false;
        if (Double.compare(cpuUsage.iowait, iowait) != 0) return false;
        return date != null ? date.equals(cpuUsage.date) : cpuUsage.date == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + cpuId;
        temp = Double.doubleToLongBits(user);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(system);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(iowait);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getCpuId() {
        return cpuId;
    }

    public void setCpuId(int cpuId) {
        this.cpuId = cpuId;
    }

    public double getUser() {
        return user;
    }

    public void setUser(double user) {
        this.user = user;
    }

    public double getSystem() {
        return system;
    }

    public void setSystem(double system) {
        this.system = system;
    }

    public double getIowait() {
        return iowait;
    }

    public void setIowait(double iowait) {
        this.iowait = iowait;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
