package pl.edu.agh.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Memory {

    private long id;

    private long serviceId;

    private double current;

    private double max;

    private Timestamp date;

    public Memory() {

    }

    public Memory(ResultSet resultSet) throws SQLException {
        id = resultSet.getInt(1);
        serviceId = resultSet.getInt(2);
        current = resultSet.getDouble(3);
        max = resultSet.getDouble(4);
        date = resultSet.getTimestamp(5);
    }

    @Override
    public String toString() {
        return "Memory{" +
                "id=" + id +
                ", serviceId=" + serviceId +
                ", current=" + current +
                ", max=" + max +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Memory)) return false;

        Memory memory = (Memory) o;

        if (id != memory.id) return false;
        if (serviceId != memory.serviceId) return false;
        if (Double.compare(memory.current, current) != 0) return false;
        if (Double.compare(memory.max, max) != 0) return false;
        return date != null ? date.equals(memory.date) : memory.date == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (serviceId ^ (serviceId >>> 32));
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

    public long getServiceId() {
        return serviceId;
    }

    public void setServiceId(long serviceId) {
        this.serviceId = serviceId;
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
