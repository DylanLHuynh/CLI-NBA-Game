public class Player {
    private String name;
    private Double credit;
    private String level;
    private Integer age;
    private String team;
    private Integer No;
    
    public Player(String name, Double credit, Integer age, Integer No)
    {
        this.name = name;
        this.credit = credit;
        this.age = age;
        this.No = No;
    }

    public String readPlayerName() {
        return(this.name);
    }

    public void updatePlayerName(String newPlayerName) {
        this.name = newPlayerName;
    }

    public Double readPlayerCredit() {
        return(this.credit);
    }

    public void updatePlayerCredit(double newPlayerCredit) {
        this.credit = newPlayerCredit;
    }

    public void addPlayerCredit(double creditAmount) {
        this.credit += creditAmount;
    }

    public void minusPlayerCredit(double creditAmount) {
        this.credit -= creditAmount;
    }

    public Integer readPlayerAge() {
        return(this.age);
    }

    public void updatePlayerAge(int newPlayerAge) {
        this.age = newPlayerAge;
    }
    
    public Integer readPlayerNo() {
        return(this.No);
    }

    public void updatePlayerNo(int newPlayerNo) {
        this.No = newPlayerNo;
    }
    
    public String readPlayerLevel() {
        if(this.credit<1000) {this.level = "Edge";}
        if(1000<=this.credit && this.credit<1500) {this.level = "Common";}
        if(1500<=this.credit && this.credit<2000) {this.level = "Core";}
        if(this.credit>=2000) {this.level = "All Star";}
        return(this.level);
    }

    public String returnPlayerLevel(String playerLevel) {
        if (this.level.equals(playerLevel)) {return this.level;}
        return "";
    }

}
