package edu.csc413.calculator.evaluator;

/**
 * Operand class used to represent an operand
 * in a valid mathematical expression.
 */
public class Operand {
   private int operand;
    /**
     * construct operand from string token.
     */
    public Operand(String token) {
        //int operand;
        operand = Integer.parseInt(token); //converts token string to int


    }

    /**
     * construct operand from integer
     */
    public Operand(int value) {
        //operand = Integer.parseInt(value);
        operand = value;

    }

    /**
     * return value of operand
     */
    public int getValue() {
        return operand;
    }

    /**
     * Check to see if given token is a valid
     * operand.
     */
    public static boolean check(String token) {
        try{
        Integer.parseInt(token);
        }catch(Exception e){
        return false;
        }
        return true;
    }
}
