package com.example.android.justjava;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity {

    // TODO(Alex) add intent on other branch
    // TODO(Alex) add automated test on other branch
    // Todo(Alex) fix ide hint ""+0 to Number.format ...
    // TODO(Alex) initialize with value on resource
    int mquantity = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void submitOrder(View view) {
        CheckBox creamTopping =  findViewById(R.id.cream_topping);
        CheckBox chocolateTopping = findViewById(R.id.chocolate_topping);
        boolean hasCream = creamTopping.isChecked();
        boolean hasChocolate = chocolateTopping.isChecked();

        EditText name = findViewById(R.id.name);
        String nameMakeOrder = name.getText().toString();

        String summary = createOrderSummary(mquantity, hasCream, hasChocolate, nameMakeOrder);
        displayOrder(summary);
    }

    public void increase(View view) {
        // Todo(Alex) remove magic numbers
        if (mquantity > 99) {
            String legibleNumber = NumberFormat.getNumberInstance().format(100);
            String message = getString(R.string.quantity_control_plus, legibleNumber);
            Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            return;
        }
        mquantity += 1;
        display(mquantity);
    }

    public void decrease(View view) {
        if (mquantity < 1) {
            String legibleNumber = NumberFormat.getNumberInstance().format(0);
            String message = getString(R.string.quantity_control_minus, legibleNumber);
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            return;
        }
        mquantity -= 1;
        display(mquantity);
    }

    private int calculatePrice(boolean hasCream, boolean hasChocolate) {
        int basePrice = 5;
        // +1 cream
        if (hasCream) {
            basePrice += 1;
        }
        // +2 chocolate
        if (hasChocolate) {
            basePrice += 2;
        }

        return mquantity * basePrice;
    }

    private void display(int number) {
        TextView quantityTextView = findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    private void displayOrder(String summaryOrder) {
        TextView priceTextView = findViewById(R.id.order_summary_text_view);
        priceTextView.setText(summaryOrder);
    }

    private String transformBoolResource(boolean value) {
        String resourceName = value ? "boolean_true" : "boolean_false";

        int resourceId = getResources()
                .getIdentifier(resourceName, "string", getPackageName());

        return getString(resourceId);
    }

    // Todo(Alex) pass Java Beans Order to avoid to many arguments
    private String createOrderSummary(int quantity, boolean creamTopping, boolean chocolateTopping, String nameMakeOrder) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        NumberFormat intFormat = NumberFormat.getNumberInstance();

        int price = calculatePrice(creamTopping, chocolateTopping);

        String legiblePrice = currencyFormat.format(price);
        String legibleHasCreamTopping = transformBoolResource(creamTopping);
        String legibleHasChocolate = transformBoolResource(chocolateTopping);
        String legibleQuantity = intFormat.format(quantity);

        StringBuffer template = new StringBuffer();
        template.append(getString(R.string.order_summary_name_output, nameMakeOrder)).append("\n")
                .append(getString(R.string.order_summary_whipped_cream_output, legibleHasCreamTopping)).append("\n")
                .append(getString(R.string.order_summary_chocolate_output, legibleHasChocolate)).append("\n")
                .append(getString(R.string.order_summary_quantity_output, legibleQuantity)).append("\n")
                .append(getString(R.string.order_summary_total_output, legiblePrice)).append("\n")
                .append(getString(R.string.order_summary_thank_you));

        return template.toString();
    }
}
