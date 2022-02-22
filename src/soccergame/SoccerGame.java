package soccergame;

public class SoccerGame {

    public static void main(String[] args) {
        
        Scheduler s1 = new Scheduler(4);
        s1.addTeams();
        s1.startOfSeason();
        s1.displayAllGamesResult();
        s1.displayTeamResult();
    }

}
