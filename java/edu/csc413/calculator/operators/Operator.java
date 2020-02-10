package edu.csc413.calculator.operators;

import edu.csc413.calculator.evaluator.Operand;

import java.util.HashMap;

public abstract class Operator {
    // The Operator class should contain an instance of a HashMap.
    // The keys of the map will be the tokens we're interested in,
    // and values will be instances of Operator.
    // ALL subclasses of operator MUST be in their own file.
    // 
    // Where does this declaration go?
    // What should its access level be?
    // Class or instance variable?
    // Is this the right declaration?
    // HashMap operators = new HashMap();
    // operators.put( "+", new AdditionOperator() );
    // operators.put( "-", new SubtractionOperator() );

    /*HashMap<String, Operator> map = new HashMap<String, Operator>();
    map.put("+", new AddOperator());*/
    private static HashMap<String, Operator> map;
    static{
        map = new HashMap<String, Operator>();
        map.put("+", new AddOperator());
        map.put("-", new SubtractOperator());
        map.put ("*", new MultiplyOperator());
        map.put("/", new DivideOperator());
        map.put("^", new PowerOperator());
        map.put("(", new OpenParenthesisOperator());
        map.put(")", new CloseParenthesisOperator());
    }

    /**
     * used to get the priority of an operator
     *
     * @return as an int, priority of operator
     */
    public abstract int priority();

    public abstract Operand execute(Operand op1, Operand op2); //I set execute method as abstract because I made execute on the sub classes instead.


    /**
     * determines if a given token is a valid operator.
     * please do your best to avoid static checks
     * for example token.equals("+") and so on.
     * Think about what happens if we add more operators.
     */
    public static boolean check(String token) {
        if(map.containsKey(token)){ // checks if token is an operator within the hashmap
            return true;
        }
        return false;
    }

    /**
     * used to retrieve an operator from our HashMap.
     * This will act as out publicly facing function,
     * granting access to the Operator HashMap.
     *
     * @param token key of the operator we want to retrieve
     * @return reference to a Operator instance.
     */
    public static Operator getOperator(String token) {
        return map.get(token);
    }
}
