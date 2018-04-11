import java.util.ArrayList;
import java.util.List;

public class Path {

    private static int counter = 0;
    private int ID;
    private double length;
    List<Action> actionInPathList;

    Path(){
        this.ID = counter;
        this.length = 0;
        this.actionInPathList = new ArrayList<>();
        counter++;
    }

    Path(int length, List<Action> actionInPathList){
        this.ID = counter;
        this.length = length;
        this.actionInPathList = actionInPathList;
        counter++;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public List<Action> getActionInPathList() {
        return actionInPathList;
    }

    public void addActionInPathList(Action action) {
        this.actionInPathList.add(action);
    }

    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        Path.counter = counter;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setActionInPathList(List<Action> actionInPathList) {
        this.actionInPathList = actionInPathList;
    }
}
