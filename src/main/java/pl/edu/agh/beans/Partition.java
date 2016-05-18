package pl.edu.agh.beans;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;

public class Partition {

    private long id;

    private int diskId;

    private String name;

    private double current;

    private double max;

    private Timestamp date;

    public Partition() {

    }

    public Partition(ResultSet resultSet) throws SQLException {
        id = resultSet.getInt(1);
        diskId = resultSet.getInt(2);
        name = resultSet.getString(3);
        current = resultSet.getDouble(4);
        max = resultSet.getDouble(5);
        date = resultSet.getTimestamp(6);
    }

    @Override
    public String toString() {
        return "Partition{" +
                "id=" + id +
                ", diskId=" + diskId +
                ", name='" + name + '\'' +
                ", current=" + current +
                ", max=" + max +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Partition)) return false;

        Partition partition = (Partition) o;

        if (id != partition.id) return false;
        if (diskId != partition.diskId) return false;
        if (Double.compare(partition.current, current) != 0) return false;
        if (Double.compare(partition.max, max) != 0) return false;
        if (name != null ? !name.equals(partition.name) : partition.name != null) return false;
        return date != null ? date.equals(partition.date) : partition.date == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + diskId;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp = Double.doubleToLongBits(current);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(max);
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCurrent() {
        return current;
    }

    public void setCurrent(double current) {
        this.current = current;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
