package Task1;

public class Geo {
    private double lat;
    private double lng;

    public Geo(double lat, double lng) {
        this.lat = lat;
        this.lng = lng;
    }

    @Override
    public String toString() {
        return "Task1.Geo{" +
                "lat=" + lat +
                ", lng=" + lng +
                '}';
    }
}
