package com.example.catanddog.viewmodel;

import androidx.lifecycle.MediatorLiveData;

import com.example.catanddog.repositories.AnimalRepos;

import javax.inject.Inject;

public class DogDataViewModel extends AnimalDataViewModel {
    @Inject
    DogDataViewModel(AnimalRepos animalRepository) {
        animalResponseMediatorLiveData = new MediatorLiveData<>();
        animalRepos = animalRepository;
    }
}
