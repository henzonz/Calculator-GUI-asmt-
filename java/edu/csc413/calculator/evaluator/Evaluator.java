package edu.csc413.calculator.evaluator;


import edu.csc413.calculator.operators.Operator;
import java.util.Stack;
import java.util.StringTokenizer;

public class Evaluator {
    private Stack<Operand> operandStack;
    private Stack<Operator> operatorStack;
    private StringTokenizer tokenizer;
    private static final String DELIMITERS = "()+-*^/ ";

    public Evaluator() {
        operandStack = new Stack<>();
        operatorStack = new Stack<>();
    }

    public int eval(String expression) {
        String token;

        // The 3rd argument is true to indicate that the delimiters should be used
        // as tokens, too. But, we'll need to remember to filter out spaces.
        this.tokenizer = new StringTokenizer(expression, DELIMITERS, true);



        while (this.tokenizer.hasMoreTokens()) {
            // filter out spaces
            if (!(token = this.tokenizer.nextToken()).equals(" ")) {
                // check if token is an operand

                if (Operand.check(token)) { //checks if token is a number
                    //if token is an operand, push to operand stack
                    operandStack.push(new Operand(token));
                } else {    //if token is not a number:
                    if (!Operator.check(token)) {   //if its not an operator, print error
                        System.out.println("*****invalid token******");
                        throw new RuntimeException("*****invalid token******");
                    }else {  //if token is an operator
                        Operator newOperator = Operator.getOperator(token);
                        if (newOperator.equals(Operator.getOperator("("))) { //if operator is an open parenthesis, push new operator
                            operatorStack.push(newOperator);
                        } else if (newOperator == Operator.getOperator(")")) {   //if operator is a close parenthesis
                            while (!(operatorStack.peek().equals(Operator.getOperator("(")))) { //while top operator stack is not an open parenthesis do:
                                process();
                                //push result to the value stack
                            }
                            operatorStack.pop();
                        } else {


                            // TODO Operator is abstract - these two lines will need to be fixed:
                            // The Operator class should contain an instance of a HashMap,
                            // and values will be instances of the Operators.  See Operator class
                            // skeleton for an example.
                            //Operator newOperator = Operator.getOperator(token);
                            while (operatorStack.isEmpty() == false && operatorStack.peek().priority() >= newOperator.priority()) {
                                // note that when we eval the expression 1 - 2 we will
                                // push the 1 then the 2 and then do the subtraction operation
                                // This means that the first number to be popped is the
                                // second operand, not the first operand - see the following code
                                process();

                            }
                            operatorStack.push(newOperator);
                        }
                    }

                }
            }
        }


        while (operatorStack.isEmpty() == false) {  //while the operator stack is not empty
            process();
        }
            // Control gets here when we've picked up all of the tokens; you must add
            // code to complete the evaluation - consider how the code given here
            // will evaluate the expression 1+2*3
            // When we have no more tokens to scan, the operand stack will contain 1 2
            // and the operator stack will have + * with 2 and * on the top;
            // In order to complete the evaluation we must empty the stacks,
            // that is, we should keep evaluating the operator stack until it is empty;
            // Suggestion: create a method that processes the operator stack until empty.

            //Don't forget to change the return value!
            return operandStack.pop().getValue();
        }
        private void process(){
            Operator operator = operatorStack.pop();
            Operand value1 = operandStack.pop(); //pops the first value from operandstack (push this last)
            Operand value2 = operandStack.pop(); //pops the second value from operandstack (push this first)
            operandStack.push(operator.execute(value2, value1));
        }

}
