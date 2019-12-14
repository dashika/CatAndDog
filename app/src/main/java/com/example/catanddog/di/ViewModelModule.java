package com.example.catanddog.di;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.catanddog.viewmodel.AnimalDataViewModel;
import com.example.catanddog.viewmodel.CatDataViewModel;
import com.example.catanddog.viewmodel.DogDataViewModel;
import com.example.catanddog.viewmodel.ViewModelFactory;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(CatDataViewModel.class)
    abstract ViewModel bindCatViewModel(CatDataViewModel catDataViewModel);

    @Binds
    @IntoMap
    @ViewModelKey(DogDataViewModel.class)
    abstract ViewModel bindDogViewModel(DogDataViewModel dogDataViewModel);

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(ViewModelFactory factory);
}
