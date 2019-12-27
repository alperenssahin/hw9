import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataScience {
  public static Stream<Penguin> getDataByTrackId(Stream<PenguinData> stream) {
    // TODO
    List<Penguin> pList = new ArrayList<>();
    stream.collect(Collectors.groupingBy(PenguinData::getTrackID)).forEach((s, penguinData) -> {
      List<Geo> x = new ArrayList<>();
      penguinData.forEach(penguinData1 -> x.add(penguinData1.getGeom()));
      pList.add(new Penguin(x,s));
    });
    return pList.stream();
  }

  public static void outputPenguinStream() {
    // TODO
    AtomicInteger counter = new AtomicInteger(0);
    Stream<PenguinData> data = CSVReading.processInputFile();
    Stream<Penguin> pg = getDataByTrackId(data);
    pg.map(penguin -> {
      counter.getAndIncrement();
      return penguin;
    }).forEach(penguin -> {
      System.out.println(counter);
      System.out.println(penguin.toStringUsingStreams());
    });

  }

  public static void outputLocationRangePerTrackid(Stream<PenguinData> stream) {
    // TODO
    getDataByTrackId(stream).forEach(penguin -> {
      System.out.println(penguin.getTrackID());
      System.out.print("Min Longitude: "+ penguin.getLocations().stream().min(Comparator.comparingDouble(Geo::getLongitude)).get().getLongitude());
      System.out.print(" Max Longitude: "+ penguin.getLocations().stream().max(Comparator.comparingDouble(Geo::getLongitude)).get().getLongitude());
      System.out.print(" Avg Longitude: "+ penguin.getLocations().stream().mapToDouble(Geo::getLongitude).sum()/penguin.getLocations().size());
      System.out.print(" Min Latitude: "+ penguin.getLocations().stream().min(Comparator.comparingDouble(Geo::getLatitude)).get().getLatitude());
      System.out.print(" Max Latitude: "+ penguin.getLocations().stream().max(Comparator.comparingDouble(Geo::getLatitude)).get().getLatitude());
      System.out.print(" Avg Latitude: "+ penguin.getLocations().stream().mapToDouble(Geo::getLatitude).sum()/penguin.getLocations().size());
      System.out.println();
    });
  }

  public static void main(String[] args) {
    // TODO Test your implementation yourself
    Stream<PenguinData> data = CSVReading.processInputFile();
//    data.forEach(p ->System.out.println(p.toString()));
//    outputLocationRangePerTrackid(data);
//    getDataByTrackId(data);
    outputPenguinStream();
  }
}