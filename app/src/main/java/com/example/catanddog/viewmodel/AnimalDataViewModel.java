package com.example.catanddog.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.catanddog.entities.Animal;
import com.example.catanddog.entities.AnimalResponse;
import com.example.catanddog.repositories.AnimalRepos;

public abstract class AnimalDataViewModel extends ViewModel {

    private final MutableLiveData<Animal> selected = new MutableLiveData<>();
    MediatorLiveData<AnimalResponse> animalResponseMediatorLiveData;
    AnimalRepos animalRepos;

    public void select(Animal result) {
        selected.setValue(result);
    }

    public LiveData<Animal> getSelected() {
        return selected;
    }


    @NonNull
    public LiveData<AnimalResponse> getApiResponse() {
        return animalResponseMediatorLiveData;
    }

    public LiveData<AnimalResponse> loadAnimalData(String query) {
        animalResponseMediatorLiveData.addSource(
                animalRepos.getAnimals(query),
                animalResponse -> animalResponseMediatorLiveData.setValue(animalResponse));

        return animalResponseMediatorLiveData;
    }

}
