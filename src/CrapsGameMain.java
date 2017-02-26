
/**
 * .5 This is the craps program on 10-15-14 We have abided by the academic honor
 * code Ochaun Marshall & Max Bushman
 */
import java.io.*;
import java.util.*;

public class CrapsGameMain {
    // Hide annoying message from NetBeans
    @SuppressWarnings("ConvertToTryWithResources")
    public static void main(String[] args) throws IOException {
        System.out.println("Welcome to the game!");
        
        // Objects and boolean expressions
        boolean tryAgainDecision = true;
        Scanner kb = new Scanner(System.in);
        Random rand = new Random();
        DoubleDice die = new DoubleDice();
        FileWriter fwrite = new FileWriter("crapOutput.txt", true);
        PrintWriter outfile = new PrintWriter(fwrite);

        // Setting default money ammounts
        double money = 1000;
        long compmoney = 1000000000;

        //Instructions 
        System.out.println("You have 1000 Yen "
                + "（￥） to bet and yes you can play even if your in debt.");
        System.out.println("Now you have two options of where to bet.");
        System.out.println("1. That you'll win in the first roll");
        System.out.println("2. That you'll win with a point roll");
        System.out.println("You can bet in one or both, but you must wager"
                + "at least ￥5 total");
        System.out.println(" ");
        outfile.println("Welcome to the game."
                + "All transactions are in ￥"
                + "All transactions are final... \n until you start "
                + "the game again...");
        
        do { //Try again do-while

            /**
             * Betting system implementation Code segment simulates "Casino
             * Style" Betting https://www.youtube.com/watch?v=i_IVEvX__3s
             */
            System.out.println("How much do you want to bet on first roll:");
            double bet1 = kb.nextDouble();
            System.out.println("How much would you like to bet on a point roll:");
            double bet2 = kb.nextDouble();

            //Betting calculation and conditional statements
            double bet = bet1 + bet2;
            if (bet < 5) {
                System.out.println("Sorry you must bet at least ￥5 Total.");
            } else if (bet1 > 1000) {
                System.out.println("Sorry you can only bet ￥1000 at a time.");
            } else if (bet1 < 0 || bet2 < 0) {
                System.out.println("You cannot bet negative numbers Cheater!");
            } else {
                money -= bet1 + bet2;
                System.out.print("You bet ￥" + bet + " " 
                        + "You have ￥" + money + " left.");
                outfile.println("User bet " + bet1 + " on first roll and " + bet2
                        +" on the point roll");

                // Computer automatically places bet between 1 - 500
                double compbet = rand.nextInt(500) + 1;
                compmoney -= compbet;
                // Comp bet not shown in game only in file
                System.out.println("The computer has bet against you...");
                System.out.println("");
                outfile.println("Computer bet: " + compbet);
                
                // Variable fr does nothing 
                // Just shows when conditional tree is in firstroll 
                boolean fr = true;

                // "roll" mechanic to trigger random number generation
                System.out.println("Lets get rolling (pun very much intended)");
                System.out.println("Type 'roll' and then press enter to roll.");
                String userThrow = kb.next();
                outfile.println("The game starts...");

                // Stings for storing roll & int to store dice roll value
                int userface = die.rolldice();
                String userThrow2, userPointThrow;
                outfile.println("User rolled " + userface +" first.");
                
                
                // Testing for proper input
                if (userThrow.equalsIgnoreCase("roll") == false) {
                    System.out.println("That's not how you spell it dood, "
                            + "type 'roll' to throw the dice.");
                    userThrow = kb.next();
                }
                if (userThrow.equalsIgnoreCase("roll")) {
                    if (userface == 7 || userface == 11) {
                        System.out.println("You won on First roll!");
                        outfile.println("Won on first roll. Wow...");
                        // Putting funds in the proper accounts
                        money += bet1 + compbet;
                        compmoney += bet2;
                        System.out.println("You won: " + (bet1 + compbet));
                    } else if (userface == 2 || userface == 3 || userface == 12) {
                        System.out.println("You lost on first roll!");
                        die.showdicepic(userface);
                        outfile.println("User lost " + bet + " on first roll."
                                + " Wow...");
                        // COmputer takes all the money
                        compmoney += bet + compbet;         
                    } else {
                        fr = false;
                        System.out.println("You did not win on first roll");
                        int playerpoint = userface;
                        System.out.println("Point is now set at: " + playerpoint);
                        int userface2 = die.rolldice();
                        System.out.println(" ");
                        System.out.println("It's your roll again.(Type 'roll')");
                        System.out.println("(If it doesn't work "
                                + "the 1st time please type again)");

                        // Loop for point win or loss
                        do {
                            userThrow2 = kb.next();
                            // Checking for proper input
                            if (userThrow2.equalsIgnoreCase("roll") == false) {
                                System.out.println("That's not how you spell it dood, "
                                        + "type 'roll' to throw the dice.");
                                userThrow2 = kb.next();

                            }
                            // If player rolls point again they win
                            if (userface2 == playerpoint
                                    && userThrow2.equalsIgnoreCase("roll")) {
                                System.out.println("Awesome!");
                                break;
                                // or they can lose...    
                            } else if (userface2 == 7
                                    && userThrow2.equalsIgnoreCase("roll")) {
                                System.out.println("Aww...");
                                break;
                                // Roll again for new value in loop
                            } else {
                                System.out.println("You rolled a: " + userface2);
                                userface2 = die.rolldice();
                                die.showdicepic(userface);
                                outfile.println("User rolled: " + userface2);
                            }
                            System.out.println("Roll again.(Type 'roll')");
                            userPointThrow = kb.next();
                        } while (userface2 != playerpoint && userface2 != 7
                                && userPointThrow.equalsIgnoreCase("roll") == true);

                        // Double checking conditional face value 
                        System.out.println("User rolled a: " + userface2);
                        die.showdicepic(userface2);
                        System.out.println(" ");
                        if (userface2 == 7) {
                            System.out.println("Rolled a 7. Too bad so sad.");
                            System.out.println("Computer earns your bet!");
                            compmoney += bet + compbet;
                              outfile.println("User lost " + bet);
                        } else {
                            System.out.println("You win from point");
                            money += bet2 + compbet;
                            compmoney += bet1;
                            System.out.println("You win: ￥" + (bet2 + compbet));
                            outfile.println("User won: " + (bet2 + compbet));
                        }
                    }
                }
            }

            // Conditional statement to decide if game ends or not. 
            System.out.println(" ");
            System.out.println("Would you like to play again? (Type: True or "
                    + "False)");
            boolean tryagain = kb.nextBoolean();
            System.out.println(" ");
            if (tryagain == false) {
                break;
            }
        } while (tryAgainDecision == true);
        System.out.println("Well a record of your Victories/Defeats has been"
                + " recorded. \nGoodbye");
        outfile.println("________________________________________________");
        outfile.close();
    }
}
