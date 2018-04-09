import java.util.ArrayList;
import java.util.List;

public class Path {

    private double length;
    List<Action> actionInPathList;

    Path(){
        this.length = 0;
        this.actionInPathList = new ArrayList<>();
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

    public void setActionInPathList(List<Action> actionInPathList) {
        this.actionInPathList = actionInPathList;
    }
}
