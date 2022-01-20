import java.io.Serializable;
import java.util.Date;

public class Race extends Formula1Driver implements Serializable {
    private Date raceDate;

    @Override
    public String toString() {
        return "Race{" +
                "driverName='" + driverName + '\'' +
                ", position=" + position +
                ", raceDate=" + raceDate +
                '}';
    }

    public Race(String driverName, int position, Date raceDate) {
        super(driverName, position);
        this.raceDate = raceDate;
    }

    public Date getRaceDate() {
        return raceDate;
    }

    public void setRaceDate(Date raceDate) {
        this.raceDate = raceDate;
    }
}
