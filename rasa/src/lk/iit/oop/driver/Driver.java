package lk.iit.oop.driver;

import java.io.Serializable;

public abstract class Driver implements Serializable {//implements Driver class from Serializable interface (covert driver class to byte stream)
    private String driverId;
    private String driverName;
    private String location;
    private String team;

    public Driver() {
    }

    public Driver(String driverId, String driverName, String location, String team, int statistics) {
        this.driverId = driverId;
        this.driverName = driverName;
        this.location = location;
        this.team = team;

    }

    public String getDriverId() {
        return driverId;
    }

    public void setDriverId(String driverId) {
        this.driverId = driverId;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        this.team = team;
    }


    @Override
    public String toString() {
        return "Driver{" +
                "id='" + driverId + '\'' +
                ", name='" + driverName + '\'' +
                ", location...='" + location + '\'' +
                ", team='" + team + '\'' +
                '}';
    }
}
