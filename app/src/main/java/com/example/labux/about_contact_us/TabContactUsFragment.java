package com.example.labux.about_contact_us;

import android.os.Bundle;

import androidx.appcompat.widget.AppCompatButton;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.labux.R;

public class TabContactUsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_tab_contact_us, container, false);

        AppCompatButton messageButton = view.findViewById(R.id.message_button);
        final EditText nameEditText = view.findViewById(R.id.input_email_contact_us);
        final EditText messageEditText = view.findViewById(R.id.input_message_contact_us);

        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered name and message from EditText fields
                String name = nameEditText.getText().toString().trim();
                String message = messageEditText.getText().toString().trim();

                // Check if both name and message are not empty
                if (name.isEmpty() || message.isEmpty()) {
                    // Show an error message using Toast
                    Toast.makeText(getContext(), "Please fill in both Name and Message", Toast.LENGTH_SHORT).show();
                } else {
                    // Both name and message are not empty, proceed to send the message
                    showPurchaseSuccessMessage();
                }
            }
        });
        return view;
    }

    private void showPurchaseSuccessMessage() {
        Toast.makeText(getContext(), "Message Sent!", Toast.LENGTH_SHORT).show();
    }

}