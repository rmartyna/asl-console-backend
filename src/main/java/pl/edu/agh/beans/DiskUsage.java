package pl.edu.agh.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class DiskUsage {

    private long id;

    private int diskId;

    private double read;

    private double write;

    private Timestamp date;

    public DiskUsage(ResultSet resultSet) throws SQLException {
        id = resultSet.getInt(1);
        diskId = resultSet.getInt(2);
        read = resultSet.getDouble(3);
        write = resultSet.getDouble(4);
        date = resultSet.getTimestamp(5);
    }

    @Override
    public String toString() {
        return "DiskUsage{" +
                "id=" + id +
                ", diskId=" + diskId +
                ", read=" + read +
                ", write=" + write +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DiskUsage)) return false;

        DiskUsage diskUsage = (DiskUsage) o;

        if (id != diskUsage.id) return false;
        if (diskId != diskUsage.diskId) return false;
        if (Double.compare(diskUsage.read, read) != 0) return false;
        if (Double.compare(diskUsage.write, write) != 0) return false;
        return date != null ? date.equals(diskUsage.date) : diskUsage.date == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + diskId;
        temp = Double.doubleToLongBits(read);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(write);
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

    public int getDiskId() {
        return diskId;
    }

    public void setDiskId(int diskId) {
        this.diskId = diskId;
    }

    public double getRead() {
        return read;
    }

    public void setRead(double read) {
        this.read = read;
    }

    public double getWrite() {
        return write;
    }

    public void setWrite(double write) {
        this.write = write;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
