package com.example.catanddog.repositories;

import androidx.lifecycle.LiveData;

import com.example.catanddog.entities.AnimalResponse;

public interface AnimalRepos {
    LiveData<AnimalResponse> getAnimals(String query);
}
