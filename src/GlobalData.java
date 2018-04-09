import java.io.FileReader;
import java.io.IOException;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;

public class GlobalData {

    static private GlobalData globalData;

    private int numberOfActions;
    private List<Integer> eventStartList;
    private List<Integer> eventEndList;
    private List<Double> defaultTimeList;
    private List<Double> afterTimeReductionList;
    private List<Double> defaultCostList;
    private List<Double> reductionCostList;

    public static GlobalData getInstance() throws IOException {
        if (globalData == null) {
            globalData = new GlobalData();
        }
        return globalData;
    }

    private GlobalData() throws IOException {

        FileReader dataFile = new FileReader("globalData.txt");
        StreamTokenizer reader = new StreamTokenizer(dataFile);
        List<Double> fileList = new ArrayList<>();

        int counter = 0;

        int streamValue;
        while ((streamValue = reader.nextToken()) != StreamTokenizer.TT_EOF) {
            if (streamValue == StreamTokenizer.TT_NUMBER) {
                fileList.add(reader.nval);
                counter++;
            }
        }

        this.numberOfActions = counter / 6;

        this.eventStartList = new ArrayList<>();
        this.eventEndList = new ArrayList<>();
        this.defaultTimeList = new ArrayList<>();
        this.afterTimeReductionList = new ArrayList<>();
        this.defaultCostList = new ArrayList<>();
        this.reductionCostList = new ArrayList<>();

        for (int i = 0; i < numberOfActions; i++) {
            eventStartList.add(fileList.get(6*i).intValue());
            eventEndList.add(fileList.get(6*i + 1).intValue());
            defaultTimeList.add(fileList.get(6*i + 2));
            afterTimeReductionList.add(fileList.get(6*i + 3));
            defaultCostList.add(fileList.get(6*i + 4));
            reductionCostList.add(fileList.get(6*i + 5));
        }

    }

    public int getNumberOfActions() {
        return numberOfActions;
    }

    public List<Integer> getEventStartList() {
        return eventStartList;
    }

    public List<Integer> getEventEndList() {
        return eventEndList;
    }

    public List<Double> getDefaultTimeList() {
        return defaultTimeList;
    }

    public List<Double> getAfterTimeReductionList() {
        return afterTimeReductionList;
    }

    public List<Double> getDefaultCostList() {
        return defaultCostList;
    }

    public List<Double> getReductionCostList() {
        return reductionCostList;
    }
}
