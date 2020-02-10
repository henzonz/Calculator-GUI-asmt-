package edu.csc413.calculator.operators;
import edu.csc413.calculator.evaluator.Operand;

public class PowerOperator extends Operator {
    @Override

    public int priority() {
        return 3;
    }

    public Operand execute(Operand op1, Operand op2) {
        //tried for loop but too messy
        Operand result = new Operand((int) Math.pow(op1.getValue(), op2.getValue())); //casted to int because math.pow uses double but getValue method is an int.
        return result;
    }
}




