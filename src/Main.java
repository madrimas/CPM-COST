import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        var variable = "CPM-COST";
        System.out.println(variable);

        GlobalData globalData = GlobalData.getInstance();
        int numberOfActions = globalData.getNumberOfActions();
        List<Integer> eventStartList = globalData.getEventStartList();
        List<Integer> eventEndList = globalData.getEventEndList();
        List<Double> defaultTimeList = globalData.getDefaultTimeList();
        List<Double> afterTimeReductionList = globalData.getAfterTimeReductionList();
        List<Double> defaultCostList = globalData.getDefaultCostList();
        List<Double> reductionCostList = globalData.getReductionCostList();

        for(int i=0;i<numberOfActions;i++){
            System.out.println("Początek czynności w zdarzeniu nr: " + eventStartList.get(i));
            System.out.println("Koniec czynności w zdarzeniu nr: " + eventEndList.get(i));
            System.out.println("Domyślny czas trwania: " + defaultTimeList.get(i));
            System.out.println("Skrócony czas trwania: " + afterTimeReductionList.get(i));
            System.out.println("Domyślny koszt: " + defaultCostList.get(i));
            System.out.println("Koszt skrócenia: " + reductionCostList.get(i));
            System.out.println();
        }
    }
}
