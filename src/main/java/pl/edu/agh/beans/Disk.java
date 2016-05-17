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

    public Disk() {
        partitionList = new ArrayList<Partition>();
        diskUsageList = new ArrayList<DiskUsage>();
    }

    public Disk(ResultSet resultSet) throws SQLException {
        id = resultSet.getInt(1);
        serviceId = resultSet.getInt(2);
        description = resultSet.getString(3);

        partitionList = new ArrayList<Partition>();
        diskUsageList = new ArrayList<DiskUsage>();
    }

    @Override
    public String toString() {
        return "Disk{" +
                "id=" + id +
                ", serviceId=" + serviceId +
                ", description='" + description + '\'' +
                ", partitionList=" + partitionList +
                ", diskUsageList=" + diskUsageList +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Disk)) return false;

        Disk disk = (Disk) o;

        if (id != disk.id) return false;
        if (serviceId != disk.serviceId) return false;
        if (description != null ? !description.equals(disk.description) : disk.description != null) return false;
        if (partitionList != null ? !partitionList.equals(disk.partitionList) : disk.partitionList != null)
            return false;
        return diskUsageList != null ? diskUsageList.equals(disk.diskUsageList) : disk.diskUsageList == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (serviceId ^ (serviceId >>> 32));
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (partitionList != null ? partitionList.hashCode() : 0);
        result = 31 * result + (diskUsageList != null ? diskUsageList.hashCode() : 0);
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

    public List<Partition> getPartitionList() {
        return partitionList;
    }

    public void setPartitionList(List<Partition> partitionList) {
        this.partitionList = partitionList;
    }

    public List<DiskUsage> getDiskUsageList() {
        return diskUsageList;
    }

    public void setDiskUsageList(List<DiskUsage> diskUsageList) {
        this.diskUsageList = diskUsageList;
    }
}