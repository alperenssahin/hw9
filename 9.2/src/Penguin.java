import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Penguin {
    private List<Geo> locations;
    private String trackID;

    public Penguin(List<Geo> locations, String trackID) {
        this.locations = locations;
        this.trackID = trackID;
    }

    @Override
    public String toString() {
        return "Penguin{" +
                "locations=" + locations +
                " , trackID='" + trackID + '\'' +
                '}';
    }

    public List<Geo> getLocations() {
        return locations;
    }

    public String getTrackID() {
        return trackID;
    }

    public String toStringUsingStreams() {
        // TODO

        return "Penguin{" +
                "locations=[" +locations.stream().map(geo -> new String(geo.toString())).collect(Collectors.joining(", "))
                +"]" +
                ", trackID='" + trackID + "'" +
                "}";
    }
}
