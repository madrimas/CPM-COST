import java.io.IOException;
import java.util.*;

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

        //System.out.println("EVENTID" + eventList.get(0).getEventID());

        for (int i = 0; i < numberOfActions; i++) {
            for (Integer object : eventList.get(i).getOutgoingActionList()
                    ) {
                //System.out.println("Czynności wychodzące ze zdarzenia: " + (i + 1) + " to: " + object);
            }
            for (Integer object : eventList.get(i).getIngoingActionList()
                    ) {
                //System.out.println("Czynności wchodzące do zdarzenia: " + (i+1) + " to: " + object);
            }
        }
        int eventCounter = 0;
        int actionCounter = 0;
        while (!allChecked) {
            List<Action> actionPathList = new ArrayList<>();
            int length = 0;
            while (eventCounter != (eventList.size()-1)) {
                allChecked = true;

                List<Integer> allOutList = eventList.get(eventCounter).getOutgoingActionList();
                List<Integer> tempOutList = new ArrayList<>();

                for (int i = 0; i < allOutList.size(); i++) {
                    if (!actionList.get((allOutList.get(i) - 1)).isChecked())
                        tempOutList.add(allOutList.get(i));
                }


                List<Action> tempActionList = new ArrayList<>();
                for (Integer aTempOutList : tempOutList) {
                    tempActionList.add(actionList.get(aTempOutList - 1));
                }
                tempActionList.sort(Action.Comparators.TIME);
                Collections.reverse(tempActionList);

                actionCounter = tempActionList.get(0).getId();
                actionPathList.add(actionList.get(actionCounter - 1));
                length += tempActionList.get(0).getTn();
                //System.out.println("PathTime: " + length);


                //if (tempOutList.size() == 1)
                actionList.get(actionCounter - 1).setChecked(true);
                //else
                //    actionList.get(actionCounter-1).setChecked(false);

                for (Action object : tempActionList
                        ) {
                    //System.out.println(object.getTn());

                }
                eventCounter = tempActionList.get(0).getEventEnd() - 1;
                //System.out.println(eventList.get(eventCounter).getEventID());

            }
            pathList.add(new Path(length, actionPathList));
            for(int i=0;i<actionList.size();i++){
                boolean test = actionList.get(i).isChecked();
                //System.out.println(test);
                if(!test){
                    eventCounter = i;
                    break;
                }
            }
            //eventCounter = 0;
            actionList.get(actionList.size() - 1).setChecked(true);
            for (int i = 0; i < numberOfActions; i++) {
                if (!actionList.get(i).isChecked())
                    allChecked = false;
            }
        }

        double criticalPathLength = 0;
        int criticalPathID = 0;
        double secondPathLength = 0;

        //System.out.println(pathList.size());
        for (Path path : pathList
                ) {
            for (int i = 0; i < path.actionInPathList.size(); i++) {
                //System.out.println(path.actionInPathList.get(i).getId());
            }
            if (path.getLength() > criticalPathLength) {
                criticalPathLength = path.getLength();
                criticalPathID = path.getID();
            } else if (path.getLength() > secondPathLength)
                secondPathLength = path.getLength();
            //System.out.println();
        }
        System.out.println("Długość ścieżki krytycznej: " + criticalPathLength);
        System.out.println("Scieżka krytyczna akcji:");
        for (int i = 0; i < pathList.get(criticalPathID).actionInPathList.size(); i++) {
            System.out.println(pathList.get(criticalPathID).actionInPathList.get(i).getId());
            pathList.get(criticalPathID).setLength(pathList.get(criticalPathID).getLength()-(
                    actionList.get(pathList.get(criticalPathID).actionInPathList.get(i).getId()-1).getTn()-
                            actionList.get(pathList.get(criticalPathID).actionInPathList.get(i).getId()-1).getTgr()
                    ));
        }

        criticalPathLength = pathList.get(criticalPathID).getLength();


        //System.out.println(criticalPathID);
        //System.out.println(secondPathLength);

        double k = 0;
        double kp = 0;
        double kpc = 0;

        //while (criticalPathLength >= secondPathLength) {
            for (int i = 0; i < actionList.size(); i++) {
                if (actionList.get(i).getTn() - actionList.get(i).getTgr() != 0) {
                    k = (actionList.get(i).getKgr()-actionList.get(i).getKn())/(actionList.get(i).getTn()-actionList.get(i).getTgr());
                }
                kp = k * (actionList.get(i).getTn()-actionList.get(i).getTgr());
                kpc += kp;
            }
        //}
        System.out.println("Koszt: " + kpc);
        System.out.println("Długość ścieżki krytycznej po skróceniu: " + criticalPathLength);

    }
}
