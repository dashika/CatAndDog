package com.example.catanddog.di;

import com.example.catanddog.api.AnimalAPIService;
import com.example.catanddog.repositories.AnimalRepos;
import com.example.catanddog.repositories.AnimalReposImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module(includes = ViewModelModule.class)
class AppModule {
    private static final String BASE_URL = "http://kot3.com/";

    @Singleton
    @Provides
    AnimalAPIService provideAnimalAPIService() {
        return new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build()
                .create(AnimalAPIService.class);
    }

    @Singleton
    @Provides
    AnimalRepos provideAnimalRepos(AnimalReposImpl animalRepos_) {
        return animalRepos_;
    }


}
