package com.example.labux.ticket;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.example.labux.R;

import java.util.Vector;

public class TicketAdapter extends RecyclerView.Adapter<TicketAdapter.ViewHolder> {
    private Vector<TicketData> ticketDataVector;
    private OnItemClickCallBack onItemClickCallBack;
    private Context context;

    public void setOnItemClickCallBack(OnItemClickCallBack onItemClickCallBack){
        this.onItemClickCallBack = onItemClickCallBack;
    }

    public TicketAdapter(Vector<TicketData> ticketDataVector, Context context){
        this.ticketDataVector = ticketDataVector;
        this.context = context;
    }


    @NonNull
    @Override
    public TicketAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.ticket_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TicketAdapter.ViewHolder holder, int position) {
        TicketData ticketData = ticketDataVector.get(position);

        holder.imageView.setImageResource(ticketData.getImage());
        holder.ticket_name.setText(ticketData.getName());
        holder.ticket_date.setText(ticketData.getDate());
        int ticketPrice = ticketData.getPrice();
        String priceString = String.valueOf(ticketPrice);
        holder.ticket_price.setText("IDR " + priceString +".000");


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallBack.onItemClicked(ticketDataVector.get(holder.getAdapterPosition()));
            }
        });

        holder.ticket_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent moveIntent = new Intent(context, DetailTicketActivity.class);
                moveIntent.putExtra(DetailTicketActivity.ITEM_EXTRA, ticketDataVector.get(holder.getAdapterPosition()));
                context.startActivity(moveIntent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return ticketDataVector.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView ticket_name;
        TextView ticket_date;
        TextView ticket_price;

        AppCompatButton ticket_button;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.ticket_image);
            ticket_name = itemView.findViewById(R.id.ticket_name);
            ticket_date = itemView.findViewById(R.id.ticket_date);
            ticket_price = itemView.findViewById(R.id.ticket_price);
            ticket_button = itemView.findViewById(R.id.ticket_button);

        }
    }
    public interface OnItemClickCallBack{
        void onItemClicked (TicketData data);
    }
}
