package com.example.labux.ticket;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.labux.R;
import com.example.labux.ticket.TicketAdapter;
import com.example.labux.ticket.TicketData;

import java.util.Vector;

public class TicketFragment extends Fragment {

    RecyclerView recyclerView;
    TicketAdapter adapter;
    Button button;

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_ticket, container, false);

        recyclerView = view.findViewById(R.id.ticket_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        Vector<TicketData> ticketDataVector = new Vector<>();
        ticketDataVector.add(new TicketData(1, "Rahwana Dance", 200, "Monday, 7 August 2023", R.drawable.rahwana,
                "10:00 - 13:00",
                "Experience the Epic Battle and Unleash Your Senses with the Enchanting Rahwana Dance Show!",
                "Rahwana Dance is a traditional Balinese dance that depicts the story of the evil demon king, Rahwana, and his battle against Prince Rama from the Hindu epic, Ramayana. The dance is characterized by elaborate costumes, intricate choreography, and vibrant music, making it a fascinating and engaging cultural performance.",
                "Attending a Rahwana Dance show is a captivating cultural experience that offers a glimpse into Balinese mythology and tradition. The audience will witness intricate choreography, vibrant music, and colorful costumes as the performers act out the epic battle between Rahwana and Prince Rama. The show is a feast for the senses, filled with drama, suspense, and awe-inspiring dance moves, making it an unforgettable experience for anyone interested in Balinese culture."
        ));

       ticketDataVector.add(new TicketData(2, "Saman Dance", 400, "Tuesday, 8 August 2023", R.drawable.saman,
               "11:00 - 12.00",
                "Witness the awe-inspiring unity of rhythmic movements and soulful chants in the traditional Acehnese dance, Saman.",
                "Saman is a traditional Acehnese dance featuring complex rhythmic patterns, singing, and chanting performed by a group of dancers. It showcases Acehnese cultural heritage and is accompanied by traditional music played on kendang drum, gong, and flute.",
                "Experience the captivating cultural heritage of Aceh through the mesmerizing performance of Saman dance. Watch a group of performers execute intricate rhythmic patterns while chanting and singing in unison. This traditional dance showcases the communal spirit and cultural richness of the Acehnese people, and the accompanying traditional music played on kendang drum, gong, and flute will transport you to the heart of Acehnese culture."

        ));

        ticketDataVector.add(new TicketData(3, "Sajojo Dance", 150, "Wednesday, 9 August 2023", R.drawable.papua,
                "16:00 - 18:00",
                "Experience the dynamic beat and colorful celebration of Papua with the energetic Sajojo dance!",
                "Sajojo is a traditional Papuan dance that celebrates joy, unity, and cultural diversity, and has been recognized by UNESCO as an intangible cultural heritage of humanity. This lively and colorful dance features upbeat moves, bamboo percussion instruments, and traditional costumes, offering a mesmerizing cultural experience that showcases the vibrant culture of Papua.",
                "Sajojo is a vibrant and lively traditional Papuan dance that celebrates joy, unity, and cultural diversity. The dance features energetic and colorful performances with upbeat dance moves and bamboo percussion instruments, and traditional costumes. Sajojo offers a mesmerizing cultural experience that showcases the vibrant culture of Papua, making it a must-see performance for anyone interested in traditional dances."
        ));


        ticketDataVector.add(new TicketData(4, "Colossal Dance", 250, "Thursday, 10 August 2023", R.drawable.teater,
                "18:00 - 20:00",
                "Experience the Majestic Grandeur and Awaken Your Imagination with the Mesmerizing Kolossal Dance Performance!",
                "Kolosal dance is a grandiose and spectacular performance that showcases a larger-than-life depiction of a cultural or historical event. The dance is characterized by intricate choreography, stunning visual effects, and elaborate costumes, accompanied by powerful music that captures the essence of the performance. Kolosal dance offers a mesmerizing cultural experience that leaves a lasting impression on the audience and serves as a celebration of a rich cultural heritage.",
                "A Colossal Dance performance is a grand and awe-inspiring cultural experience that showcases the rich heritage and traditions of a community or country. The dance is characterized by a large number of performers, elaborate costumes, and intricate choreography that tells a captivating story or expresses powerful emotions. The audience will be mesmerized by the stunning visual spectacle and the moving music, making it a truly unforgettable experience for anyone interested in cultural performances."
        ));

        ticketDataVector.add(new TicketData(5, "Java Theatrical", 350, "Friday, 11 August 2023", R.drawable.sendratari,
                "10:00 - 13:00",
                "Witness the Epic Battle and Experience the Magic of Java Theatrical Dance!",
                "Java Theatrical is a unique form of Javanese performance art that combines dance, music, and storytelling to retell traditional tales and legends. With its ornate costumes, intricate movements, and mesmerizing music, the Java Theatrical offers an immersive cultural experience that highlights the rich history and traditions of Java.",
                "Attending a Java theatrical performance is an immersive cultural experience that showcases the rich history and tradition of the island, featuring elaborate costumes, intricate choreography, and a captivating blend of music and storytelling that transports the audience to another time and place."
        ));

        adapter = new TicketAdapter(ticketDataVector, requireContext());
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickCallBack(new TicketAdapter.OnItemClickCallBack() {
            @Override
            public void onItemClicked(TicketData data) {
                Intent moveIntent = new Intent(getActivity(), DetailTicketActivity.class);
                moveIntent.putExtra(DetailTicketActivity.ITEM_EXTRA, data);
                startActivity(moveIntent);
            }
        });


        return view;
    }
}
