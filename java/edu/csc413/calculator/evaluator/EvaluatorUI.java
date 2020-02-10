package edu.csc413.calculator.evaluator;



import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EvaluatorUI extends JFrame implements ActionListener {

    private TextField txField = new TextField();
    private Panel buttonPanel = new Panel();

    // total of 20 buttons on the calculator,
    // numbered from left to right, top to bottom
    // bText[] array contains the text for corresponding buttons
    private static final String[] bText = {
            "7", "8", "9", "+", "4", "5", "6", "- ", "1", "2", "3",
            "*", "0", "^", "=", "/", "(", ")", "C", "CE"
    };

    /**
     * C  is for clear, clears entire expression
     * CE is for clear expression (or entry), clears last entry up until the last operator.
     */
    private Button[] buttons = new Button[bText.length];

    public static void main(String argv[]) {
        EvaluatorUI calc = new EvaluatorUI();
    }

    public EvaluatorUI() {
        setLayout(new BorderLayout());
        this.txField.setPreferredSize(new Dimension(600, 50));
        this.txField.setFont(new Font("Courier", Font.BOLD, 28));

        add(txField, BorderLayout.NORTH);
        txField.setEditable(false);

        add(buttonPanel, BorderLayout.CENTER);
        buttonPanel.setLayout(new GridLayout(5, 4));

        //create 20 buttons with corresponding text in bText[] array
        Button bt;
        for (int i = 0; i < EvaluatorUI.bText.length; i++) {
            bt = new Button(bText[i]);
            bt.setFont(new Font("Courier", Font.BOLD, 28));
            buttons[i] = bt;
        }

        //add buttons to button panel
        for (int i = 0; i < EvaluatorUI.bText.length; i++) {
            buttonPanel.add(buttons[i]);
        }

        //set up buttons to listen for mouse input
        for (int i = 0; i < EvaluatorUI.bText.length; i++) {
            buttons[i].addActionListener(this);
        }

        setTitle("Calculator");
        setSize(400, 400);
        setLocationByPlatform(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);
    }

    /**
     * This function is triggered anytime a button is pressed
     * on our Calculator GUI.
     *
     * @param eventObject Event object generated when a
     *                    button is pressed.
     */
    public void actionPerformed(ActionEvent eventObject) {
        Evaluator evaluate = new Evaluator();


        if(eventObject.getActionCommand().equals("=")){ //when = is pressed, evaluate the entered txt
            txField.setText(Integer.toString(evaluate.eval(txField.getText())));
        }else{
            txField.setText(txField.getText() + eventObject.getActionCommand());
            if(eventObject.getActionCommand().equals("C")){ //if C is pressed, calculator clears the txfield
                txField.setText("");
            }else if (eventObject.getActionCommand().equals("CE") ){    //if CE is pressed:


                try {   //on a try catch block so CE wont print out errors when pressed without any numbers in the txfield
                        //calculator prints CE when pressed, so length of substring - 3 should delete the previous text
                        txField.setText(txField.getText().substring(0, txField.getText().length() - 3));

                    } catch (Exception e){
                    txField.setText("");
                }
            }
            else{
                //empty
            }
        }
        }
    }
