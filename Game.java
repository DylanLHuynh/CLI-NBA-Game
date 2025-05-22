import java.util.ArrayList;
public class Game
{
    private ArrayList<Team> teams;
    private ArrayList<Team> results;
    public Integer term;
    public Game(int term)
    {
        this.term = term;
        this.teams = new ArrayList<>();
        this.results = new ArrayList<>();
    }

    public int returnTerm() {
        return this.term;
    }

    public ArrayList<Team> returnGameTeamList() {
        return this.teams;
    }
}




