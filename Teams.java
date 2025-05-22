import java.util.ArrayList;
public class Teams {
    public ArrayList<Team> teams;

    public Teams()
    {
        this.teams = new ArrayList<>();

        Team suns = new Team("Suns");
        Team bulls = new Team("Bulls");
        Team hawks = new Team("Hawks");
        Team nets = new Team("Nets");

        Players sunsPlayers = new Players();
        Players bullsPlayers = new Players();
        Players hawksPlayers = new Players();
        Players netsPlayers = new Players();

        sunsPlayers.addPlayer(new Player("Devin Booker", 2500.00, 26, 1));
        sunsPlayers.addPlayer(new Player("Chris Paul", 1500.00, 37, 3));
        sunsPlayers.addPlayer(new Player("Deandre Ayton", 2000.00, 24, 22));
        sunsPlayers.addPlayer(new Player("Kevin Durant", 3000.00, 34, 35));
        sunsPlayers.addPlayer(new Player("Terrence Ross", 1000.00, 32, 8));
        
        bullsPlayers.addPlayer(new Player("Andre Drummond", 1500.00, 29, 3));
        bullsPlayers.addPlayer(new Player("Zach LaVine", 3000.00, 28, 8));
        bullsPlayers.addPlayer(new Player("Dalen Terry", 900.00, 20, 25));
        bullsPlayers.addPlayer(new Player("Terry Taylor", 1000.00, 23, 32));
        bullsPlayers.addPlayer(new Player("Carlik Jones", 800.00, 25, 22));

        hawksPlayers.addPlayer(new Player("Trae Young", 2200.00, 24, 11));
        hawksPlayers.addPlayer(new Player("John Collins", 2000.00, 25, 20));
        hawksPlayers.addPlayer(new Player("Aaron Holiday", 800.00, 26, 3));
        hawksPlayers.addPlayer(new Player("Jalen Johnson", 1000.00, 21, 1));
        hawksPlayers.addPlayer(new Player("Trent Forrest", 1200.00, 24, 2));

        netsPlayers.addPlayer(new Player("Mikal Bridges", 2400.00, 26, 1));
        netsPlayers.addPlayer(new Player("Ben Simmons", 2000.00, 26, 10));
        netsPlayers.addPlayer(new Player("Patty Mills", 900.00, 34, 8));
        netsPlayers.addPlayer(new Player("Joe Harris", 1200.00, 31, 12));
        netsPlayers.addPlayer(new Player("Seth Curry", 1900.00, 32, 30));

        suns.addPlayers(sunsPlayers);
        bulls.addPlayers(bullsPlayers);
        hawks.addPlayers(hawksPlayers);
        nets.addPlayers(netsPlayers);

        this.teams.add(suns);
        this.teams.add(bulls);
        this.teams.add(hawks);
        this.teams.add(nets);
    }

    // public void teamsAdd(String teamName) {
    //     teams.add(Team.readName());
    // }

    public void addTeam(String teamName) {
        this.teams.add(new Team(teamName));
    }

    public void addPlayerstoTeamArray() {
        
    }
}
