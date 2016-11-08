package com.se.developershub.simplecalculator;
/*
* @Author Anthony Chan
* Goal of Project: to produce an Infix Calculator which does simple algebra
* Future goals:
*     - use shared preferences to deal with saving values into 'registers'
*     - Log, e, √, Parenthetical Computation, etc.
*/

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Stack;

public class MainActivity extends AppCompatActivity {

    //////////VARIABLES//////////
    String calculatorInputSTR;
    String numberSTR;
    ArrayList<String> calculatorInput = new ArrayList<String>();
    /*Initialize View Variables*/
    Button ADD;
    Button SUBTRACT;
    Button MULTIPLY;
    Button DIVIDE;
    Button EQUALS;
    Button CLEAR;
    Button DELETE;
    // NUMBERS
    Button ONE;
    Button TWO;
    Button THREE;
    Button FOUR;
    Button FIVE;
    Button SIX;
    Button SEVEN;
    Button EIGHT;
    Button NINE;
    Button ZERO;
    // TEXTVIEWS
    TextView SCREEN;
    //////////VARIABLES//////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initializeBTN();
        initializeNumberBTN();
        initializeTV();
        setBtnOnClickListeners();
        calculatorInputSTR = ""; // set string initially to blank
        numberSTR = "";
    }

    /*
     Initialize view objects
    */
    void initializeBTN(){
        ADD = (Button)findViewById(R.id.ADD);
        SUBTRACT = (Button)findViewById(R.id.SUBTRACT);
        MULTIPLY = (Button)findViewById(R.id.MULTIPLY);
        DIVIDE = (Button)findViewById(R.id.DIVIDE);
        EQUALS = (Button)findViewById(R.id.EQUALS);
        CLEAR = (Button)findViewById(R.id.CLEAR);
        DELETE = (Button)findViewById(R.id.DELETE);
    }
    void initializeNumberBTN(){
        ONE = (Button)findViewById(R.id.ONE);
        TWO = (Button)findViewById(R.id.TWO);
        THREE = (Button)findViewById(R.id.THREE);
        FOUR = (Button)findViewById(R.id.FOUR);
        FIVE = (Button)findViewById(R.id.FIVE);
        SIX = (Button)findViewById(R.id.SIX);
        SEVEN = (Button)findViewById(R.id.SEVEN);
        EIGHT = (Button)findViewById(R.id.EIGHT);
        NINE = (Button)findViewById(R.id.NINE);
        ZERO = (Button)findViewById(R.id.ZERO);
    }
    void initializeTV(){
        SCREEN = (TextView)findViewById(R.id.tv_screen);
    }

    void setBtnOnClickListeners(){
        //////////NUMBER ONCLICKLISTENERS//////////
        ONE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatorInput.add("1");
                numberSTR = numberSTR + "1";
                printExpression(SCREEN, calculatorInput);
            }
        });
        TWO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatorInput.add("2");
                numberSTR = numberSTR + "2";
                printExpression(SCREEN, calculatorInput);
            }
        });
        THREE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatorInput.add("3");
                numberSTR = numberSTR + "3";
                printExpression(SCREEN, calculatorInput);
            }
        });
        FOUR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatorInput.add("4");
                numberSTR = numberSTR + "4";
                printExpression(SCREEN, calculatorInput);
            }
        });
        FIVE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatorInput.add("5");
                numberSTR = numberSTR + "5";
                printExpression(SCREEN, calculatorInput);
            }
        });
        SIX.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatorInput.add("6");
                numberSTR = numberSTR + "6";
                printExpression(SCREEN, calculatorInput);
            }
        });
        SEVEN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatorInput.add("7");
                numberSTR = numberSTR + "7";
                printExpression(SCREEN, calculatorInput);
            }
        });
        EIGHT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatorInput.add("8");
                numberSTR = numberSTR + "8";
                printExpression(SCREEN, calculatorInput);
            }
        });
        NINE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatorInput.add("9");
                numberSTR = numberSTR + "9";
                printExpression(SCREEN, calculatorInput);
            }
        });
        ZERO.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculatorInput.add("0");
                numberSTR = numberSTR + "0";
                printExpression(SCREEN, calculatorInput);
            }
        });
        //////////SYMBOL ONCLICKLISTENERS//////////
        ADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateArray();
                calculatorInput.add("+");
                printExpression(SCREEN, calculatorInput);
            }
        });
        SUBTRACT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateArray();
                calculatorInput.add("-");
                printExpression(SCREEN, calculatorInput);
            }
        });
        MULTIPLY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateArray();
                calculatorInput.add("*");
                printExpression(SCREEN, calculatorInput);
            }
        });
        DIVIDE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateArray();
                calculatorInput.add("/");
                printExpression(SCREEN, calculatorInput);
            }
        });
        CLEAR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                numberSTR = "";
                clearResult();
            }
        });
        EQUALS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!calculatorInput.isEmpty()) {
                    if (!calculatorInput.get(calculatorInput.size() - 1).matches("^-?\\d+$")) {
                        Toast.makeText(MainActivity.this, "Please complete your expression", Toast.LENGTH_SHORT).show();
                    } else {
                        updateArray();
                        String result = String.valueOf(calculateResult());
                        clearArray();
                        calculatorInput.add(result);
                        SCREEN.setText(result);
                    }
                }

            }
        });
        DELETE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteValues();
            }
        });
    }

    /**
     * Deletes most recent input from SCREEN and from calculatorInput
     */
    void deleteValues(){
        if(calculatorInput.size()!=0){
            calculatorInput.remove(calculatorInput.size()-1);
            printExpression(SCREEN, calculatorInput);
        }

    }

    /**
     * reset String variable @calculatorInput and TextView @SCREEN
     */
    void clearResult(){
        clearArray();
        printExpression(SCREEN, calculatorInput);
    }

    /**
     *  Clear calculatorInput Arraylist
     */
    void clearArray(){
        calculatorInput.clear();
    }

    void printExpression(TextView view, ArrayList<String> array){
        StringBuffer expression = new StringBuffer();
        for(String i : array){
            expression.append(i);
        }
        view.setText(expression.toString());
    }

    void updateArray(){
        if(numberSTR.length() != 0) {
            for (int i = 0; i < numberSTR.length(); i++) {
                deleteValues();
            }
            calculatorInput.add(numberSTR);
            numberSTR = "";
        }
    }
    /**
     * Compute the result and return the value
     * @return
     */
    long calculateResult(){

        calculatorInput.add("e"); // add in an end trigger
        Stack<String> operands = new Stack<>();
        Stack<Long> numbers = new Stack<>();
        String currentOperand;
        long VAR1;
        long VAR2;
        int OPi;
        int OPstack;
            for(String i : calculatorInput){

                if(i.matches("^-?\\d+$")){
                    // i is a digit
                    numbers.push(Long.valueOf(i));
                }else{
                    // i is an operand
                        OPi = operandPrecedence(i);
                    if(operands.isEmpty()){
                        OPstack = -1;
                    }else{
                        OPstack = operandPrecedence(operands.peek());
                    }

                    while(OPi <= OPstack){
                        currentOperand = operands.pop(); //pop out operand in stack
                        VAR1 = numbers.pop();
                        VAR2 = numbers.pop();
                        switch (currentOperand){
                            case "+":
                                numbers.push(VAR2 + VAR1);
                                break;
                            case "-":
                                numbers.push(VAR2 - VAR1);
                                break;
                            case "*":
                                numbers.push(VAR2 * VAR1);
                                break;
                            case "/":
                                numbers.push(VAR2 / VAR1);
                                break;
                            case "e":
                                break;
                            default:
                                break;
                        }

                        if(operands.isEmpty()){
                            OPstack = -1001;
                        }else{
                            OPstack = operandPrecedence(operands.peek());
                        }
                    }

                    operands.push(i);
                }
            }

        return numbers.peek();
    }

    /**
     * Function determines a numeric value of precedence of the operation
     * @param operand
     * @return
     */
    int operandPrecedence(String operand){
        switch (operand){
            case "+":
                return 1;
            case "-":
                return 1;
            case "*":
                return 3;
            case "/":
                return 3;
            case "e":
                return -900; // end trigger value
            default:
                return -1000;
        }
    }

}
