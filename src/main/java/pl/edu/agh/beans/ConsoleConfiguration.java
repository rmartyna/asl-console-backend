package pl.edu.agh.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class ConsoleConfiguration {

    private long id;

    private int serviceId;

    private double cpuTempLowerBound;

    private double cpuTempUpperBound;

    private double cpuLoadLowerBound;

    private double cpuLoadUpperBound;

    private double bandwidthLowerBound;

    private double bandwidthUpperBound;

    private double ioLowerBound;

    private double ioUpperBound;

    private double ramLowerBound;

    private double ramUpperBound;


    public ConsoleConfiguration() {

    }

    public ConsoleConfiguration(ResultSet resultSet) throws SQLException {
        id = resultSet.getInt(1);
        serviceId = resultSet.getInt(2);
        cpuTempLowerBound = resultSet.getDouble(3);
        cpuTempUpperBound = resultSet.getDouble(4);
        cpuLoadLowerBound = resultSet.getDouble(5);
        cpuLoadUpperBound = resultSet.getDouble(6);
        bandwidthLowerBound = resultSet.getDouble(7);
        bandwidthUpperBound = resultSet.getDouble(8);
        ioLowerBound = resultSet.getDouble(9);
        ioUpperBound = resultSet.getDouble(10);
        ramLowerBound = resultSet.getDouble(11);
        ramUpperBound = resultSet.getDouble(12);
    }

    @Override
    public String toString() {
        return "ConsoleConfiguration{" +
                "id=" + id +
                ", serviceId=" + serviceId +
                ", cpuTempLowerBound=" + cpuTempLowerBound +
                ", cpuTempUpperBound=" + cpuTempUpperBound +
                ", cpuLoadLowerBound=" + cpuLoadLowerBound +
                ", cpuLoadUpperBound=" + cpuLoadUpperBound +
                ", bandwidthLowerBound=" + bandwidthLowerBound +
                ", bandwidthUpperBound=" + bandwidthUpperBound +
                ", ioLowerBound=" + ioLowerBound +
                ", ioUpperBound=" + ioUpperBound +
                ", ramLowerBound=" + ramLowerBound +
                ", ramUpperBound=" + ramUpperBound +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ConsoleConfiguration)) return false;

        ConsoleConfiguration that = (ConsoleConfiguration) o;

        if (id != that.id) return false;
        if (serviceId != that.serviceId) return false;
        if (Double.compare(that.cpuTempLowerBound, cpuTempLowerBound) != 0) return false;
        if (Double.compare(that.cpuTempUpperBound, cpuTempUpperBound) != 0) return false;
        if (Double.compare(that.cpuLoadLowerBound, cpuLoadLowerBound) != 0) return false;
        if (Double.compare(that.cpuLoadUpperBound, cpuLoadUpperBound) != 0) return false;
        if (Double.compare(that.bandwidthLowerBound, bandwidthLowerBound) != 0) return false;
        if (Double.compare(that.bandwidthUpperBound, bandwidthUpperBound) != 0) return false;
        if (Double.compare(that.ioLowerBound, ioLowerBound) != 0) return false;
        if (Double.compare(that.ioUpperBound, ioUpperBound) != 0) return false;
        if (Double.compare(that.ramLowerBound, ramLowerBound) != 0) return false;
        return Double.compare(that.ramUpperBound, ramUpperBound) == 0;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + serviceId;
        temp = Double.doubleToLongBits(cpuTempLowerBound);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(cpuTempUpperBound);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(cpuLoadLowerBound);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(cpuLoadUpperBound);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(bandwidthLowerBound);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(bandwidthUpperBound);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(ioLowerBound);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(ioUpperBound);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(ramLowerBound);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(ramUpperBound);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getServiceId() {
        return serviceId;
    }

    public void setServiceId(int serviceId) {
        this.serviceId = serviceId;
    }

    public double getCpuTempLowerBound() {
        return cpuTempLowerBound;
    }

    public void setCpuTempLowerBound(double cpuTempLowerBound) {
        this.cpuTempLowerBound = cpuTempLowerBound;
    }

    public double getCpuTempUpperBound() {
        return cpuTempUpperBound;
    }

    public void setCpuTempUpperBound(double cpuTempUpperBound) {
        this.cpuTempUpperBound = cpuTempUpperBound;
    }

    public double getCpuLoadLowerBound() {
        return cpuLoadLowerBound;
    }

    public void setCpuLoadLowerBound(double cpuLoadLowerBound) {
        this.cpuLoadLowerBound = cpuLoadLowerBound;
    }

    public double getCpuLoadUpperBound() {
        return cpuLoadUpperBound;
    }

    public void setCpuLoadUpperBound(double cpuLoadUpperBound) {
        this.cpuLoadUpperBound = cpuLoadUpperBound;
    }

    public double getBandwidthLowerBound() {
        return bandwidthLowerBound;
    }

    public void setBandwidthLowerBound(double bandwidthLowerBound) {
        this.bandwidthLowerBound = bandwidthLowerBound;
    }

    public double getBandwidthUpperBound() {
        return bandwidthUpperBound;
    }

    public void setBandwidthUpperBound(double bandwidthUpperBound) {
        this.bandwidthUpperBound = bandwidthUpperBound;
    }

    public double getIoLowerBound() {
        return ioLowerBound;
    }

    public void setIoLowerBound(double ioLowerBound) {
        this.ioLowerBound = ioLowerBound;
    }

    public double getIoUpperBound() {
        return ioUpperBound;
    }

    public void setIoUpperBound(double ioUpperBound) {
        this.ioUpperBound = ioUpperBound;
    }

    public double getRamLowerBound() {
        return ramLowerBound;
    }

    public void setRamLowerBound(double ramLowerBound) {
        this.ramLowerBound = ramLowerBound;
    }

    public double getRamUpperBound() {
        return ramUpperBound;
    }

    public void setRamUpperBound(double ramUpperBound) {
        this.ramUpperBound = ramUpperBound;
    }
}
