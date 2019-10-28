//Isaac Espinoza
//Nolan Shikanai
//Fatima Siddiqua
//CS 200-1, Fall 2018
import java.util.Scanner;

public class Yahtzee_PartIII
{
   public static void main(String[] args)
   {
      int player = 1;
      int[][] scores = initializeScores();
      int[] diceStatus;
      int category, score;
      for (int i = 0; i < 13; i++)
      {
         diceStatus = initializeDiceStatus();
         player = 1;
         printAllScores(player, createScoreDescription(), scores); 
         System.out.println();
         oneTurn(player, diceStatus);
         category = getCategory(player, scores);
         score = getScore();
         scores =  updateScores(scores, player, category, score);
         System.out.println();
         
         player = 2;
         diceStatus = initializeDiceStatus();
         printAllScores(player, createScoreDescription(), scores); 
         System.out.println();
         oneTurn(player, diceStatus);
         category = getCategory(player, scores);
         score = getScore();
         scores =  updateScores(scores, player, category, score);
         System.out.println();
      }
      int player1Score = 0, player2Score = 0;
      player1Score = totalScore(scores, 0);
      player2Score = totalScore(scores, 1);
      printGameResult(player1Score, player2Score);
      
      
   }
   public static int totalScore(int[][] scores, int player)
   {
      int total = 0;
      for (int i = 0; i < 13; i++)
         total += scores[player][i];
      return total;
   }     
   public static int getScore()
   {
      int score = 0;
      
      Scanner kb = new Scanner(System.in);
      
      System.out.print("Enter score: ");
      score = kb.nextInt();
      
      return score;
   }
   
   public static void printScoreValue(int x)
   {
      if (x == -1)
      {
         System.out.println("Score: N/A");
      }
      else
      {
         System.out.println("Score: " + x);
      }
   }
   
   public static void printGameResult(int sum1, int sum2)
   {
      System.out.println("Player 1: " + sum1);
      System.out.println("Player 2: " + sum2);
      
      if (sum1 == sum2)
      {
         System.out.println("It's a tie!");
      }
      else if (sum1 > sum2)
      {
         System.out.println("Player 1 wins!");
      }
      else
      {
         System.out.println("Player 2 wins!");
      }
   }
   
   public static String[] createScoreDescription()
   {
      String[] desc = {"Ones (Sum 1s)", "Twos (sum 2s)", "Threes (sums 3s)", "Fours (sum 4s)", 
                       "Fives (sum 5s)", "Sixes (sum 6s)", "Three of a kind (sum all dice)", 
                       "Four of kind (sum all dice)", "Full House (25 points)", "Small Straight (30 points)",
                       "Large Straight (40 points)", "Chance (summ all dice)", "Yahtzee (50 points)"};
                       
      return desc;
   }
   
   public static int[] initializeDiceStatus()
   {
     int[] arr = {1,1,1,1,1};
     
     return arr;
   }
   
   public static void roll (int[] dice, int[] diceStatus)
   {
      for (int i = 0; i < 5; i++)
      {
         if (diceStatus[i] == 1)
         {
            double n = Math.random();
            dice[i] = (int)(n * 6 + 1);
            diceStatus[i] = 0;
         }
      }
      
      for (int i = 0; i < dice.length;i++)
      {
         System.out.print(dice[i] + " ");
      }
   }
   
   public static void  setDiceStatusForRollAgain (int numDice, int [] diceStatus)
   {
      Scanner input = new Scanner (System.in);
      int x = 0; 
         for (int i = 0; i < numDice; i++)
         {
            System.out.print("Whice dice? Enter 1 - 5: ");
            x = input.nextInt();
            diceStatus[x -1] = 1;
         }     
   }
   
   public static int handleRollAgain(int roll, int [] diceStatus)
   {
      Scanner input = new Scanner(System.in);
      int user = 0, dice;
      if (roll <= 3)
      {
         System.out.print("Do you want to roll again? \n (0 - no, 1 - yes): ");
         user = input.nextInt(); 
      }
      
      if (user == 1)
      {
         System.out.print("How many dice? ");
         dice = input.nextInt();
         setDiceStatusForRollAgain(dice, diceStatus); 
      }
      
      return user;
   }
   
   public static void oneTurn (int player, int [] diceStatus)
   {
      int roll = 1;
      int [] dice = {0, 0, 0, 0, 0};
      int user = 1;
      
      do
      {
         if (roll <= 2)
         {
            System.out.print("Playes " + player + " - Dice roll: ");
            roll(dice, diceStatus);
            System.out.println();
            user = handleRollAgain(roll, diceStatus);
            roll++;
            
         }
         else
         {
            System.out.print("Playes " + player + " - Dice roll: ");
            roll(dice, diceStatus);
            roll++;
            
         }
      }while(roll <= 3 && user != 0);
      System.out.println();
   }
   public static int[][] initializeScores()
   {
      int[][] initializeScores = { { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 },
                                    { -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 } };
      return initializeScores;
   }
   
   public static int getCategory(int player, int[][] scores)
   {
      Scanner kb = new Scanner(System.in);
      boolean validCategory = false;
      int userN;
      do
      {
         System.out.print("Enter a category (1-13): ");
         userN = kb.nextInt();
         if (scores[player - 1][userN - 1] == -1)
            validCategory = true;
         else
            System.out.println("Category already has a score");
      }while (!validCategory);
      return userN;
   }
   public static void printAllScores(int player, String[] descriptions, int[][] scores)
   {
      System.out.println("Player " + player + " Categories/Current Scores: ");
      for (int i = 0; i < scores[player - 1].length; i++)
      {
         if (scores[player - 1][i] == -1)
            System.out.println(descriptions[i] + " (" + (i + 1) + ") Score: N/A" );
         else
            System.out.println(descriptions[i] + " (" + (i + 1) + ") Score: " + scores[player - 1][i] );
      }
   }
   public static int[][] updateScores(int[][] scores, int player, int category, int score)
   {
      scores[player-1][category-1] = score;
      return scores;
   }

}

