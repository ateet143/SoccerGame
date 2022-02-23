package soccergame;

public class Game {
    private final int gameNumber;
    private Team teamA;
    private Team teamB;
    private int teamAscore;
    private int teamBscore;
    private double temperature;
    private static int counter;
  

    public Game(Team teamA, Team teamB, double temperature) {
        counter++;
        gameNumber = counter;
        this.teamA = teamA;
        this.teamB = teamB;
        this.temperature = temperature;
    }

    public int getGameNumber() {
        return gameNumber;
    }

    public Team getTeamA() {
        return teamA;
    }
    
    public Team getTeamB() {
        return teamB;
    }
  
    public int getTeamAscore() {
        return teamAscore;
    }

    public int getTeamBscore() {
        return teamBscore;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTeamAscore(int teamAscore) {
        this.teamAscore = teamAscore;
    }

    public void setTeamBscore(int teamBscore) {
        this.teamBscore = teamBscore;
    }
   
}
