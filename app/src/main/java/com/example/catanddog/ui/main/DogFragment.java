package com.example.catanddog.ui.main;

import androidx.lifecycle.ViewModelProviders;

import com.example.catanddog.R;
import com.example.catanddog.viewmodel.AnimalDataViewModel;
import com.example.catanddog.viewmodel.DogDataViewModel;

import java.util.Objects;

public class DogFragment extends MainFragment {

    public static DogFragment newInstanceDog() {
        return new DogFragment();
    }

    @Override
    String getAnimal() {
        return getString(R.string.tab_2);
    }

    @Override
    AnimalDataViewModel setViewModel(AnimalDataViewModel animalViewModel) {
        return ViewModelProviders.of(Objects.requireNonNull(getActivity()), viewModelFactory)
                .get(DogDataViewModel.class);
    }
}
