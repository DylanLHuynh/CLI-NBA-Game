import java.util.ArrayList;
public class Season {
    private ArrayList<Game> schedule;
    private ArrayList<Team> currentTeamList;
    private Integer round;
    private ArrayList<Record> records;

    public Season()
    {
        this.schedule = new ArrayList<>();
        this.currentTeamList = new ArrayList<>();
        this.records = new ArrayList<>();
    }

    public ArrayList<Game> giveScheduleList() {
        return this.schedule;
    }

    public ArrayList<Record> giveRecordsList() {
        return this.records;
    }
    public ArrayList<Team> giveCurrentTeamList() {
        return this.currentTeamList;
    }
    public int currentRound() {
        if(this.currentTeamList.size() == 4) {
            return (1);
        }
        if(this.currentTeamList.size() == 2) {
            return (2);
        }
        return (0);
    }

}
