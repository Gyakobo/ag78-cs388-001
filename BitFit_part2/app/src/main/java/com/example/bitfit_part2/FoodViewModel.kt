package com.example.bitfit_part2

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

class FoodViewModel(application: Application) : AndroidViewModel(application) {
    private val foodDao = FoodDatabase.getDatabase(application).foodDao()
    val allFoods: LiveData<List<Food>> = foodDao.getAllFoods()
}
