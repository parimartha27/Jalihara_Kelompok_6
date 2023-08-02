package com.example.labux.home;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import com.example.labux.R;
import com.example.labux.activity.LoginActivity;
import com.example.labux.ticket.TicketFragment;

import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {

    private TextView helloHome;
    ViewPager2 viewPager2;

    Button button;
    private int currentPage = 0;
    private final long AUTO_SLIDE_DELAY = 1000;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        helloHome = view.findViewById(R.id.HelloHome);

        helloHome.setText(" "+ LoginActivity.globalUsername + " ");

        viewPager2 = view.findViewById(R.id.carousel);

        List<SliderItem> sliderItems = new ArrayList<>();
        sliderItems.add(new SliderItem(R.drawable.photo1));
        sliderItems.add(new SliderItem(R.drawable.photo2));
        sliderItems.add(new SliderItem(R.drawable.photo3));

        SliderAdapter sliderAdapter = new SliderAdapter(sliderItems, viewPager2);
        viewPager2.setAdapter(sliderAdapter);

        int initialPosition = Integer.MAX_VALUE / 2;
        viewPager2.setCurrentItem(initialPosition, false);

        final Handler handler = new Handler();
        final Runnable update = new Runnable() {
            public void run() {
                viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1, true);
                handler.postDelayed(this, AUTO_SLIDE_DELAY);
            }
        };

        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                currentPage = position;
            }
        });

        handler.postDelayed(update, AUTO_SLIDE_DELAY);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.85f + r * 0.15f);
            }
        });

        viewPager2.setPageTransformer(compositePageTransformer);

        button = view.findViewById(R.id.home_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Fragment ticket_fragment = new TicketFragment();
                FragmentTransaction ticketTransaction = getActivity().getSupportFragmentManager().beginTransaction();
                ticketTransaction.replace(R.id.fragment_container, ticket_fragment).commit();
            }
        });

        return view;
    }
}
