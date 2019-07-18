package com.yisam.assignment2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/*
    Simple calculator application made by Samuel Yi. (v2)
    Activity/Controller handles View hookups and updates, calculator logic in model class
*/

public class CalculatorActivity extends AppCompatActivity implements View.OnClickListener {
    CalculatorModel calculator;
    TextView prevCalcView, currCalcView;
    Button one, two, three, four, five, six, seven, eight, nine, zero, decimal, sign, divide, multiply, paren, minus, plus, clear, equals;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calculator = new CalculatorModel();
        setListeners();
    }

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
}
