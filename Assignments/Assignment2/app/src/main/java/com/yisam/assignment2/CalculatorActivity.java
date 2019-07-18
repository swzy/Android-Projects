package com.yisam.assignment2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
<<<<<<< HEAD
=======
import android.util.Log;
>>>>>>> 29950d18aa18fc24e1accffa8a92526cb3f9387e
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/*
<<<<<<< HEAD
    Simple calculator application made by Samuel Yi. (v2)
    Activity/Controller handles View hookups and updates, calculator logic in model class
*/

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {
    CalculatorModel calculator;
    TextView prevCalcView, currCalcView;
=======
    Simple calculator application made by Samuel Yi.
    String evaluation/parser provided by user Boann@StackOverflow: https://stackoverflow.com/a/26227947/
*/

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {
    TextView prevCalcView, currCalcView;
    String prevText, currentText;
    //Checks if last click was equals
    boolean didEquals = false;
>>>>>>> 29950d18aa18fc24e1accffa8a92526cb3f9387e
    Button one, two, three, four, five, six, seven, eight, nine, zero, decimal, sign, divide, multiply, paren, minus, plus, clear, equals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
<<<<<<< HEAD
        calculator = new CalculatorModel();
        setListeners();
    }

=======
        setListeners();
    }

    @Override
    public void onClick(View v) {
        try {
            currentText = currCalcView.getText().toString();
        } catch (Exception e) {
            currentText = "";
        }

        if (didEquals) {
            currentText = "";
            currCalcView.setText(currentText);
            didEquals = false;
        }

        Log.i("Event","Click received");
        switch(v.getId()) {
            case R.id.calculator_currentText:
                currentText = currentText.substring(0, currentText.length() - 1);
                currCalcView.setText(currentText);
                break;

            case R.id.one:
                currentText += "1";
                currCalcView.setText(currentText);
                break;

            case R.id.two:
                currentText += "2";
                currCalcView.setText(currentText);
                break;

            case R.id.three:
                currentText += "3";
                currCalcView.setText(currentText);
                break;

            case R.id.four:
                currentText += "4";
                currCalcView.setText(currentText);
                break;

            case R.id.five:
                currentText += "5";
                currCalcView.setText(currentText);
                break;

            case R.id.six:
                currentText += "6";
                currCalcView.setText(currentText);
                break;

            case R.id.seven:
                currentText += "7";
                currCalcView.setText(currentText);
                break;

            case R.id.eight:
                currentText += "8";
                currCalcView.setText(currentText);
                break;

            case R.id.nine:
                currentText += "9";
                currCalcView.setText(currentText);
                break;

            case R.id.zero:
                currentText += "0";
                currCalcView.setText(currentText);
                break;

            case R.id.decimal:
                currentText += ".";
                currCalcView.setText(currentText);
                break;

            case R.id.divide:
                currentText += "/";
                currCalcView.setText(currentText);
                break;

            case R.id.multiply:
                currentText += "*";
                currCalcView.setText(currentText);
                break;

            case R.id.paren:
                //If paren button was pressed before
                if (currentText.contains("(")) {
                    char lastChar = currentText.charAt(currentText.length() - 1);

                    //If last character was a number, add a closing right parentheses
                    if (lastChar >= 48 && lastChar <= 57) {
                        currentText += ")";
                    }
                    //If last character was an operator, add a left parentheses
                    else if (lastChar >= 42 && lastChar <= 47) {
                        currentText += "(";
                    } else if (lastChar == '(') {
                        currentText += "(";
                    } else if (lastChar == ')') {
                        currentText += ")";
                    }
                    currCalcView.setText(currentText);
                }
                else {
                    currentText += "(";
                    currCalcView.setText(currentText);
                }
                break;

            case R.id.minus:
                currentText += "-";
                currCalcView.setText(currentText);
                break;

            case R.id.plus:
                currentText += "+";
                currCalcView.setText(currentText);
                break;

            case R.id.clear:
                currentText = "";
                currCalcView.setText(currentText);
                break;

            case R.id.equals:
                try {
                    prevText = currentText;
                    double result = eval(currentText);
                    int result_int;
                    //Checks if result is a whole number
                    if (result % 1 == 0) {
                        result_int = (int) result;
                        currentText = Integer.toString(result_int);
                    } else {
                        currentText = Double.toString(result);
                    }

                    Log.i("Eval", "Sum was clicked");
                    prevCalcView.setText(prevText);
                    currCalcView.setText(currentText);
                    didEquals = true;

                } catch (Exception e) {
                    prevText = currentText;
                    currentText = "Invalid!";

                    prevCalcView.setText(prevText);
                    currCalcView.setText(currentText);
                    didEquals = true;
                }
                break;
        }
    }

    public static double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length()) throw new RuntimeException("Unexpected: " + (char)ch);
                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    x = Double.parseDouble(str.substring(startPos, this.pos));
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else throw new RuntimeException("Unknown function: " + func);
                } else {
                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }

>>>>>>> 29950d18aa18fc24e1accffa8a92526cb3f9387e
    private void setListeners() {
        one = (Button) findViewById(R.id.one);
        one.setOnClickListener(this);

        two = (Button) findViewById(R.id.two);
        two.setOnClickListener(this);

        three = (Button) findViewById(R.id.three);
        three.setOnClickListener(this);

        four = (Button) findViewById(R.id.four);
        four.setOnClickListener(this);

        five = (Button) findViewById(R.id.five);
        five.setOnClickListener(this);

        six = (Button) findViewById(R.id.six);
        six.setOnClickListener(this);

        seven = (Button) findViewById(R.id.seven);
        seven.setOnClickListener(this);

        eight = (Button) findViewById(R.id.eight);
        eight.setOnClickListener(this);

        nine = (Button) findViewById(R.id.nine);
        nine.setOnClickListener(this);

        zero = (Button) findViewById(R.id.zero);
        zero.setOnClickListener(this);

        decimal = (Button) findViewById(R.id.decimal);
        decimal.setOnClickListener(this);

        divide = (Button) findViewById(R.id.divide);
        divide.setOnClickListener(this);

        multiply = (Button) findViewById(R.id.multiply);
        multiply.setOnClickListener(this);

        minus = (Button) findViewById(R.id.minus);
        minus.setOnClickListener(this);

        plus = (Button) findViewById(R.id.plus);
        plus.setOnClickListener(this);

        sign = (Button) findViewById(R.id.sign);
        sign.setOnClickListener(this);

        clear = (Button) findViewById(R.id.clear);
        clear.setOnClickListener(this);

        equals = (Button) findViewById(R.id.equals);
        equals.setOnClickListener(this);

        paren = (Button) findViewById(R.id.paren);
        paren.setOnClickListener(this);

        prevCalcView = (TextView) findViewById(R.id.calculator_prevText);
        prevCalcView.setOnClickListener(this);

        currCalcView = (TextView) findViewById(R.id.calculator_currentText);
        currCalcView.setOnClickListener(this);
        currCalcView.setMovementMethod(new ScrollingMovementMethod());
    }
<<<<<<< HEAD

    //Passes values to model class
    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.calculator_currentText:
                calculator.backspace();
                updateCurrentCalcView();
                break;

            case R.id.one:
                calculator.setCurrentText("1");
                updateCurrentCalcView();
                break;

            case R.id.two:
                calculator.setCurrentText("2");
                updateCurrentCalcView();
                break;

            case R.id.three:
                calculator.setCurrentText("3");
                updateCurrentCalcView();
                break;

            case R.id.four:
                calculator.setCurrentText("4");
                updateCurrentCalcView();
                break;

            case R.id.five:
                calculator.setCurrentText("5");
                updateCurrentCalcView();
                break;

            case R.id.six:
                calculator.setCurrentText("6");
                updateCurrentCalcView();
                break;

            case R.id.seven:
                calculator.setCurrentText("7");
                updateCurrentCalcView();
                break;

            case R.id.eight:
                calculator.setCurrentText("8");
                updateCurrentCalcView();
                break;

            case R.id.nine:
                calculator.setCurrentText("9");
                updateCurrentCalcView();
                break;

            case R.id.zero:
                calculator.setCurrentText("0");
                updateCurrentCalcView();
                break;

            case R.id.decimal:
                calculator.setCurrentText(".");
                updateCurrentCalcView();
                break;

            case R.id.divide:
                calculator.setCurrentText("/");
                updateCurrentCalcView();
                break;

            case R.id.multiply:
                calculator.setCurrentText("*");
                updateCurrentCalcView();
                break;

            case R.id.paren:
                calculator.setCurrentText("(");
                updateCurrentCalcView();
                break;

            case R.id.minus:
                calculator.setCurrentText("-");
                updateCurrentCalcView();
                break;

            case R.id.plus:
                calculator.setCurrentText("+");
                updateCurrentCalcView();
                break;

            case R.id.sign:
                calculator.setCurrentText(":D->-<");
                updateCurrentCalcView();
                break;

            case R.id.clear:
                calculator.setCurrentText("");
                updateCurrentCalcView();
                break;

            case R.id.equals:
                calculator.equalsClicked();
                updateCurrentCalcView();
                break;
        }
    }

    private void updateCurrentCalcView () {
        String calcString = calculator.getCurrentText();
        String prevCalcString = calculator.getPrevText();

        this.prevCalcView.setText(prevCalcString);
        this.currCalcView.setText(calcString);
    }
=======
>>>>>>> 29950d18aa18fc24e1accffa8a92526cb3f9387e
}
