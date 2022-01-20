import java.io.Serializable;


public class Formula1Driver extends Driver implements Serializable {
    String driverTeam;
    int firstPositionCount;
    int secondPositionCount;
    int thirdPositionCount;
    int points;
    int participatedRacesCount;
    int position;//
    public Formula1Driver(String driverName, String driverLocation,String driverTeam){
        super(driverName,driverLocation);
        this.setDriverTeam(driverTeam);
    }

    public Formula1Driver(String driverName, int position) {
        super(driverName);
        this.position = position;
    }

    @Override
    public String toString() {
        return "Formula1Driver{" +
                "driverTeam='" + driverTeam + '\'' +
                ", firstPositionCount=" + firstPositionCount +
                ", secondPositionCount=" + secondPositionCount +
                ", thirdPositionCount=" + thirdPositionCount +
                ", points=" + points +
                ", participatedRacesCount=" + participatedRacesCount +
                ", position=" + position +
                '}';
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getDriverTeam() {
        return driverTeam;
    }

    public void setDriverTeam(String driverTeam) {
        this.driverTeam = driverTeam;
    }

    public int getFirstPositionCount() {
        return firstPositionCount;
    }

    public void setFirstPositionCount(int firstPositionCount) {
        this.firstPositionCount = firstPositionCount;
    }

    public int getSecondPositionCount() {
        return secondPositionCount;
    }

    public void setSecondPositionCount(int secondPositionCount) {
        this.secondPositionCount = secondPositionCount;
    }

    public int getThirdPositionCount() {
        return thirdPositionCount;
    }

    public void setThirdPositionCount(int thirdPositionCount) {
        this.thirdPositionCount = thirdPositionCount;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getParticipatedRacesCount() {
        return participatedRacesCount;
    }

    public void setParticipatedRacesCount(int participatedRacesCount) {
        this.participatedRacesCount = participatedRacesCount;
    }
}
