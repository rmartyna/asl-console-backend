package pl.edu.agh.beans;

import javafx.util.converter.TimeStringConverter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CpuTemp {

    private long id;

    private int cpuId;

    private double value;

    private Timestamp date;

    public CpuTemp(ResultSet resultSet) throws SQLException {
        id = resultSet.getInt(1);
        cpuId = resultSet.getInt(2);
        value = resultSet.getDouble(3);
        date = resultSet.getTimestamp(4);
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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CpuTemp)) return false;

        CpuTemp cpuTemp = (CpuTemp) o;

        if (id != cpuTemp.id) return false;
        if (cpuId != cpuTemp.cpuId) return false;
        if (Double.compare(cpuTemp.value, value) != 0) return false;
        return date != null ? date.equals(cpuTemp.date) : cpuTemp.date == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + cpuId;
        temp = Double.doubleToLongBits(value);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "CpuTemp{" +
                "id=" + id +
                ", cpuId=" + cpuId +
                ", value=" + value +
                ", date=" + date +
                '}';
    }
}
