package com.example.labux.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.widget.AppCompatButton;

import com.example.labux.R;

public class LoginActivity extends Activity {

    EditText usernameEditText, passwordEditText;
    AppCompatButton loginButton;
    public static String globalUsername;

    TextView usernameErrorLabel, passwordErrorLabel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);
        usernameErrorLabel = findViewById(R.id.usernameErrorLabel);
        passwordErrorLabel = findViewById(R.id.passwordErrorLabel);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateInput();
            }
        });
    }

    private void validateInput() {
        String username = usernameEditText.getText().toString().trim();
        String password = passwordEditText.getText().toString().trim();

        boolean isUsernameValid = true;
        boolean isPasswordValid = true;

        if (username.isEmpty()) {
            showErrorLabel(usernameEditText, usernameErrorLabel, "Username must be not empty");
            isUsernameValid = false;
        } else if (username.length() < 5) {
            showErrorLabel(usernameEditText, usernameErrorLabel, "Username must be at least 5 characters");
            isUsernameValid = false;
        } else {
            hideErrorLabel(usernameErrorLabel);
        }

        if (password.isEmpty()) {
            showErrorLabel(passwordEditText, passwordErrorLabel, "Password must be not empty");
            isPasswordValid = false;
        } else if (password.length() <= 8) {
            showErrorLabel(passwordEditText, passwordErrorLabel, "Password must be at least 8 characters");
            isPasswordValid = false;
        } else {
            hideErrorLabel(passwordErrorLabel);
        }

        if (isUsernameValid && isPasswordValid) {
            globalUsername = username;
            redirectToHome();
        }
    }

    private void showErrorLabel(EditText editText, TextView errorLabel, String errorMessage) {
        editText.requestFocus();
        errorLabel.setVisibility(View.VISIBLE);
        errorLabel.setText(errorMessage);
    }

    private void hideErrorLabel(TextView errorLabel) {
        errorLabel.setVisibility(View.GONE);
    }

    private void redirectToHome() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}