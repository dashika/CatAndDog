package com.example.catanddog.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.catanddog.DetailActivity;
import com.example.catanddog.IOnClick;
import com.example.catanddog.R;
import com.example.catanddog.di.Injectable;
import com.example.catanddog.entities.Animal;
import com.example.catanddog.viewmodel.AnimalDataViewModel;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class MainFragment extends Fragment implements Injectable, IOnClick {

    @BindView(R.id.rvAnimalList)
    RecyclerView rvAnimalList;
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    private AnimalDataViewModel animalViewModel;
    private AnimalAdapter animalAdapter;

    abstract String getAnimal();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_fragment, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    abstract AnimalDataViewModel setViewModel(AnimalDataViewModel animalViewModel);

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        animalViewModel = setViewModel(animalViewModel);

        setUpView();
        animalViewModel.loadAnimalData(getAnimal());

        animalViewModel.getApiResponse().observe(this, animalResponse -> {
            if (animalResponse.getError() != null) {
                Toast.makeText(getContext(), "Error", Toast.LENGTH_LONG).show();
            } else {
                if (animalResponse.getAnimal()!= null)
                    animalAdapter.getAnimalResults(animalResponse.getAnimal().getData());
            }
        });
    }

    private void setUpView() {
        rvAnimalList.setHasFixedSize(true);
        animalAdapter = new AnimalAdapter(getContext(), animalViewModel, this);
        rvAnimalList.setAdapter(animalAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        rvAnimalList.setLayoutManager(linearLayoutManager);
    }

    @Override
    public void onClick(Animal animal){
       Intent intent= new Intent(getContext(), DetailActivity.class);
       intent.putExtra(Animal.class.getSimpleName(), animal);
       Objects.requireNonNull(getContext()).startActivity(intent);
    }

}
