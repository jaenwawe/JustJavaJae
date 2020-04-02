package android.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import java.text.NumberFormat;


/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {

    int quantity =2;
    int price = 0;
    boolean hasWhippedCream= false;
    boolean hasChocolate = false;
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void checkWhippedCream(View view)
    {
        CheckBox checkBox = (CheckBox) view;
        TextView textView = (TextView) (findViewById(R.id.text_whipped_cream));
        if (checkBox.isChecked()){
            textView.setText("Add Whipped Cream");
            hasWhippedCream = true;
            displayQuantity(quantity);

         //   Log.v("MainActivity", "Whipped Cream");
        }
        else{
            textView.setText("No Whipped Cream");
            hasWhippedCream = false;
            displayQuantity(quantity);
        }

    }


    public void checkChocolate(View view)
    {
        CheckBox checkBox = (CheckBox) view;
        TextView textView = (TextView) (findViewById(R.id.text_chocolate));
        if (checkBox.isChecked()){
            textView.setText("Add Chocolate");
            hasChocolate = true;
            displayQuantity(quantity);
            //   Log.v("MainActivity", "Whipped Cream");
        }
        else{
            textView.setText("No Chocolate");
            hasChocolate = false;
            displayQuantity(quantity);
        }

    }


    private int calculatePrice() {
            price = quantity *5;
        if (!hasChocolate && !hasWhippedCream)
            price = quantity * 5;
        if (hasWhippedCream)
            price++;
        if (hasChocolate)
            price = price+2;
        return price;
    }


    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        EditText text = (EditText) findViewById(R.id.name_edittext);
        name = text.getText().toString();

        String message = "Thank you!";
        String priceMessage = "Name: " + name;
        priceMessage += "\nAdd whipped cream? " + hasWhippedCream;
        priceMessage += "\nAdd chocolate? " + hasChocolate;
        priceMessage += "\nQuantity: " + quantity;
        priceMessage += "\nTotal: $" + calculatePrice();
        priceMessage += "\nThank you!";


        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Just Java order for " + name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);

        }
    }

     public void createOrderSummary(int amount, int number){
         TextView nameTextView = (TextView) findViewById(R.id.text_name);
         nameTextView.setText("Name: " + "Jae Nwawe");

         TextView quantityTextView = (TextView) findViewById(R.id.text_quantity);
         quantityTextView.setText("Quantity: " + number);

         TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
         priceTextView.setText("Total: "+NumberFormat.getCurrencyInstance().format(calculatePrice()));

         TextView thankYou = (TextView)findViewById(R.id.gratitude);
         thankYou.setText("Thank you");

         TextView textView = (TextView) (findViewById(R.id.text_whipped_cream));
         if(hasWhippedCream == true) textView.setText("Add Whipped Cream");

     }



    public void incrementOrder(View view) {
      if  (quantity >= 1 && quantity <= 100) {
          quantity = quantity + 1;
          displayQuantity(quantity);
      } else quantity = 1;

    }
    public void decrementOrder(View view) {

        if (quantity >= 1 && quantity <= 100) {
            quantity = quantity - 1;
            displayQuantity(quantity);
        }
        else quantity =1;
        displayQuantity(quantity);
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
        TextView cost = (TextView) findViewById(R.id.price_text_view);
        cost.setText(NumberFormat.getCurrencyInstance().format(calculatePrice()));


    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView priceTextView = (TextView) findViewById(R.id.price_text_view);
        priceTextView.setText(message);
    }








}
