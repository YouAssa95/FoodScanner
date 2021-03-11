package com.univ_amu.food_scanner.viewmodels;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.univ_amu.food_scanner.data.Food;
import com.univ_amu.food_scanner.data.Quantity;
import com.univ_amu.food_scanner.data.Repository;

import java.util.List;

public class FoodViewModel extends ViewModel {
    private final Repository repository;
    private final LiveData<Food> food;
    private final LiveData<List<Quantity>> quantities;

    public FoodViewModel(Application application, String code) {
        Context context = application.getApplicationContext();
        this.repository = new Repository(context);
        this.food = repository.getFood(code);
        this.quantities = repository.getQuantities(code);
    }

    public LiveData<Food> food() {
        return food;
    }

    public LiveData<List<Quantity>> quantities() {
        return quantities;
    }
}