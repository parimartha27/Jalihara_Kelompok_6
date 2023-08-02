package com.example.labux.ticket;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.labux.R;
import com.example.labux.activity.LoginActivity;
import com.example.labux.activity.MainActivity;

public class FormTicketActivity extends AppCompatActivity {

    public static final String ITEM_EXTRA = "item_extra";

    TextView form_ticket_price;
    RadioGroup booth_type;

    private int quantity = 0;

    private EditText form_input_quantity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_ticket);

        ImageView imageView = findViewById(R.id.form_ticket_image);
        TextView form_ticket_name = findViewById(R.id.form_ticket_name);
        TextView form_ticket_date = findViewById(R.id.form_ticket_date);
        form_ticket_price = findViewById(R.id.form_ticket_price);
        form_input_quantity = findViewById(R.id.form_input_quantity);

        form_input_quantity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                int currentQuantity = getQuantityFromEditText();
                if (currentQuantity > 25) {
                    quantity = 25;
                    updateQuantityTextView();
                } else {
                    updateTotalPrice();
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });


        booth_type = findViewById(R.id.booth_type);
        TicketData ticketData = getIntent().getParcelableExtra(ITEM_EXTRA);

        booth_type.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                updateTotalPrice();
            }
        });

        if (ticketData != null) {
            imageView.setImageResource(ticketData.getImage());
            form_ticket_name.setText(ticketData.getName());
            form_ticket_date.setText(ticketData.getDate());
            int ticketPrice = ticketData.getPrice();
            form_ticket_price.setText("IDR " + String.valueOf(ticketPrice) + ".000");

            Button buyButton = findViewById(R.id.form_buy_button);

            buyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    validateInput();

                }
            });
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Buy Ticket Form");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    private void hideErrorLabelName(TextView errorLabel) {
        errorLabel.setVisibility(View.GONE);
    }

    private void showErrorLabelName(EditText form_input_name, TextView errorLabel, String errorMessage) {
        form_input_name.requestFocus();
        errorLabel.setVisibility(View.VISIBLE);
        errorLabel.setText(errorMessage);
    }

    private boolean isBoothTypeSelected() {
        RadioGroup boothTypeRadioGroup = findViewById(R.id.booth_type);
        int selectedRadioButtonId = boothTypeRadioGroup.getCheckedRadioButtonId();
        return selectedRadioButtonId != -1;
    }

    private void validateInput() {
        EditText form_input_name = findViewById(R.id.form_input_name);
        String input_name = form_input_name.getText().toString().trim();
        TextView errorMessageName = findViewById(R.id.form_input_name_error);
        boolean isValidName = true;

        // Validate Name
        if (input_name.isEmpty()) {
            showErrorLabelName(form_input_name, errorMessageName, "Name must be not empty!");
            isValidName = false;
        } else {
            hideErrorLabelName(errorMessageName);
        }

        // Validate Booth Type
        if (!isBoothTypeSelected()) {
            TextView errorMessageBoothType = findViewById(R.id.form_input_booth_error);
            errorMessageBoothType.setVisibility(View.VISIBLE);
            errorMessageBoothType.setText("Booth type must be chosen!");
        } else {
            TextView errorMessageBoothType = findViewById(R.id.form_input_booth_error);
            errorMessageBoothType.setVisibility(View.GONE);
        }

        // Validate Quantity
        if (quantity <= 0) {
            TextView errorMessageQuantity = findViewById(R.id.form_input_quantity_error);
            errorMessageQuantity.setVisibility(View.VISIBLE);
            errorMessageQuantity.setText("Ticket quantity must be greater than 0!");
        } else {
            TextView errorMessageQuantity = findViewById(R.id.form_input_quantity_error);
            errorMessageQuantity.setVisibility(View.GONE);
        }

        if (isValidName && isBoothTypeSelected() && quantity > 0) {
            updateTotalPrice();
            showPurchaseSuccessMessage();
            redirectToHome();
        }
    }

    public void onDecrementButtonClicked(View view) {
        quantity = getQuantityFromEditText();
        if (quantity > 0) {
            quantity--;
            updateQuantityTextView();
            updateTotalPrice();
        }
    }

    public void onIncrementButtonClicked(View view) {
        int currentQuantity = getQuantityFromEditText();
        if (currentQuantity < 25) {
            quantity = currentQuantity + 1;
            updateQuantityTextView();
            updateTotalPrice();
        }
    }

    private int getQuantityFromEditText() {
        String quantityText = form_input_quantity.getText().toString().trim();
        if (!quantityText.isEmpty()) {
            return Integer.parseInt(quantityText);
        }
        return 0;
    }

    private void updateQuantityTextView() {
        form_input_quantity.setText(String.valueOf(quantity));
        updateTotalPrice();
    }


    private void updateTotalPrice() {
        int ticketPrice = Integer.parseInt(form_ticket_price.getText().toString().replace("IDR ", "").replace(".000", ""));
        TextView form_total_price = findViewById(R.id.form_total_price);

        RadioGroup boothTypeRadioGroup = findViewById(R.id.booth_type);
        int boothTypeRadioButtonId = boothTypeRadioGroup.getCheckedRadioButtonId();
        int additionalCost = 0;

        if (boothTypeRadioButtonId == R.id.vip) {
            additionalCost = 50;
        } else if (boothTypeRadioButtonId == R.id.vvip) {
            additionalCost = 100;
        }
//        ini error
        quantity = getQuantityFromEditText();
        int totalPrice = (ticketPrice + additionalCost) * quantity;

        if (!isBoothTypeSelected() || quantity <= 0) {
            form_total_price.setText("IDR 0.000");
        } else if (totalPrice > 999) {
            String total_price = String.valueOf(totalPrice);
            String a = total_price.substring(0,1);
            String b = total_price.substring(1);
            form_total_price.setText("IDR " + a + "." + b + ".000");
        } else {
            form_total_price.setText("IDR " + String.valueOf(totalPrice) + ".000");
        }
    }

    private void redirectToHome() {
        Intent intent = new Intent(FormTicketActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    private void showPurchaseSuccessMessage() {
        Toast.makeText(this, "Purchasing Ticket Success!", Toast.LENGTH_SHORT).show();
    }

}