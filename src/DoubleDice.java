/*
 * This class is inteneded to geberate an object that simmulates
 * two dice objects for a game that requires two dice. 
 * 
 */

/**
 *
 * @author oemarsha
 */
import java.util.*;
import java.io.*;

public class DoubleDice {

    private int sides;
    private boolean firstroll;
    final private String FACE1 = "\u2680";
    final private String FACE2 = "\u2681";
    final private String FACE3 = "\u2682";
    final private String FACE4 = "\u2683";
    final private String FACE5 = "\u2684";
    final private String FACE6 = "\u2685";
    Random rand = new Random();
    PrintStream out = System.out;

    /**
     * Default Constructor
     */
    public DoubleDice() {
        sides = 12;
        firstroll = true;
    }

    /**
     * Programmer Defined constructor
     *
     * @param s
     * @param fr
     */
    public DoubleDice(int s, boolean fr) {
        sides = s;
        firstroll = fr;
    }

    /**
     * Displays whether or not this roll is the first roll.
     *
     * @return
     */
    public boolean getfirstroll() {
        return firstroll;
    }

    /**
     * Displays how many sides the current dice object has
     *
     * @return
     */
    public int getsides() {
        return sides;
    }

    /**
     * Changes whether or not current roll is first roll
     *
     * @param fr
     */
    public void setroll(boolean fr) {
        fr = firstroll;
    }

    /**
     * Allows one to change the number of sides for the dice; Value must be
     * greater than one.
     *
     * @param s
     */
    public void setsides(int s) {
        if (s > 1) {
            sides = s;
        } else {
            System.out.println("Sides of bust be an integer greater than 1");
            System.out.println("Sides will not be changed");
        }
    }

    /**
     * Generates random number value between two and twelve
     *
     * @return
     */
    public int rolldice() {      // 
        int face = rand.nextInt(11) + 2;
        return face;
    //Change UML TO set functionallity of this varible    

    }

    /**
     * Display Unicode dice image when done.
     *
     * @param face
     */
    public void showdicepic(int face) {
        // Will show a unicode image of a dice when called with sout statement
        switch (face) {
            case 2:
                System.out.println(FACE1 + FACE1);
                break;
            case 3:
                System.out.println(FACE1 + FACE2);
                break;
            case 4:
                System.out.println(FACE2 + FACE2);
                break;
            case 5:
                System.out.println(FACE1 + FACE4);
                break;
            case 6:
                System.out.println(FACE3 + FACE3);
                break;
            case 7:
                System.out.println(FACE2 + FACE5);
                break;
            case 8:
                System.out.println(FACE4 + FACE4);
                break;
            case 9:
                System.out.println(FACE5 + FACE4);
                break;
            case 10:
                System.out.println(FACE5 + FACE5);
                break;
            case 11:
                System.out.println(FACE6 + FACE5);
                break;
            case 12:
                System.out.println(FACE6 + FACE6);
                break;

        }
    }

    /**
     * Shows current status of dice object.
     */
    public void showdice() {
        // SHows output generaged by dice
        System.out.println("First roll?" + firstroll +
                " Number of Sides:" + sides);
    }

    /**
     * If you are using a dice that has sides not equal to twelve then use this
     * roll to roll your dice
     *
     * @return
     */
    public int customroll() {
        int face = rand.nextInt(sides) + 2;
        return face;
    }
}
