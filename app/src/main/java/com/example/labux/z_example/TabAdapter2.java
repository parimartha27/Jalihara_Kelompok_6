package com.example.labux.z_example;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.labux.about_contact_us.TabAboutUsFragment;
import com.example.labux.about_contact_us.TabContactUsFragment;

public class TabAdapter2 extends FragmentStateAdapter {
    public TabAdapter2(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 1:return new TabAboutUsFragment();
            default: return new TabContactUsFragment();
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
