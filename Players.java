import java.util.ArrayList;

public class Players {
    private ArrayList<Player> Players;
    
    public Players()
    {
        this.Players = new ArrayList<>();
    }

    public void addPlayer(Player player) {
        Players.add(player);
    }

    public void updatePlayer(String existingPlayerName, String updatedPlayerName, double updatedCredit, int updatedAge, int updatedNo) {
        for (int i=0; i<Players.size(); i++) {
            if (Players.get(i).readPlayerName().equals(existingPlayerName)) {
                Players.set(i, new Player(updatedPlayerName, updatedCredit, updatedAge, updatedNo));
            }
        }
        // Players.set()
    }

    public int NoofPlayers() {
        if (this.Players == null) {
            return 0;
        }
        return (this.Players.size());
    }

    public double avgPlayerCredit() {
        double add = 0;
        if (this.Players.size() == 0) {
            return 0;
        }
        else {
        for (int i=0; i < this.Players.size(); i++) {
            add += this.Players.get(i).readPlayerCredit();
        }
        return (add/this.Players.size());
        }
    }

    public double avgPlayerAge() {
        double add = 0;
        if (this.Players.size() == 0) {
            return 0;
        }
        for(int i=0; i < this.Players.size(); i++) {
            add += this.Players.get(i).readPlayerAge();
        }
        return (add/this.Players.size());
    }

    public ArrayList<Player> giveList() {
        return (this.Players);
    }

    public String nameOfPlayerWithSameNo(int No) {
        for (int i=0; i<Players.size(); i++) {
            if (this.Players.get(i).readPlayerNo() == No) {
                return(this.Players.get(i).readPlayerName());
            }
        }
        return("");

    }

    public int getIndexofPlayersListfromName(String playerName) {
        for (int i=0; i<Players.size(); i++) {
            // System.out.println(Players.get(i).readPlayerName());
            if (Players.get(i).readPlayerName().equals(playerName)) {
                return(Players.indexOf(i));
            }
        }
        return(0);
    }

}
