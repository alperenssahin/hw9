
import java.util.ArrayList;

public class Test {
    public static void main(String[] args) {
        TestToString();
//         TestGetDataByTrackId();
        // TestOutputPenguinStream();
        // OutputLocationRangePerTrackid();
    }

    static void OutputLocationRangePerTrackid() {
        DataScience.outputLocationRangePerTrackid(CSVReading.processInputFile());
    }
    static void TestOutputPenguinStream() {
        DataScience.outputPenguinStream();
    }

    static void TestGetDataByTrackId() {
        PenguinData pd1 = new PenguinData("1", 1, null, 2, 4, "", "", "P1", null);
        PenguinData pd2 = new PenguinData("2", 2, null, 1, 5, "", "", "P2", null);
        PenguinData pd3 = new PenguinData("3", 3, null, 2, 6, "", "", "P3", null);
        PenguinData pd4 = new PenguinData("4", 4, null, -1, 3, "", "", "P2", null);
        PenguinData pd5 = new PenguinData("5", 5, null, -4, 4, "", "", "P4", null);

        ArrayList<PenguinData> pdList = new ArrayList<PenguinData>();
        pdList.add(pd1);
        pdList.add(pd2);
        pdList.add(pd3);
        pdList.add(pd4);
        pdList.add(pd5);

        DataScience.getDataByTrackId(pdList.stream()).forEach(p -> System.out.println(p.toStringUsingStreams()));
    }

    static void TestToString() {
        Geo g1 = new Geo(-3.5, 12);
        Geo g2 = new Geo(1, 4);
        Geo g3 = new Geo(1, 2);

        ArrayList<Geo> geoList = new ArrayList<Geo>();
        geoList.add(g1);
        geoList.add(g2);
        geoList.add(g3);

        Penguin p = new Penguin(geoList, "Pingu");
        System.out.println(p.toString());
//        System.out.println(p.toStringUsingStreams());
    }
}
