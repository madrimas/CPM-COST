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
        List<Action> actionList = new ArrayList<>();
        List<Path> pathList = new ArrayList<>();
        boolean allChecked = false;


        for (int i = 0; i < numberOfActions; i++) {
            actionList.add(new Action(idList.get(i), eventStartList.get(i), eventEndList.get(i), defaultTimeList.get(i),
                    afterTimeReductionList.get(i), defaultCostList.get(i), reductionCostList.get(i)));
            System.out.println("ID czynności: " + actionList.get(i).getId());
            System.out.println("Początek czynności w zdarzeniu nr: " + actionList.get(i).getEventStart());
            System.out.println("Koniec czynności w zdarzeniu nr: " + actionList.get(i).getEventEnd());
            System.out.println("Domyślny czas trwania: " + actionList.get(i).getTn());
            System.out.println("Skrócony czas trwania: " + actionList.get(i).getTgr());
            System.out.println("Domyślny koszt: " + actionList.get(i).getKn());
            System.out.println("Koszt skrócenia: " + actionList.get(i).getKgr());
            System.out.println();
        }

        for (int i = 0; i < numberOfActions; i++) {
            eventList.add(new Event());
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
                //System.out.println("Czynności wchodzące do zdarzenia: " + (i+1) + " to: " + object);
            }
        }
        int counter = 0;
        while(!allChecked){
            allChecked = true;
            for(int i=0;i<numberOfActions;i++){
                if(!actionList.get(i).isChecked())
                    allChecked = false;
            }
            pathList.add(new Path());
            pathList.get(counter).
        }



    }
}
