package com.example.bitfit_part1

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_add_food.*

class AddFoodActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_food)

        btnSaveFood.setOnClickListener {
            val foodName = etFoodName.text.toString()
            val calories = etCalories.text.toString().toIntOrNull() ?: 0

            val resultIntent = intent
            resultIntent.putExtra("FOOD_NAME", foodName)
            resultIntent.putExtra("FOOD_CALORIES", calories)
            setResult(Activity.RESULT_OK, resultIntent)
            finish()
        }
    }
}
