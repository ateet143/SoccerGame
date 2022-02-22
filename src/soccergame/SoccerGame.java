package soccergame;

public class SoccerGame {

    public static void main(String[] args) {
        
        Scheduler s1 = new Scheduler(4);
        s1.addTeams();
        s1.startOfSeason();
        
        // this foreach loop will execute the scoreGoals method so that each games gets scoring.
         for(Game game: s1.getGames()){
             s1.scoringGoals(game.getTemperature(), game);
         }
         
        s1.displayAllGamesResult();
        s1.displayTeamResult();
    }

}
