import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        var variable = "CPM-COST";
        System.out.println(variable);

        GlobalData globalData = GlobalData.getInstance();
        int numberOfActions = globalData.getNumberOfActions();
        List<Integer> idList = globalData.getIdList();
        List<Integer> eventStartList = globalData.getEventStartList();
        List<Integer> eventEndList = globalData.getEventEndList();
        List<Double> defaultTimeList = globalData.getDefaultTimeList();
        List<Double> afterTimeReductionList = globalData.getAfterTimeReductionList();
        List<Double> defaultCostList = globalData.getDefaultCostList();
        List<Double> reductionCostList = globalData.getReductionCostList();
        List<Event> eventList = new ArrayList<>();

        for (int i = 0; i < numberOfActions; i++) {
            System.out.println("ID czynności: " + idList.get(i));
            System.out.println("Początek czynności w zdarzeniu nr: " + eventStartList.get(i));
            System.out.println("Koniec czynności w zdarzeniu nr: " + eventEndList.get(i));
            System.out.println("Domyślny czas trwania: " + defaultTimeList.get(i));
            System.out.println("Skrócony czas trwania: " + afterTimeReductionList.get(i));
            System.out.println("Domyślny koszt: " + defaultCostList.get(i));
            System.out.println("Koszt skrócenia: " + reductionCostList.get(i));
            System.out.println();
        }

        for (int i = 0; i < numberOfActions; i++) {
            eventList.add(new Event());
            System.out.println("Event nr: " + eventList.get(i).getEventID());
            for (int j = 0; j < numberOfActions; j++) {
                Integer tempEventID = eventList.get(i).getEventID();
                Integer tempActionID = eventStartList.get(j);
                if (tempEventID.equals(tempActionID))
                    eventList.get(i).getOutgoingActionList().add(idList.get(j));
                tempActionID = eventEndList.get(j);
                if (tempEventID.equals(tempActionID))
                    eventList.get(i).getIngoingActionList().add(idList.get(j));
            }
        }

        for (int i = 0; i < numberOfActions; i++) {
            for (Integer object : eventList.get(i).getOutgoingActionList()
                    ) {
                System.out.println("Czynności wychodzące ze zdarzenia: " + (i+1) + " to: " + object);
            }
            for (Integer object : eventList.get(i).getIngoingActionList()
                    ) {
                System.out.println("Czynności wchodzące do zdarzenia: " + (i+1) + " to: " + object);
            }
        }
    }
}
