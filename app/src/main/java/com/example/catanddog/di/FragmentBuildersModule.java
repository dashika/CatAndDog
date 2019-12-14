package com.example.catanddog.di;


import com.example.catanddog.ui.main.CatFragment;
import com.example.catanddog.ui.main.DogFragment;
import com.example.catanddog.ui.main.MainFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class FragmentBuildersModule {

    @ContributesAndroidInjector
    abstract CatFragment contributeCatFragment();

    @ContributesAndroidInjector
    abstract DogFragment contributeDogFragment();

}
