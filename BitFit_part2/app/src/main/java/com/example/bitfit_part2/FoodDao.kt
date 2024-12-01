package com.example.bitfit_part2

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.lifecycle.LiveData

@Dao
interface FoodDao {

    // Insert a single Food item
    @Insert
    suspend fun insert(food: Food)

    // Retrieve all Food items
    @Query("SELECT * FROM food_table")
    fun getAllFoods(): LiveData<List<Food>>
}
