import java.util.ArrayList;
import java.util.List;

public class Event {

    private static int eventCounterID = 0;
    private int eventID;
    private List<Integer> ingoingActionList;
    private List<Integer> outgoingActionList;

    Event(){
        eventCounterID++;
        eventID = eventCounterID;
        ingoingActionList = new ArrayList<>();
        outgoingActionList = new ArrayList<>();
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public List<Integer> getIngoingActionList() {
        return ingoingActionList;
    }

    public void setIngoingActionList(List<Integer> ingoingActionList) {
        this.ingoingActionList = ingoingActionList;
    }

    public List<Integer> getOutgoingActionList() {
        return outgoingActionList;
    }

    public void setOutgoingActionList(List<Integer> outgoingActionList) {
        this.outgoingActionList = outgoingActionList;
    }

    public static int getEventCounterID() {
        return eventCounterID;
    }

    public static void setEventCounterID(int eventCounterID) {
        Event.eventCounterID = eventCounterID;
    }
}
