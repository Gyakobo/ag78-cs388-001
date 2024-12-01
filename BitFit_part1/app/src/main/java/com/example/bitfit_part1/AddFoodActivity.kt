package com.example.bitfit_part1

import android.app.Activity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class AddFoodActivity : AppCompatActivity() {

    private lateinit var etFoodName: EditText // Declare EditText for food name
    private lateinit var etCalories: EditText // Declare EditText for calories
    private lateinit var btnSaveFood: Button // Declare Save button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_food)

        // Initialize views
        etFoodName = findViewById(R.id.etFoodName)
        etCalories = findViewById(R.id.etCalories)
        btnSaveFood = findViewById(R.id.btnSaveFood)

        // Set up Save button click listener
        btnSaveFood.setOnClickListener {
            val foodName = etFoodName.text.toString()
            val calories = etCalories.text.toString().toIntOrNull() ?: 0

            if (foodName.isBlank() || calories <= 0) {
                // Show a toast message if input is invalid
                Toast.makeText(this, "Please enter valid food details", Toast.LENGTH_SHORT).show()
            } else {
                // Pass the result back to MainActivity
                val resultIntent = intent
                resultIntent.putExtra("FOOD_NAME", foodName)
                resultIntent.putExtra("FOOD_CALORIES", calories)
                setResult(Activity.RESULT_OK, resultIntent)
                finish()
            }
        }
    }
}
