import java.util.ArrayList;
public class Team {
    private String name;
    private Players players;

    public Team(String name)
    {   
        this.name = name;
    }

    public String readName() {
        return this.name;
    }

    public Players teamPlayers() {
        if (this.players == null) {
            return(new Players());
        }
        return this.players;
    }

    // public void addPlayersToTeam(Players players) {
    //     this.players = new Players();
    // }

    public void addPlayers(Players players) {
        this.players = players;
    }

    // public int countPlayers() {
    //     int count = 0;
    //     for (Players player : players) {
    //         count++;
    //     }
    //     return count;
    // }
}
