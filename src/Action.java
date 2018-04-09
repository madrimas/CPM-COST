public class Action {
    private int id; //id czynności
    private int eventStart; //numer zdarzenia
    private int eventEnd; //numer zdarzenia
    private double tn; //czas domyślny
    private double tgr; //czas po skróceniu
    private double kn; //koszt domyślny
    private double kgr; //koszt skrócenia

    Action(int id, int eventStart, int eventEnd, double tn, double tgr, double kn, double kgr) {
        this.id = id;
        this.eventStart = eventStart;
        this.eventEnd = eventEnd;
        this.tn = tn;
        this.tgr = tgr;
        this.kn = kn;
        this.kgr = kgr;
    }

    public int getId() {
        return id;
    }

    public int getEventStart() {
        return eventStart;
    }

    public int getEventEnd() {
        return eventEnd;
    }

    public double getTn() {
        return tn;
    }

    public double getTgr() {
        return tgr;
    }

    public double getKn() {
        return kn;
    }

    public double getKgr() {
        return kgr;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setEventStart(int eventStart) {
        this.eventStart = eventStart;
    }

    public void setEventEnd(int eventEnd) {
        this.eventEnd = eventEnd;
    }

    public void setTn(double tn) {
        this.tn = tn;
    }

    public void setTgr(double tgr) {
        this.tgr = tgr;
    }

    public void setKn(double kn) {
        this.kn = kn;
    }

    public void setKgr(double kgr) {
        this.kgr = kgr;
    }
}
