package com.yisam.assignment2;

import android.util.Log;

public class CalculatorModel {
    private String prevText, currentText;
    //Checks if last click was equals to clear
    private boolean didEquals = false;

    CalculatorModel () {
        this.prevText = "";
        this.currentText = "";
    }

    //String concatenate
    public void setCurrentText(String input) {
        //If last button pressed was 'sum', clear currentText string on next entry
        if (didEquals) {
            this.currentText = "";
            didEquals = false;
        }
        Log.i("Event","Click received");

        if (input.equals("(") || input.equals(")")) {
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
            }
            else {
                currentText += "(";
            }
        } else if (input.equals("")) {
            this.currentText = "";
        } else {
            this.currentText += input;
        }
    }

    //Erase one character from string
    public void backspace() {
        if (this.currentText.length() > 0) {
            this.currentText = currentText.substring(0, this.currentText.length() - 1);
        }
    }

    public String getCurrentText () {
        return currentText;
    }
    public String getPrevText () {
        return prevText;
    }

    //If the 'sum' is clicked
    public void equalsClicked() {
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
            didEquals = true;

        } catch (Exception e) {
            prevText = currentText;
            currentText = "Invalid!";

            didEquals = true;
        }
    }

    /*
        String evaluation/parser provided by user Boann@StackOverflow: https://stackoverflow.com/a/26227947/
     */

    //Eval method for sum
    private double eval(final String str) {
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
}
