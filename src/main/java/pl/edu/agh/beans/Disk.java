package pl.edu.agh.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Disk {

    private long id;

    private long serviceId;

    private String description;

    private List<Partition> partitionList;

    private List<DiskUsage> diskUsageList;

    public Disk(ResultSet resultSet) throws SQLException {
        id = resultSet.getInt(1);
        serviceId = resultSet.getInt(2);
        description = resultSet.getString(3);

        partitionList = new ArrayList<Partition>();
        diskUsageList = new ArrayList<DiskUsage>();
    }

    public void addPartition(Partition partition) {
        partitionList.add(partition);
    }

    public void addDiskUsage(DiskUsage diskUsage) {
        diskUsageList.add(diskUsage);
    }

    public void setPartitionList(List<Partition> partitionList) {
        this.partitionList = partitionList;
    }

    public void setDiskUsageList(List<DiskUsage> diskUsageList) {
        this.diskUsageList = diskUsageList;
    }

    @Override
    public String toString() {
        return "Disk{" +
                "id=" + id +
                ", serviceId=" + serviceId +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Disk)) return false;

        Disk disk = (Disk) o;

        if (id != disk.id) return false;
        if (serviceId != disk.serviceId) return false;
        return description != null ? description.equals(disk.description) : disk.description == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (serviceId ^ (serviceId >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
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
}
