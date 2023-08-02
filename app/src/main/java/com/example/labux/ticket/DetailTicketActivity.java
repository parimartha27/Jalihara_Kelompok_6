package com.example.labux.ticket;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.labux.R;

import org.w3c.dom.Text;

public class DetailTicketActivity extends AppCompatActivity {

    public static final String ITEM_EXTRA = "item_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_ticket);

        ImageView imageView  = findViewById(R.id.detail_image);
        TextView detail_name = findViewById(R.id.detail_name);
        TextView detail_date = findViewById(R.id.detail_date);
        TextView after_detail_name = findViewById(R.id.after_detail_name);
        TextView detail_time = findViewById(R.id.detail_time);
        TextView detail_description = findViewById(R.id.detail_description);
        TextView detail_experience = findViewById(R.id.detail_experience);

        TicketData ticketData = getIntent().getParcelableExtra(ITEM_EXTRA);

        if(ticketData != null){
            imageView.setImageResource(ticketData.getImage());
            detail_name.setText(ticketData.getName());
            detail_date.setText(ticketData.getDate());
            after_detail_name.setText(ticketData.getDetail_name());
            detail_time.setText(ticketData.getDetail_time());
            detail_description.setText(ticketData.getDescription());
            detail_experience.setText(ticketData.getExperience());

            AppCompatButton buyTicketButton = findViewById(R.id.detail_button);

            buyTicketButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startFormTicketActivity(ticketData);
                }
            });
        }

        if (getSupportActionBar() != null){
            getSupportActionBar().setTitle("Detail Ticket");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        }
    }

    @Override
    public  boolean onSupportNavigateUp(){
        finish();
        return true;
    }

    private void startFormTicketActivity(TicketData ticketData) {
        Intent intent = new Intent(this, FormTicketActivity.class);
        intent.putExtra(FormTicketActivity.ITEM_EXTRA, ticketData);
        startActivity(intent);
    }
}
