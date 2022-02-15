package soccergame;

public class SoccerGame {

    public static void main(String[] args) {

       
        Scheduler s1 = new Scheduler(4);
        s1.addTeams();
        s1.startOfSeason();
        for (int i = 0; i < s1.getGames().size(); i++) {
            s1.scoringGoals(s1.getGames().get(i).getTemperature(), s1.getGames().get(i));

        }

        s1.displayAllGamesResult();
        s1.displayTeamResult();
    }

}
