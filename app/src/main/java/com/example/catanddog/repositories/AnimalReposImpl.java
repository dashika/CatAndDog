package com.example.catanddog.repositories;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.catanddog.api.AnimalAPIService;
import com.example.catanddog.entities.AnimalData;
import com.example.catanddog.entities.AnimalResponse;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnimalReposImpl implements AnimalRepos {

    @Inject
    AnimalAPIService mApiService;

    @Inject
    AnimalReposImpl() {

    }

    @Override
    public LiveData<AnimalResponse> getAnimals(String query) {
        final MutableLiveData<AnimalResponse> data = new MutableLiveData<>();
        Call<AnimalData> call = mApiService.getAnimal(query);
        call.enqueue(new Callback<AnimalData>() {
            @Override
            public void onResponse(Call<AnimalData> call, Response<AnimalData> response) {
                data.setValue(new AnimalResponse(response.body()));
            }

            @Override
            public void onFailure(Call<AnimalData> call, Throwable t) {
                data.setValue(new AnimalResponse(t));
            }
        });

        return data;
    }
}
