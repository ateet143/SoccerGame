package soccergame;

public class Team {

    private String name;
    private int totalWin;
    private int totalLoss;
    private int totalTie;
    private int totalGoalScored;
    private int totalGoalAllowed;

    public Team(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTotalWin() {
        return totalWin;
    }

    public int getTotalLoss() {
        return totalLoss;
    }

    public int getTotalTie() {
        return totalTie;
    }

    public int getTotalGoalScored() {
        return totalGoalScored;
    }

    public int getTotalGoalAllowed() {
        return totalGoalAllowed;
    }
    
     public void setTotalWin(int totalWin) {
        this.totalWin += totalWin;
    }

    public void setTotalLoss(int totalLoss) {
        this.totalLoss += totalLoss;
    }

    public void setTotalTie(int totalTie) {
        this.totalTie += totalTie;
    }

    public void setTotalGoalScored(int totalGoalScored) {
        this.totalGoalScored += totalGoalScored;
    }

    public void setTotalGoalAllowed(int totalGoalAllowed) {
        this.totalGoalAllowed += totalGoalAllowed;
    }
}
