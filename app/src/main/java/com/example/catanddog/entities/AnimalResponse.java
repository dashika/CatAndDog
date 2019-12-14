package com.example.catanddog.entities;

public class AnimalResponse {

    private AnimalData animal;
    private Throwable error;

    public AnimalResponse(AnimalData animal) {
        this.animal = animal;
        this.error = null;
    }

    public AnimalResponse(Throwable error) {
        this.error = error;
        animal = null;
    }

    public AnimalData getAnimal() {
        return animal;
    }

    public void setAnimal(AnimalData animal) {
        this.animal = animal;
    }

    public Throwable getError() {
        return error;
    }

    public void setError(Throwable error) {
        this.error = error;
    }
}
