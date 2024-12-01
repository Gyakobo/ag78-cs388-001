package com.example.bitfit_part1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.widget.Button

class MainActivity : AppCompatActivity() {

    private lateinit var rvFoodList: RecyclerView // Declare the RecyclerView
    private lateinit var btnAddFood: Button // Declare the Add Food button

    private val foodList = mutableListOf<FoodItem>() // List to hold food items
    private val foodAdapter = FoodAdapter(foodList) // Adapter for RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize the views
        rvFoodList = findViewById(R.id.rvFoodList)
        btnAddFood = findViewById(R.id.btnAddFood)

        // Set up the RecyclerView
        rvFoodList.layoutManager = LinearLayoutManager(this)
        rvFoodList.adapter = foodAdapter

        // Set up the button click listener
        btnAddFood.setOnClickListener {
            val intent = Intent(this, AddFoodActivity::class.java)
            startActivityForResult(intent, 100)
        }
    }

    // Handle the result from AddFoodActivity
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == RESULT_OK) {
            val name = data?.getStringExtra("FOOD_NAME") ?: ""
            val calories = data?.getIntExtra("FOOD_CALORIES", 0) ?: 0
            foodList.add(FoodItem(name, calories))
            foodAdapter.notifyDataSetChanged()
        }
    }
}
