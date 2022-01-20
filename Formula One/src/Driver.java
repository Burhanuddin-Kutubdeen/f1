import java.io.Serializable;

abstract class Driver implements Serializable {
    String driverName;
    String driverLocation;

    public Driver(String driverName, String driverLocation) {
        this.driverName = driverName;
        this.driverLocation = driverLocation;
    }

     public Driver(String driverName) {
        this.driverName=driverName;
     }

     @Override
    public String toString() {
        return "Driver{" +
                "driverName='" + driverName + '\'' +
                ", driverLocation='" + driverLocation + '\'' +
                '}';
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverLocation() {
        return driverLocation;
    }

    public void setDriverLocation(String driverLocation) {
        this.driverLocation = driverLocation;
    }
}
