package soccergame;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;


public class Scheduler {

    private Team[] teams;              //array of object Team in teams 
    private ArrayList<Game> games;     // Arraylist storing games object
    int noOfTeams;                     //defines the number of teams in the tournament
    boolean endOfSeason;               // to terminate the season
    int count = 0;
    boolean gameFixed;
    private ArrayList<Double> temperatureRecords;
   

    public Scheduler(int noOfTeams) {
        this.noOfTeams = noOfTeams;
        teams = new Team[this.noOfTeams];
        games = new ArrayList<>();
        temperatureRecords = new ArrayList<>();
    }

    public Team[] getTeams() {
        return teams;
    }

    public ArrayList<Game> getGames() {
        return games;
    }
    
     public void addTeams() {   //method helps to add teams and store the each team in array teams.
         System.out.println("\nAdd Teams");
        int pointer =0;
        for (int i = 0; i < noOfTeams; i++) {
            Scanner sc = new Scanner(System.in);
            System.out.print(String.format("Team %d Name:", i + 1));
            String teamName = sc.nextLine();
          
            while (teamName.isEmpty() || teamName.isBlank()) {
                System.out.println("!Team name cannot be blank or empty");
                System.out.print(String.format("Team %d Name:", i + 1));
                teamName = sc.nextLine();
            }
              
            while(nameExist(teamName,pointer)){
               System.out.println("!Team Already exist, Please enter again");
               System.out.print(String.format("Team %d Name:", i + 1));
               teamName = sc.nextLine();
            }
            
             while(notDigitOrLetter(teamName)){
               System.out.println("!Team Name excepts Letter/Digit only.Please enter again");
               System.out.print(String.format("Team %d Name:", i + 1));
               teamName = sc.nextLine();
            }
            teams[i] = new Team(teamName);
            pointer++;
        }
    }
     
     public boolean nameExist(String teamName,int pointer){
         for(int i=0; i<pointer; i++){
             if(teamName.equalsIgnoreCase(teams[i].getName()))
                 return true;
         }
         return false;
     }
     
    public boolean notDigitOrLetter(String teamName) {
        char[] charArray = teamName.toCharArray();
         for(char c: charArray){
             if(!Character.isLetterOrDigit(c))
                 return true;
         } 
         return false;
    }

    public boolean gameFixture(double temperature) {             //it fix the game between the teams # creates the game object and add it to the arraylist
        int noOfGamesFixed =0;
        Random rnd = new Random();
        ArrayList<Integer> numbers = new ArrayList<>();       // to store the random number
        for (int i = 0; i < this.noOfTeams; i++) {
            int randomNumber = rnd.nextInt(this.noOfTeams);
            if (!numbers.contains(randomNumber)) {            // it will not add the random number until it is unique
                numbers.add(randomNumber);
            } else {                                          // if repeat then decrease the i variable 
                i--;
            }
        }
        for (int i = 0; i < numbers.size(); i += noOfTeams / 2) {  // this loop will create two games by executing only 2 times.
            games.add(new Game(this.teams[numbers.get(i)], this.teams[numbers.get(i + 1)], temperature));
            noOfGamesFixed++;
            gameFixed = true;
        }
        System.out.println(String.format("%d Games scheduled. (1 game for %d teams)",noOfGamesFixed,noOfTeams / 2));
        return gameFixed;
    }

    public void startOfSeason() {
        while (!endOfSeason) {
            try {
                Scanner sc = new Scanner(System.in);
                System.out.println("\nWhat is today's Temperature (in Fahrenheit)?");
                double temperature = Double.parseDouble(sc.nextLine());
                char c = (temperature <= 9) ? 'A' : (temperature > 9 && temperature <= 130) ? 'B' : 'C';
                switch (c) {
                    case 'A' -> {
                        System.out.println("Too cold to play.");
                        count++;
                        temperatureRecords.add(temperature);
                        if (count == 3) {
                            System.out.println("Season is over");
                            endOfSeason = true;
                        }
                    }
                    case 'B' -> {
                        gameFixture(temperature);
                        temperatureRecords.add(temperature);
                        count = 0; // to reset the value of count
                    }
                    default ->
                        System.out.println("Temperature cannot be more than 130F,Enter Again");
                }
            }catch (NumberFormatException ex) {
                System.out.println("-->Please Enter Digit Only<--");
            }
            catch(Exception ex){
                 System.out.println("Exception error:" + ex);
            }
        }
    }

    public void scoringGoals(double temperature, Game game) {  // to randomly generate the scoring for the teams in a game
        Random rnd = new Random();
        double maxGoal = (temperature < 0) ? 0 : temperature / 10;
        int teamAScore, teamBScore;

        if (maxGoal >= 3) {   // if the temperature is higher it will produce more goals
            teamAScore = rnd.nextInt((int)maxGoal) + 1;
            teamBScore = rnd.nextInt((int)maxGoal) + 1;
        } else {
            teamAScore = rnd.nextInt((int)maxGoal);
            teamBScore = rnd.nextInt((int)maxGoal);
        }
        //To set the Score of the teams
        game.setTeamAscore(teamAScore);
        game.setTeamBscore(teamBScore);
        // To store the win, loss and draw record of each team.
        if (teamAScore > teamBScore) {
            game.getTeamA().setTotalWin(1);
            game.getTeamB().setTotalLoss(1);
        } else if (teamAScore < teamBScore) {
            game.getTeamB().setTotalWin(1);
            game.getTeamA().setTotalLoss(1);
        } else {
            game.getTeamA().setTotalTie(1);
            game.getTeamB().setTotalTie(1);
        }
        //to record the total goal scored and allowed of each team
        game.getTeamA().setTotalGoalScored(teamAScore);
        game.getTeamA().setTotalGoalAllowed(teamBScore);
        game.getTeamB().setTotalGoalScored(teamBScore);
        game.getTeamB().setTotalGoalAllowed(teamAScore);

    }

    public void displayAllGamesResult() {
        if (!this.gameFixed) {
            System.out.println("\n!!!No any games played:");
        } else {
            System.out.println("\n####GAMES RESULT####");
            String gameNo = "Game", teamA = "Home Team", teamB = "Away Team", temperature = "Temperature";
            int a, b, c;
            double d;
            System.out.println(String.format("%s %12s %12s %12s", gameNo, teamA, teamB, temperature));
            for(Game game: games){
                a = game.getGameNumber();
                b = game.getTeamAscore();
                c = game.getTeamBscore();
                d = game.getTemperature();
                System.out.println(String.format("%2d %10d %12d %12.2f", a, b, c, d));
            }
        }
            System.out.println(getTemperatureData());
    }

    public void displayTeamResult() {
        if (!this.gameFixed) {
            System.out.println("\n!!!No any games played:");
        } else {
            System.out.println("\n####TEAMS RESULT####");
            String teamName = "Name", totalGoal = "Goal_Scored", totalGoalAllowed = "Goal_Allowed", win = "Win", loss = "Loss", draw = "Draw";
            System.out.println(String.format("%s %17s  %12s %5s %5s %5s", teamName, totalGoal, totalGoalAllowed, win, loss, draw));

            for (Team team : this.teams) {
                String tempString = team.getName();
                for (int j = tempString.length(); j < 4; j++) {  //To add spaces to the string if less than 3 of length(to fit the text in teams result method)
                    tempString += " ";
                }
                String name = tempString.substring(0, 3); // To only show three letter of the team name

                int gScored = team.getTotalGoalScored();
                int gAllowed = team.getTotalGoalAllowed();
                int tWin = team.getTotalWin();
                int tLoss = team.getTotalLoss();
                int tDraw = team.getTotalTie();
                System.out.println(String.format("%s %12d %13d %10d %5d %5d", name, gScored, gAllowed, tWin, tLoss, tDraw));
            }
        }

    }
    
    public String getTemperatureData(){
        double minValue = Integer.MAX_VALUE;
        double maxValue = Integer.MIN_VALUE;
        double sum =0;
        double averageTemperature =0;
        for(double temperature:temperatureRecords ){
             if(minValue > temperature){
                minValue = temperature;
            }
            else if(maxValue < temperature){
                maxValue = temperature;
            }
           sum +=  temperature;
        }
        averageTemperature = sum/temperatureRecords.size();
        return String.format("Minimum Temperature:%.2f" +"\nMaximum Temperature:%.2f" + "\nAverage Temperature:%.2f",minValue,maxValue,averageTemperature );
    }
}
