package com.example.labux.z_example;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.labux.about_contact_us.AboutUsFragment;
import com.example.labux.about_contact_us.ContactUsFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {


    public ViewPagerAdapter(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
       switch (position){
           case 0:
               return new AboutUsFragment();
           case 1:
               return new ContactUsFragment();
           default:
               return new AboutUsFragment();
       }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
