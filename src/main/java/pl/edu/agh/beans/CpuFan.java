package pl.edu.agh.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class CpuFan {

    private long id;

    private int cpuId;

    private int speed;

    private Timestamp date;

    public CpuFan() {

    }

    public CpuFan(ResultSet resultSet) throws SQLException {
        id = resultSet.getInt(1);
        cpuId = resultSet.getInt(2);
        speed = resultSet.getInt(3);
        date = resultSet.getTimestamp(4);
    }

    @Override
    public String toString() {
        return "CpuFan{" +
                "id=" + id +
                ", cpuId=" + cpuId +
                ", speed=" + speed +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof CpuFan)) return false;

        CpuFan cpuFan = (CpuFan) o;

        if (id != cpuFan.id) return false;
        if (cpuId != cpuFan.cpuId) return false;
        if (speed != cpuFan.speed) return false;
        return date != null ? date.equals(cpuFan.date) : cpuFan.date == null;

    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + cpuId;
        result = 31 * result + speed;
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

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }
}
