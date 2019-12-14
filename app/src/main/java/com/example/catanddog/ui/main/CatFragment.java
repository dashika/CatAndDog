package com.example.catanddog.ui.main;

import androidx.lifecycle.ViewModelProviders;

import com.example.catanddog.R;
import com.example.catanddog.viewmodel.AnimalDataViewModel;
import com.example.catanddog.viewmodel.CatDataViewModel;

import java.util.Objects;

public class CatFragment extends MainFragment {

    public static CatFragment newInstanceCat() {
        return new CatFragment();
    }

    @Override
    String getAnimal() {
        return getString(R.string.tab_1);
    }

    @Override
    AnimalDataViewModel setViewModel(AnimalDataViewModel animalViewModel) {
        return ViewModelProviders.of(Objects.requireNonNull(getActivity()), viewModelFactory)
                .get(CatDataViewModel.class);
    }
}
