package pl.edu.agh.beans;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class Network {

    private long id;

    private long serviceId;

    private double download;

    private double upload;

    private Timestamp date;

    public Network() {

    }

    public Network(ResultSet resultSet) throws SQLException {
        id = resultSet.getInt(1);
        serviceId = resultSet.getInt(2);
        download = resultSet.getDouble(3);
        upload = resultSet.getDouble(4);
        date = resultSet.getTimestamp(5);
    }

    @Override
    public String toString() {
        return "Network{" +
                "id=" + id +
                ", serviceId=" + serviceId +
                ", download=" + download +
                ", upload=" + upload +
                ", date=" + date +
                '}';
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

    public double getDownload() {
        return download;
    }

    public void setDownload(double download) {
        this.download = download;
    }

    public double getUpload() {
        return upload;
    }

    public void setUpload(double upload) {
        this.upload = upload;
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
        if (!(o instanceof Network)) return false;

        Network network = (Network) o;

        if (id != network.id) return false;
        if (serviceId != network.serviceId) return false;
        if (Double.compare(network.download, download) != 0) return false;
        if (Double.compare(network.upload, upload) != 0) return false;
        return date != null ? date.equals(network.date) : network.date == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = (int) (id ^ (id >>> 32));
        result = 31 * result + (int) (serviceId ^ (serviceId >>> 32));
        temp = Double.doubleToLongBits(download);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(upload);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }
}
