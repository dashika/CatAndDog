package com.example.catanddog;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.catanddog.ui.main.CatFragment;
import com.example.catanddog.ui.main.DogFragment;
import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

public class MainActivity extends AppCompatActivity implements HasSupportFragmentInjector, TabLayout.OnTabSelectedListener {

    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        tabLayout = findViewById(R.id.tabLayout);
        if (getSupportFragmentManager().getFragments().size() == 0) {
            addFragment(DogFragment.newInstanceDog());
            addFragment(CatFragment.newInstanceCat());
        } else {
            Fragment fragment = getSupportFragmentManager().findFragmentByTag(CatFragment.class.getName());
            if (fragment != null)
                Objects.requireNonNull(tabLayout.getTabAt(fragment.isHidden() ? 1 : 0)).select();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        tabLayout.removeOnTabSelectedListener(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        tabLayout.addOnTabSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        finish();
        super.onBackPressed();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }


    void addFragment(Fragment fragment) {
        String backStateName = fragment.getClass().getName();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.addToBackStack(null);
        if (manager.findFragmentByTag(backStateName) == null) {
            ft.add(R.id.flContainer, fragment, backStateName);
        }
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    void changeView(String showTab, String hideTab) {
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.hide(Objects.requireNonNull(manager.findFragmentByTag(hideTab)));
        ft.show(Objects.requireNonNull(manager.findFragmentByTag(showTab)));
        ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        ft.commit();
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        switch (tab.getPosition()) {
            case 0: {
                changeView(CatFragment.class.getName(), DogFragment.class.getName());
                break;
            }
            case 1: {
                changeView(DogFragment.class.getName(), CatFragment.class.getName());
                break;
            }
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {

    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {

    }

}
