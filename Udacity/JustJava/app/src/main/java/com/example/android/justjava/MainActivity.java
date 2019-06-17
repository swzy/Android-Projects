package com.example.android.justjava;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    private int quantity = 0;
    private String userName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void increment(View view) {
        if (quantity < 100) {
            quantity += 1;
        }
        displayQuantity(quantity);
    }

    public void decrement(View view) {
        if (quantity > 0) {
            quantity -= 1;
        }
        else
            Toast.makeText(this, "Can't order less than 0 cups of coffee!", Toast.LENGTH_SHORT).show();
        displayQuantity(quantity);
    }

    public String getName() {
        EditText editText = findViewById(R.id.name_text_field);
        return String.valueOf(editText.getText());
    }

    public boolean whipIsChecked(){
        CheckBox whipCheckBox = findViewById(R.id.whippedCream_check_box);
        return whipCheckBox.isChecked();
    }
    public boolean chocIsChecked(){
        CheckBox chocCheckBox = findViewById(R.id.chocolate_check_box);
        return chocCheckBox.isChecked();
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        double price = calculatePrice(quantity, 5, whipIsChecked(), chocIsChecked());

        composeEmail("Just Java order for " + userName, createOrderSummary(getName(), price, whipIsChecked(), chocIsChecked()));
    }

    /**
     * Calculates the price of the order based on the current quantity.
     *
     * @return the price
     */
    private double calculatePrice(int quantity, double coffeeCost, boolean whippedCream, boolean chocolate) {
        double total = coffeeCost;
        if (whippedCream) {
            total += 1;
        }
        if (chocolate) {
            total += 2;
        }
        total = total * quantity;
        return total;
    }

    public void composeEmail(String subject, String contents) {
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, contents);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(
                R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private String createOrderSummary(String name, double price, boolean whipCheck, boolean chocCheck) {
        String message = "Name: " + name;
        this.userName = name;
        message += "\nQuantity: " + quantity;
        message += "\nAdd Whipped Cream: " + whipCheck;
        message += "\nAdd Chocolate: " + chocCheck;
        message += "\nTotal Cost: $" + price;
        message += "\nThank You!";

        return message;
    }
}