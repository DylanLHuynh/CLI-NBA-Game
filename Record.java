public class Record
{
    private String WinTeam;
    private String LoseTeam;
    private Integer GameNo;
    private Integer Round;
    
    public Record(String WinTeam, String LoseTeam, int GameNo, int Round)
    {
        this.WinTeam = WinTeam;
        this.LoseTeam = LoseTeam;
        this.GameNo = GameNo;
        this.Round = Round;
    }

    public String returnWinningTeam() {
        return this.WinTeam;
    }

    public String returnLosingTeam() {
        return this.LoseTeam;
    }

    public Integer returnGameNo() {
        return this.GameNo;
    }

    public Integer Round() {
        return this.Round;
    }
}
