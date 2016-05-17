package pl.edu.agh.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Cpu {

    private long id;

    private long serviceId;

    private String description;

    private List<CpuFan> cpuFanList;

    private List<CpuTemp> cpuTempList;

    private List<CpuUsage> cpuUsageList;

    public Cpu() {
        cpuFanList = new ArrayList<CpuFan>();
        cpuTempList = new ArrayList<CpuTemp>();
        cpuUsageList = new ArrayList<CpuUsage>();
    }

    public Cpu(ResultSet resultSet) throws SQLException {
        id = resultSet.getInt(1);
        serviceId = resultSet.getInt(2);
        description = resultSet.getString(3);

        cpuFanList = new ArrayList<CpuFan>();
        cpuTempList = new ArrayList<CpuTemp>();
        cpuUsageList = new ArrayList<CpuUsage>();
    }


    @Override
    public String toString() {
        return "Cpu{" +
                "id=" + id +
                ", serviceId=" + serviceId +
                ", description='" + description + '\'' +
                ", cpuFanList=" + cpuFanList +
                ", cpuTempList=" + cpuTempList +
                ", cpuUsageList=" + cpuUsageList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cpu)) return false;

        Cpu cpu = (Cpu) o;

        if (id != cpu.id) return false;
        if (serviceId != cpu.serviceId) return false;
        if (description != null ? !description.equals(cpu.description) : cpu.description != null) return false;
        if (cpuFanList != null ? !cpuFanList.equals(cpu.cpuFanList) : cpu.cpuFanList != null) return false;
        if (cpuTempList != null ? !cpuTempList.equals(cpu.cpuTempList) : cpu.cpuTempList != null) return false;
        return cpuUsageList != null ? cpuUsageList.equals(cpu.cpuUsageList) : cpu.cpuUsageList == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (serviceId ^ (serviceId >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (cpuFanList != null ? cpuFanList.hashCode() : 0);
        result = 31 * result + (cpuTempList != null ? cpuTempList.hashCode() : 0);
        result = 31 * result + (cpuUsageList != null ? cpuUsageList.hashCode() : 0);
        return result;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<CpuFan> getCpuFanList() {
        return cpuFanList;
    }

    public void setCpuFanList(List<CpuFan> cpuFanList) {
        this.cpuFanList = cpuFanList;
    }

    public List<CpuTemp> getCpuTempList() {
        return cpuTempList;
    }

    public void setCpuTempList(List<CpuTemp> cpuTempList) {
        this.cpuTempList = cpuTempList;
    }

    public List<CpuUsage> getCpuUsageList() {
        return cpuUsageList;
    }

    public void setCpuUsageList(List<CpuUsage> cpuUsageList) {
        this.cpuUsageList = cpuUsageList;
    }
}