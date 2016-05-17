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

    public Cpu(ResultSet resultSet) throws SQLException {
        id = resultSet.getInt(1);
        serviceId = resultSet.getInt(2);
        description = resultSet.getString(3);

        cpuFanList = new ArrayList<CpuFan>();
        cpuTempList = new ArrayList<CpuTemp>();
        cpuUsageList = new ArrayList<CpuUsage>();
    }

    public void addCpuFan(CpuFan cpuFan) {
        cpuFanList.add(cpuFan);
    }

    public void addCpuTemp(CpuTemp cpuTemp) {
        cpuTempList.add(cpuTemp);
    }

    public void addCpuUsage(CpuUsage cpuUsage) {
        cpuUsageList.add(cpuUsage);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cpu)) return false;

        Cpu cpu = (Cpu) o;

        if (id != cpu.id) return false;
        if (serviceId != cpu.serviceId) return false;
        return description != null ? description.equals(cpu.description) : cpu.description == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (serviceId ^ (serviceId >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Cpu{" +
                "id=" + id +
                ", serviceId=" + serviceId +
                ", description='" + description + '\'' +
                '}';
    }

    public List<CpuFan> getCpuFanList() {
        return cpuFanList;
    }

    public List<CpuTemp> getCpuTempList() {
        return cpuTempList;
    }

    public List<CpuUsage> getCpuUsageList() {
        return cpuUsageList;
    }
}
