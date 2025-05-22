public class Association
{
    private Teams teams;
    private Season season;

    public Association()
    {
        teams = new Teams();
        season = new Season();
        for (int i=0; i<teams.teams.size(); i++) {
            season.giveCurrentTeamList().add(teams.teams.get(i));
        }
        // season.giveRecordsList()
    }

    public static void main(String[] args) {
        new Association().use();
    }

    private void use() {
        String choice = readChoice1();
        while(!(choice.equals("X"))) {
            switch(choice) {
                case "1": exploreTeam(); break;
                case "2": arrange(); break;
                case "X": break;
                default: System.out.println("Please enter a number 1 or 2, or press X to exit."); break;
            }
            choice = readChoice1();
        }
        System.out.println("Done");
    }

    private void exploreTeam() {
        String exploreChoice = readChoice2();

        while(!(exploreChoice.equals("R"))) {
            switch(exploreChoice) {
                case "1": displayTeams(); break;
                case "2": displayPlayers(); break;
                case "3": addTeam(); break;
                case "4": manageTeam(); break;
                case "5": deleteTeam(); break;
                case "6": displayAllPlayersByLv(); break;
                case "R": break;
                default: break;
            }
            exploreChoice = readChoice2();
        }
    }

    private void arrange() {
        String arrangeChoice = readChoice3();
        while(!(arrangeChoice.equals("R"))) {
            switch(arrangeChoice) {
                case "1": addTeamToRound(); break;
                case "2": displayCurrentRound(); break;
                case "3": playGames(); break;
                case "4": displayGameResults(); break;
                case "R": break;
                default: System.out.println("Please enter a number between 1 and 4 or press R to return to the previous menu."); break;
            }
            arrangeChoice = readChoice3();
        }
    }

    private void displayGameResults() {
        Utils.RecordHeader();
        for(int i=0; i<season.giveRecordsList().size(); i++) {
            System.out.format(Utils.RecordFormat, season.giveRecordsList().get(i).Round(), season.giveRecordsList().get(i).returnGameNo(), 
            season.giveRecordsList().get(i).returnWinningTeam(), season.giveRecordsList().get(i).returnLosingTeam());
        }
        Utils.RecordEnd();

    }

    private void playGames() {
        boolean check = false;

        if(season.giveScheduleList().size() == 0) {
            System.out.println("No game in the current round, please add teams to the round first!");
        }
        if(season.giveScheduleList().size() > 0) {
            if(season.giveRecordsList().size() == 0) {
                double loseTeamCredit = 0;
                double winTeamCredit = 0;
                
                for(int i=0; i<season.giveScheduleList().size(); i++) {
                    if(season.giveScheduleList().get(i).returnGameTeamList().get(0).teamPlayers().avgPlayerCredit() > season.giveScheduleList().get(i).returnGameTeamList().get(1).teamPlayers().avgPlayerCredit()) {
                        season.giveRecordsList().add(new Record(season.giveScheduleList().get(i).returnGameTeamList().get(0).readName(), season.giveScheduleList().get(i).returnGameTeamList().get(1).readName()
                        , season.giveScheduleList().get(i).returnTerm(), 1)); 

                        //ADD CREDIT CHANGES HERE
                        for(int j=0; j<teams.teams.size(); j++) {
                            if(season.giveScheduleList().get(i).returnGameTeamList().get(1).readName().equals(teams.teams.get(j).readName())) {
                                loseTeamCredit = teams.teams.get(j).teamPlayers().avgPlayerCredit();
                                
                            }
                        }

                        for(int j=0; j<teams.teams.size(); j++) {
                            if(season.giveScheduleList().get(i).returnGameTeamList().get(0).readName().equals(teams.teams.get(j).readName())) {
                                winTeamCredit = teams.teams.get(j).teamPlayers().avgPlayerCredit();
                            }
                        }
                        for(int j=0; j<teams.teams.size(); j++) {
                            double creditDifference = (winTeamCredit - loseTeamCredit)/5;
                            if(teams.teams.get(j).readName().equals(season.giveScheduleList().get(i).returnGameTeamList().get(0).readName())) {
                                for(int k=0; k<teams.teams.get(j).teamPlayers().NoofPlayers(); k++) {
                                    // System.out.println("credit diff " + creditDifference);
                                    // System.out.println("win credit "+winTeamCredit);
                                    // System.out.println("1this team is " + season.giveScheduleList().get(i).returnGameTeamList().get(0).readName());
                                    teams.teams.get(j).teamPlayers().giveList().get(k).addPlayerCredit(creditDifference);
                                    // System.out.println("new winning team avg credit is " + teams.teams.get(j).teamPlayers().avgPlayerCredit());
                                    // System.out.println(season.giveScheduleList().get(i).returnGameTeamList().get(0).readName() + " " + teams.teams.get(j).teamPlayers().giveList().get(k).readPlayerName() 
                                    // + "'s new player credit avg is " + teams.teams.get(j).teamPlayers().giveList().get(k).readPlayerCredit());
                                    // System.out.println("The difference in credit is " + creditDifference);
                                    // System.out.println("The lose credit is " + loseTeamCredit);
                                    // System.out.println(teams.teams.get(j).teamPlayers().giveList().get(k).readPlayerName());
                                }
                            }
                        }
                        for(int j=0; j<teams.teams.size(); j++) {
                            double creditDifference = (winTeamCredit - loseTeamCredit)/5;
                            if(teams.teams.get(j).readName().equals(season.giveScheduleList().get(i).returnGameTeamList().get(1).readName())) {
                                for(int k=0; k<teams.teams.get(j).teamPlayers().NoofPlayers(); k++) {
                                    // System.out.println(teams.teams.get(j).readName());
                                    // System.out.println("2this team is " + season.giveScheduleList().get(i).returnGameTeamList().get(1).readName());
                                    teams.teams.get(j).teamPlayers().giveList().get(k).minusPlayerCredit(creditDifference);
                                }
                            }
                        }
                    }

                    if(season.giveScheduleList().get(i).returnGameTeamList().get(1).teamPlayers().avgPlayerCredit() > season.giveScheduleList().get(i).returnGameTeamList().get(0).teamPlayers().avgPlayerCredit()) {
                        season.giveRecordsList().add(new Record(season.giveScheduleList().get(i).returnGameTeamList().get(1).readName(), season.giveScheduleList().get(i).returnGameTeamList().get(0).readName()
                        , season.giveScheduleList().get(i).returnTerm(), 1));  

                        for(int j=0; j<teams.teams.size(); j++) {
                            if(season.giveScheduleList().get(i).returnGameTeamList().get(1).readName().equals(teams.teams.get(j).readName())) {
                                winTeamCredit = teams.teams.get(j).teamPlayers().avgPlayerCredit();
                            }
                        }
                        for(int j=0; j<teams.teams.size(); j++) {
                            if(season.giveScheduleList().get(i).returnGameTeamList().get(0).readName().equals(teams.teams.get(j).readName())) {
                                loseTeamCredit = teams.teams.get(j).teamPlayers().avgPlayerCredit();
                            }
                        }
                        for(int j=0; j<teams.teams.size(); j++) {
                            double creditDifference = (winTeamCredit - loseTeamCredit)/5;
                            if(teams.teams.get(j).readName().equals(season.giveScheduleList().get(i).returnGameTeamList().get(1).readName())) {
                                for(int k=0; k<teams.teams.get(j).teamPlayers().NoofPlayers(); k++) {
                                    // System.out.println("1this team is " + season.giveScheduleList().get(i).returnGameTeamList().get(1).readName());
                                    // System.out.println("credit diff is " + creditDifference);
                                    teams.teams.get(j).teamPlayers().giveList().get(k).addPlayerCredit(creditDifference);
                                }
                            }
                        }
                        for(int j=0; j<teams.teams.size(); j++) {
                            double creditDifference = (winTeamCredit - loseTeamCredit)/5;
                            if(teams.teams.get(j).readName().equals(season.giveScheduleList().get(i).returnGameTeamList().get(0).readName())) {
                                for(int k=0; k<teams.teams.get(j).teamPlayers().NoofPlayers(); k++) {
                                    // System.out.println(teams.teams.get(j).readName());
                                    // System.out.println("2this team is " + season.giveScheduleList().get(i).returnGameTeamList().get(0).readName());
                                    teams.teams.get(j).teamPlayers().giveList().get(k).minusPlayerCredit(creditDifference);
                                }
                            }
                        }
                    }

                    if(season.giveScheduleList().get(i).returnGameTeamList().get(1).teamPlayers().avgPlayerCredit() == season.giveScheduleList().get(i).returnGameTeamList().get(0).teamPlayers().avgPlayerCredit()) {
                        if(season.giveScheduleList().get(i).returnGameTeamList().get(1).readName().charAt(0) < season.giveScheduleList().get(i).returnGameTeamList().get(0).readName().charAt(0)) {
                            season.giveRecordsList().add(new Record(season.giveScheduleList().get(i).returnGameTeamList().get(1).readName(), season.giveScheduleList().get(i).returnGameTeamList().get(0).readName()
                            , season.giveScheduleList().get(i).returnTerm(), 1)); 

                            for(int j=0; j<teams.teams.size(); j++) {
                                if(season.giveScheduleList().get(i).returnGameTeamList().get(1).readName().equals(teams.teams.get(j).readName())) {
                                    winTeamCredit = teams.teams.get(j).teamPlayers().avgPlayerCredit();
                                }
                            }
                            for(int j=0; j<teams.teams.size(); j++) {
                                if(season.giveScheduleList().get(i).returnGameTeamList().get(0).readName().equals(teams.teams.get(j).readName())) {
                                    loseTeamCredit = teams.teams.get(j).teamPlayers().avgPlayerCredit();
                                }
                            }
                            for(int j=0; j<teams.teams.size(); j++) {
                                double creditDifference = (winTeamCredit - loseTeamCredit)/5;
                                if(teams.teams.get(j).readName().equals(season.giveScheduleList().get(i).returnGameTeamList().get(1).readName())) {
                                    for(int k=0; k<teams.teams.get(j).teamPlayers().NoofPlayers(); k++) {
                                        // System.out.println("1this team is " + season.giveScheduleList().get(i).returnGameTeamList().get(1).readName());
                                        // System.out.println("credit diff is " + creditDifference);
                                        teams.teams.get(j).teamPlayers().giveList().get(k).addPlayerCredit(creditDifference);
                                    }
                                }
                            }
                            for(int j=0; j<teams.teams.size(); j++) {
                                double creditDifference = (winTeamCredit - loseTeamCredit)/5;
                                if(teams.teams.get(j).readName().equals(season.giveScheduleList().get(i).returnGameTeamList().get(0).readName())) {
                                    for(int k=0; k<teams.teams.get(j).teamPlayers().NoofPlayers(); k++) {
                                        // System.out.println(teams.teams.get(j).readName());
                                        // System.out.println("2this team is " + season.giveScheduleList().get(i).returnGameTeamList().get(0).readName());
                                        teams.teams.get(j).teamPlayers().giveList().get(k).minusPlayerCredit(creditDifference);
                                    }
                                }
                            }
                        }
                        else {
                            season.giveRecordsList().add(new Record(season.giveScheduleList().get(i).returnGameTeamList().get(0).readName(), season.giveScheduleList().get(i).returnGameTeamList().get(1).readName()
                            , season.giveScheduleList().get(i).returnTerm(), 1));

                            for(int j=0; j<teams.teams.size(); j++) {
                                if(season.giveScheduleList().get(i).returnGameTeamList().get(0).readName().equals(teams.teams.get(j).readName())) {
                                    winTeamCredit = teams.teams.get(j).teamPlayers().avgPlayerCredit();
                                }
                            }
                            for(int j=0; j<teams.teams.size(); j++) {
                                if(season.giveScheduleList().get(i).returnGameTeamList().get(1).readName().equals(teams.teams.get(j).readName())) {
                                    loseTeamCredit = teams.teams.get(j).teamPlayers().avgPlayerCredit();
                                }
                            }
                            for(int j=0; j<teams.teams.size(); j++) {
                                double creditDifference = (winTeamCredit - loseTeamCredit)/5;
                                if(teams.teams.get(j).readName().equals(season.giveScheduleList().get(i).returnGameTeamList().get(0).readName())) {
                                    for(int k=0; k<teams.teams.get(j).teamPlayers().NoofPlayers(); k++) {
                                        teams.teams.get(j).teamPlayers().giveList().get(k).addPlayerCredit(creditDifference);
                                    }
                                }
                            }
                            for(int j=0; j<teams.teams.size(); j++) {
                                double creditDifference = (winTeamCredit - loseTeamCredit)/5;
                                if(teams.teams.get(j).readName().equals(season.giveScheduleList().get(i).returnGameTeamList().get(1).readName())) {
                                    for(int k=0; k<teams.teams.get(j).teamPlayers().NoofPlayers(); k++) {
                                        teams.teams.get(j).teamPlayers().giveList().get(k).minusPlayerCredit(creditDifference);
                                    }
                                }
                            }
                        }
                    }
                }
                System.out.println("All games finished! You can use 4 to check the results.");
                check = true;
            }
        }

        if(season.giveRecordsList().size() == 2 && check == false) {
            double loseTeamCredit = 0;
            double winTeamCredit = 0;

            if(season.giveScheduleList().get(0).returnGameTeamList().get(2).teamPlayers().avgPlayerCredit() > season.giveScheduleList().get(0).returnGameTeamList().get(3).teamPlayers().avgPlayerCredit()) {
                season.giveRecordsList().add(new Record(season.giveScheduleList().get(0).returnGameTeamList().get(2).readName(), season.giveScheduleList().get(0).returnGameTeamList().get(3).readName()
                , season.giveScheduleList().get(0).returnTerm(), 2));         

                for(int j=0; j<teams.teams.size(); j++) {
                    if(season.giveScheduleList().get(0).returnGameTeamList().get(3).readName().equals(teams.teams.get(j).readName())) {
                        loseTeamCredit = teams.teams.get(j).teamPlayers().avgPlayerCredit();
                    }
                }

                for(int j=0; j<teams.teams.size(); j++) {
                    if(season.giveScheduleList().get(0).returnGameTeamList().get(2).readName().equals(teams.teams.get(j).readName())) {
                        winTeamCredit = teams.teams.get(j).teamPlayers().avgPlayerCredit();
                    }
                }
                for(int j=0; j<teams.teams.size(); j++) {
                    double creditDifference = (winTeamCredit - loseTeamCredit)/5;
                    if(teams.teams.get(j).readName().equals(season.giveScheduleList().get(0).returnGameTeamList().get(2).readName())) {
                        for(int k=0; k<teams.teams.get(j).teamPlayers().NoofPlayers(); k++) {
                            teams.teams.get(j).teamPlayers().giveList().get(k).addPlayerCredit(creditDifference);
                            // System.out.println(season.giveScheduleList().get(0).returnGameTeamList().get(2).readName() + " " + teams.teams.get(j).teamPlayers().giveList().get(k).readPlayerName() 
                            // + "'s credit difference is " + creditDifference);
                            // System.out.println("Winning credit is " + winTeamCredit + " losing credit is " + loseTeamCredit);
                        }
                    }
                }
                for(int j=0; j<teams.teams.size(); j++) {
                    double creditDifference = (winTeamCredit - loseTeamCredit)/5;
                    if(teams.teams.get(j).readName().equals(season.giveScheduleList().get(0).returnGameTeamList().get(3).readName())) {
                        for(int k=0; k<teams.teams.get(j).teamPlayers().NoofPlayers(); k++) {
                            teams.teams.get(j).teamPlayers().giveList().get(k).minusPlayerCredit(creditDifference);
                        }
                    }
                }
            }

            if(season.giveScheduleList().get(0).returnGameTeamList().get(3).teamPlayers().avgPlayerCredit() > season.giveScheduleList().get(0).returnGameTeamList().get(2).teamPlayers().avgPlayerCredit()) {
                season.giveRecordsList().add(new Record(season.giveScheduleList().get(0).returnGameTeamList().get(3).readName(), season.giveScheduleList().get(0).returnGameTeamList().get(2).readName()
                , season.giveScheduleList().get(0).returnTerm(), 2));  

                for(int j=0; j<teams.teams.size(); j++) {
                    if(season.giveScheduleList().get(0).returnGameTeamList().get(3).readName().equals(teams.teams.get(j).readName())) {
                        winTeamCredit = teams.teams.get(j).teamPlayers().avgPlayerCredit();
                    }
                }
                for(int j=0; j<teams.teams.size(); j++) {
                    if(season.giveScheduleList().get(0).returnGameTeamList().get(2).readName().equals(teams.teams.get(j).readName())) {
                        loseTeamCredit = teams.teams.get(j).teamPlayers().avgPlayerCredit();
                    }
                }
                for(int j=0; j<teams.teams.size(); j++) {
                    double creditDifference = (winTeamCredit - loseTeamCredit)/5;
                    if(teams.teams.get(j).readName().equals(season.giveScheduleList().get(0).returnGameTeamList().get(3).readName())) {
                        for(int k=0; k<teams.teams.get(j).teamPlayers().NoofPlayers(); k++) {
                            teams.teams.get(j).teamPlayers().giveList().get(k).addPlayerCredit(creditDifference);
                        }
                    }
                }
                for(int j=0; j<teams.teams.size(); j++) {
                    double creditDifference = (winTeamCredit - loseTeamCredit)/5;
                    if(teams.teams.get(j).readName().equals(season.giveScheduleList().get(0).returnGameTeamList().get(2).readName())) {
                        for(int k=0; k<teams.teams.get(j).teamPlayers().NoofPlayers(); k++) {
                            teams.teams.get(j).teamPlayers().giveList().get(k).minusPlayerCredit(creditDifference);
                        }
                    }
                }
            }

            if(season.giveScheduleList().get(0).returnGameTeamList().get(3).teamPlayers().avgPlayerCredit() == season.giveScheduleList().get(0).returnGameTeamList().get(2).teamPlayers().avgPlayerCredit()) {
                if(season.giveScheduleList().get(0).returnGameTeamList().get(3).readName().charAt(0) < season.giveScheduleList().get(0).returnGameTeamList().get(2).readName().charAt(0)) {
                    season.giveRecordsList().add(new Record(season.giveScheduleList().get(0).returnGameTeamList().get(3).readName(), season.giveScheduleList().get(0).returnGameTeamList().get(2).readName()
                    , season.giveScheduleList().get(0).returnTerm(), 2)); 

                    for(int j=0; j<teams.teams.size(); j++) {
                        if(season.giveScheduleList().get(0).returnGameTeamList().get(3).readName().equals(teams.teams.get(j).readName())) {
                            winTeamCredit = teams.teams.get(j).teamPlayers().avgPlayerCredit();
                        }
                    }
                    for(int j=0; j<teams.teams.size(); j++) {
                        if(season.giveScheduleList().get(0).returnGameTeamList().get(2).readName().equals(teams.teams.get(j).readName())) {
                            loseTeamCredit = teams.teams.get(j).teamPlayers().avgPlayerCredit();
                        }
                    }
                    for(int j=0; j<teams.teams.size(); j++) {
                        double creditDifference = (winTeamCredit - loseTeamCredit)/5;
                        if(teams.teams.get(j).readName().equals(season.giveScheduleList().get(0).returnGameTeamList().get(3).readName())) {
                            for(int k=0; k<teams.teams.get(j).teamPlayers().NoofPlayers(); k++) {
                                        // System.out.println("1this team is " + season.giveScheduleList().get(i).returnGameTeamList().get(1).readName());
                                        // System.out.println("credit diff is " + creditDifference);
                                teams.teams.get(j).teamPlayers().giveList().get(k).addPlayerCredit(creditDifference);
                            }
                        }
                    }
                    for(int j=0; j<teams.teams.size(); j++) {
                        double creditDifference = (winTeamCredit - loseTeamCredit)/5;
                        if(teams.teams.get(j).readName().equals(season.giveScheduleList().get(0).returnGameTeamList().get(2).readName())) {
                            for(int k=0; k<teams.teams.get(j).teamPlayers().NoofPlayers(); k++) {
                                        // System.out.println(teams.teams.get(j).readName());
                                        // System.out.println("2this team is " + season.giveScheduleList().get(i).returnGameTeamList().get(0).readName());
                                teams.teams.get(j).teamPlayers().giveList().get(k).minusPlayerCredit(creditDifference);
                            }
                        }
                    }
                }
                else {
                    season.giveRecordsList().add(new Record(season.giveScheduleList().get(0).returnGameTeamList().get(2).readName(), season.giveScheduleList().get(0).returnGameTeamList().get(3).readName()
                    , season.giveScheduleList().get(0).returnTerm(), 2));

                    for(int j=0; j<teams.teams.size(); j++) {
                        if(season.giveScheduleList().get(0).returnGameTeamList().get(2).readName().equals(teams.teams.get(j).readName())) {
                            winTeamCredit = teams.teams.get(j).teamPlayers().avgPlayerCredit();
                        }
                    }
                    for(int j=0; j<teams.teams.size(); j++) {
                        if(season.giveScheduleList().get(0).returnGameTeamList().get(3).readName().equals(teams.teams.get(j).readName())) {
                            loseTeamCredit = teams.teams.get(j).teamPlayers().avgPlayerCredit();
                        }
                    }
                    for(int j=0; j<teams.teams.size(); j++) {
                        double creditDifference = (winTeamCredit - loseTeamCredit)/5;
                        if(teams.teams.get(j).readName().equals(season.giveScheduleList().get(0).returnGameTeamList().get(2).readName())) {
                            for(int k=0; k<teams.teams.get(j).teamPlayers().NoofPlayers(); k++) {
                                teams.teams.get(j).teamPlayers().giveList().get(k).addPlayerCredit(creditDifference);
                            }
                        }
                    }
                    for(int j=0; j<teams.teams.size(); j++) {
                        double creditDifference = (winTeamCredit - loseTeamCredit)/5;
                        if(teams.teams.get(j).readName().equals(season.giveScheduleList().get(0).returnGameTeamList().get(3).readName())) {
                            for(int k=0; k<teams.teams.get(j).teamPlayers().NoofPlayers(); k++) {
                                teams.teams.get(j).teamPlayers().giveList().get(k).minusPlayerCredit(creditDifference);
                            }
                        }
                    }
                }
            }
            
            System.out.println("All games finished! You can use 4 to check the results.");
            System.out.println("This season ends!");
            System.out.println(season.giveRecordsList().get(2).returnWinningTeam() + " is the Champion!!");
        }
    }

    private void displayCurrentRound() {
        Utils.GameHeader();

        for(int i=0; i<season.giveScheduleList().size(); i++) {
            System.out.format(Utils.GamesFormat, season.giveScheduleList().get(i).returnGameTeamList().get(0).readName(), " vs", 
            season.giveScheduleList().get(i).returnGameTeamList().get(1).readName());
        }

        Utils.GameEnd();
    }

    private void addTeamToRound() {
        //this adds the teamsclass list to the season teamcurrentlist
        for (int i=0; i<teams.teams.size(); i++) {
            while(teams.teams.size() > season.giveCurrentTeamList().size()) {
                season.giveCurrentTeamList().add(new Team(""));
            }

            if(teams.teams.get(i) != season.giveCurrentTeamList().get(i)) {
                season.giveCurrentTeamList().set(i, teams.teams.get(i));
            }
        }
        for (int i=0; i<season.giveCurrentTeamList().size(); i++) {
            if(season.giveRecordsList().size() > 0) {
                if(season.giveCurrentTeamList().get(i).readName().equals(season.giveRecordsList().get(0).returnLosingTeam())) {
                    season.giveCurrentTeamList().remove(i);
                    i=i-1;

                }
                if(season.giveCurrentTeamList().get(i).readName().equals(season.giveRecordsList().get(1).returnLosingTeam())) {
                    season.giveCurrentTeamList().remove(i);
                    i=i-1;
                }
            }
        }
        
            
        //creates a new element of type Game in the schedult list in Season.
        for (int i=0; i<season.giveCurrentTeamList().size()/2; i++) {
            season.giveScheduleList().add(new Game(i+1));
        }

        int initialCount = season.giveCurrentTeamList().size();
        int gameCount = 0;
        while((season.giveCurrentTeamList().size() != 1 && initialCount%2 != 1) || season.giveCurrentTeamList().size() != 0) {
                
            if(season.giveCurrentTeamList().size() == 1 && initialCount%2 == 1) {
                break;
            }
                
            String currentTeamNames = "";
            if (season.giveCurrentTeamList().size() > 0) {
                System.out.println("The existing teams are as follows: ");
                for (int j=0; j<season.giveCurrentTeamList().size(); j++) {
                    currentTeamNames += (season.giveCurrentTeamList().get(j).readName() + " ");
                }
                System.out.println(currentTeamNames.trim().replaceAll("\\s{2,}", " "));
                System.out.println("Please enter the team's name that you want to schedule: ");
                String teamScheduled1 = In.nextLine();

                for(int i=0; i<season.giveScheduleList().size(); i++) {
                    if(season.giveScheduleList().get(i).returnGameTeamList().size() != 2) {
                        gameCount = season.giveScheduleList().get(i).returnTerm();
                        break;
                    }
                }   

                checkSeasonTeams(teamScheduled1, gameCount-1);
                
            }
            if (season.giveCurrentTeamList().size() == 0) {
                break;
            }
        }
    }

    //this checks if the teamname entered matches a team in the list
    private void checkSeasonTeams(String enteredTeamName, int gameIndexCount) {
        boolean check = false;
        int index = 0;

        //for loop iterates the number of teams created.
        for (int i=0; i<season.giveCurrentTeamList().size(); i++) {
            if((enteredTeamName.toLowerCase().equals(season.giveCurrentTeamList().get(i).readName().toLowerCase()))) {
                index = i;
                check = true;
            }
        }

        if(check == true) {
            season.giveScheduleList().get(gameIndexCount).returnGameTeamList().add(season.giveCurrentTeamList().get(index));
            //removes the team from season current teamlist.
            
            if(season.giveScheduleList().get(gameIndexCount).returnGameTeamList().size() %2 == 1) {
                System.out.println("Team " + season.giveCurrentTeamList().get(index).readName() + " has been added at the Game " 
                + season.giveScheduleList().get(gameIndexCount).returnTerm() + " position " + 1);
            }
            if(season.giveScheduleList().get(gameIndexCount).returnGameTeamList().size() %2 == 0) {
                System.out.println("Team " + season.giveCurrentTeamList().get(index).readName() + " has been added at the Game " 
                + season.giveScheduleList().get(gameIndexCount).returnTerm() + " position " + 2);
            }

            season.giveCurrentTeamList().remove(index);
        }

        if(check == false) {
            System.out.println("No such team! Please try again");
        }   
    }

    private void manageTeam() {
        boolean check = false;
        System.out.print("Please enter the team's name that you want to manage: ");
        String manageTeamInput = In.nextLine();

        for (int i=0; i<teams.teams.size(); i++) {
            if (manageTeamInput.equals(teams.teams.get(i).readName())) {
                teamsMenu(manageTeamInput);
                check = true;
            }
        }
        if (check == false) {
            System.out.println("Team does not exist!");
        }
        if(manageTeamInput.equals("R")) {
            System.out.println("Team does not exist!");
        }
    }

    private void teamsMenu(String teamName) {
        String teamsMenuChoice = readChoice4(teamName);

        while(!(teamsMenuChoice.equals("R"))) {
            switch(teamsMenuChoice) {
                case "1": displayTeamPlayers(teamName); break;
                case "2": addPlayerToTeam(teamName); break;
                case "3": updateExistingPlayer(teamName); break;
                case "4": deleteExistingPlayer(teamName); break;
                case "R": break;
                default: break;
            }
            teamsMenuChoice = readChoice4(teamName);
        }
    }

    private String readChoice1() {
        System.out.println("Welcome to the Association! Please make a selection from the menu:");
        System.out.println("1. Explore the teams.");
        System.out.println("2. Arrange a new season.");
        System.out.println("X. Exit the system.");
        System.out.print("Enter a choice: ");
        return (In.nextLine());
    }

    private String readChoice2() {
        System.out.println("Welcome to the Teams Page! Please make a selection from the menu:");
        System.out.println("1. Display all teams.");
        System.out.println("2. Display all players.");
        System.out.println("3. Add a new team.");
        System.out.println("4. Manage an existing team.");
        System.out.println("5. Delete an existing team.");
        System.out.println("6. Display all players by an level.");
        System.out.println("R. Return to previous menu.");
        System.out.print("Enter a choice: ");
        return (In.nextLine());
    }

    private String readChoice3() {
        System.out.println("Welcome to the season page! Please make a selection from the menu:");
        System.out.println("1. Add a team to the round.");
        System.out.println("2. Display the current round.");
        System.out.println("3. Play games.");
        System.out.println("4. Display the game result records.");
        System.out.println("R. Return to previous menu.");
        System.out.print("Enter a choice: ");
        return (In.nextLine());
    }

    private String readChoice4(String teamName) {
        System.out.println("Welcome to the " + teamName + " 's Page! Please make a selection from the menu:");
        System.out.println("1. Display team's players.");
        System.out.println("2. Add a new player.");
        System.out.println("3. Update an existing player.");
        System.out.println("4. Delete an existing player.");
        System.out.println("R. Return to previous menu.");
        System.out.print("Enter a choice: ");
        return (In.nextLine());
    }

    private void displayTeamPlayers(String teamName) {
        Utils.playerHeader();

        for (int j=0; j<teams.teams.size(); j++) {
            int teamNum = j;
            
            if (teams.teams.get(teamNum).readName().equals(teamName)) {
                for (int i = 0; i<(teams.teams.get(teamNum).teamPlayers().giveList().size()); i++) {
                    System.out.format(Utils.PlayerFormat, teams.teams.get(teamNum).teamPlayers().giveList().get(i).readPlayerName(), teams.teams.get(teamNum).teamPlayers().giveList().get(i).readPlayerCredit(), teams.teams.get(teamNum).teamPlayers().giveList().get(i).readPlayerLevel(),
                    teams.teams.get(teamNum).teamPlayers().giveList().get(i).readPlayerNo(), teams.teams.get(teamNum).teamPlayers().giveList().get(i).readPlayerAge());
                }
            }
        }
        Utils.playerTableEnd();
    }

    private void addPlayerToTeam(String teamName) {
        System.out.print("Please enter the player's name: ");
        String playerName = In.nextLine();
        System.out.print("Please enter the player's credit: ");
        Double playerCredit = In.nextDouble();
        System.out.print("Please enter the player's age: ");
        int playerAge = In.nextInt();
        System.out.print("Please enter the player's No: ");
        int playerNo = In.nextInt();

        for (int j=0; j<teams.teams.size(); j++) {
            int teamNum = j;
            
            if (teams.teams.get(teamNum).readName().equals(teamName)) {
                for (int i = 0; i<(teams.teams.get(teamNum).teamPlayers().giveList().size()); i++) {
                    while(playerNo != teams.teams.get(teamNum).teamPlayers().giveList().get(i).readPlayerNo()) {
                        if(teams.teams.get(teamNum).teamPlayers().nameOfPlayerWithSameNo(playerNo).equals("")) {
                            break;
                        }
                        System.out.print("This No has been occupied by: " + teams.teams.get(teamNum).teamPlayers().nameOfPlayerWithSameNo(playerNo) + ". Please re-enter the No: ");
                        playerNo = In.nextInt();
                    }
                }
            }
            teams.teams.get(teamNum).teamPlayers().addPlayer(new Player(playerName, playerCredit, playerAge, playerNo));
            break;
        }
        System.out.println("Player " + playerName + " added!");
    }

    private void updateExistingPlayer(String teamName) {
        boolean check = false;

        System.out.print("Please enter the player's name: ");
        String oldExistingPlayerName = In.nextLine();

        if(checkPlayerExists(teamName, oldExistingPlayerName) == false) {
            System.out.println("Player does not exist.");
        }
        else{
            System.out.print("Please enter the name: ");
            String newExistingPlayerName = In.nextLine();
            System.out.print("Please enter the credit: ");
            double updateExistingPlayerCredit = In.nextDouble();
            System.out.print("Please enter the age: ");
            int updateExistingPlayerAge = In.nextInt();
            System.out.print("Please enter the No: ");
            int updateExistingPlayerNo = In.nextInt();

            for (int j=0; j<teams.teams.size(); j++) {
                int teamNum = j;
            
                if (teams.teams.get(teamNum).readName().equals(teamName)) {
                    for (int i = 0; i<(teams.teams.get(teamNum).teamPlayers().giveList().size()); i++) {
                        if(teams.teams.get(teamNum).teamPlayers().giveList().get(i).readPlayerName().toLowerCase().equals(oldExistingPlayerName.toLowerCase())) {
                            teams.teams.get(teamNum).teamPlayers().giveList().get(i).updatePlayerName(newExistingPlayerName);
                            teams.teams.get(teamNum).teamPlayers().giveList().get(i).updatePlayerCredit(updateExistingPlayerCredit);
                            teams.teams.get(teamNum).teamPlayers().giveList().get(i).updatePlayerAge(updateExistingPlayerAge);

                            while(updateExistingPlayerNo != teams.teams.get(teamNum).teamPlayers().giveList().get(i).readPlayerNo()) {
                                if(teams.teams.get(teamNum).teamPlayers().nameOfPlayerWithSameNo(updateExistingPlayerNo).equals("")) {
                                    teams.teams.get(teamNum).teamPlayers().giveList().get(i).updatePlayerNo(updateExistingPlayerNo);
                                    check = true;
                                    break;
                                }
                                System.out.print("This No has been occupied by: " + teams.teams.get(teamNum).teamPlayers().nameOfPlayerWithSameNo(updateExistingPlayerNo) + ". Please re-enter the No: ");
                                updateExistingPlayerNo = In.nextInt();
                            }
                        }
                    }
                }
            }
            if(check == true) {
                System.out.println("Player information updated.");
            }
        }
    }

    private boolean checkPlayerExists(String teamName, String oldExistingPlayerName) {
        for (int j=0; j<teams.teams.size(); j++) {
            int teamNum = j;
            
            if (teams.teams.get(teamNum).readName().equals(teamName)) {
                for (int i = 0; i<(teams.teams.get(teamNum).teamPlayers().giveList().size()); i++) {
                    if(teams.teams.get(teamNum).teamPlayers().giveList().get(i).readPlayerName().toLowerCase().equals(oldExistingPlayerName.toLowerCase())) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private void deleteExistingPlayer(String teamName) {
        boolean check = false;
        System.out.print("Please enter the player's name: ");
        String deletePlayerName = In.nextLine();

        for (int j=0; j<teams.teams.size(); j++) {
            int teamNum = j;
            
            if (teams.teams.get(teamNum).readName().equals(teamName)) {
                
                for (int i = 0; i<(teams.teams.get(teamNum).teamPlayers().giveList().size()); i++) {
                    if(teams.teams.get(teamNum).teamPlayers().giveList().get(i).readPlayerName().equals(deletePlayerName)) {
                        teams.teams.get(teamNum).teamPlayers().giveList().remove(i);
                        System.out.println("Player deleted.");
                        check = true;
                    }
                }
                if (check == false) {
                    System.out.println("Player does not exist.");
                }
            }
        }
    }

    private void displayTeams() {
        Utils.teamsHeader();

        for (int i = 0; i<teams.teams.size(); i++) {
            System.out.format(Utils.teamsFormat, teams.teams.get(i).readName(), teams.teams.get(i).teamPlayers().NoofPlayers(), teams.teams.get(i).teamPlayers().avgPlayerCredit(), teams.teams.get(i).teamPlayers().avgPlayerAge());
        }
        Utils.teamTableEnd();
    }

    private void displayPlayers() {
        Utils.DisplayPlayerFromAllTeamsHeader();

        for (int j=0; j<teams.teams.size(); j++) {
            int teamNum = j;

            for (int i = 0; i<(teams.teams.get(teamNum).teamPlayers().giveList().size()); i++) {
                System.out.format(Utils.DisplayPlayerFromAllTeamsFormat, teams.teams.get(teamNum).teamPlayers().giveList().get(i).readPlayerName(), teams.teams.get(teamNum).teamPlayers().giveList().get(i).readPlayerCredit(), teams.teams.get(teamNum).teamPlayers().giveList().get(i).readPlayerLevel(),
                teams.teams.get(teamNum).teamPlayers().giveList().get(i).readPlayerAge(), teams.teams.get(teamNum).teamPlayers().giveList().get(i).readPlayerNo(), teams.teams.get(teamNum).readName());
            }
            Utils.DisplayPlayerFromAllTeamsEnd();
        }
    }

    private void addTeam() {
        System.out.print("Please enter the name of the team: ");
        
        String teamInput = In.nextLine();
        for (int i=0; i<teams.teams.size(); i++) {
            if (teamInput.equals(teams.teams.get(i).readName())) {
                System.out.print("Team " + teams.teams.get(i).readName() + " already exist! Please enter a new name: ");
                teamInput = In.nextLine();
            }
        }
        teams.addTeam(teamInput);
        System.out.println("Team " + teamInput + " added!");
    }

    private void deleteTeam() {
        boolean check = false;
        System.out.print("Please enter the team's name that you want to delete: ");
        String deleteTeamInput = In.nextLine();

        for (int j=0; j<teams.teams.size(); j++) {
            if(teams.teams.get(j).readName().equals(deleteTeamInput)) {
                System.out.println("The team " + deleteTeamInput + " has been deleted.");
                teams.teams.remove(j);
                check = true;
            }
            break;
        }
        if (check == false) {
            System.out.println("The team you want to delete does not exist!");
        }
    }

    private void displayAllPlayersByLv() {
        System.out.print("Please enter the player's level that you want to view: ");
        String playerLevel = In.nextLine();

        while(!(playerLevel.equals("Core") || playerLevel.equals("Edge") || playerLevel.equals("Common") || playerLevel.equals("All Star"))) {
            System.out.print("No such level! Please re-enter the level: ");
            playerLevel = In.nextLine();
        }

        Utils.DisplayPlayerFromAllTeamsHeader();
            for (int j=0; j<teams.teams.size(); j++) {
                int teamNum = j;
                for (int i = 0; i<(teams.teams.get(teamNum).teamPlayers().giveList().size()); i++) {
                    if(teams.teams.get(teamNum).teamPlayers().giveList().get(i).readPlayerLevel().equals(playerLevel)){
                        System.out.format(Utils.DisplayPlayerFromAllTeamsFormat, teams.teams.get(teamNum).teamPlayers().giveList().get(i).readPlayerName(), teams.teams.get(teamNum).teamPlayers().giveList().get(i).readPlayerCredit(),
                        teams.teams.get(teamNum).teamPlayers().giveList().get(i).returnPlayerLevel(playerLevel), teams.teams.get(teamNum).teamPlayers().giveList().get(i).readPlayerAge(), teams.teams.get(teamNum).teamPlayers().giveList().get(i).readPlayerNo(),
                        teams.teams.get(teamNum).readName());
                    }
                }
            }
        Utils.DisplayPlayerFromAllTeamsEnd();
    }
}
