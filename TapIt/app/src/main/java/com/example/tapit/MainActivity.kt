package com.example.tapit

// My imports
import android.widget.Button
import android.widget.TextView
import android.view.View

// Miscellaneous
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    var counter = 0;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = findViewById<Button>(R.id.UpdateCounterButton)

        val textView = findViewById<TextView>(R.id.textView)

        textView.text = counter.toString()

        button.setOnClickListener {
            // Toast.makeText(it.context, "Clicked Button!", Toast.LENGTH_SHORT).show()
            counter++
            textView.text = counter.toString()
        }

        val upgradeButton = findViewById<Button>(R.id.UpgradeStepCounter)

        var reached_hundred = true

        button.setOnClickListener {
            counter++
            textView.text = counter.toString()


            if (counter >= 100) {

                // Show upgrade button and set onClickListener
                upgradeButton.visibility = View.VISIBLE

                // Switched to double counter
                if (reached_hundred) {
                    Toast.makeText(it.context, "Reached the 100th count", Toast.LENGTH_SHORT).show()
                    reached_hundred = false
                }

                upgradeButton.setOnClickListener {
                    // Switched to double counter
                    Toast.makeText(it.context, "Switched to Double Counter", Toast.LENGTH_SHORT).show()

                    // Update original button to add 2 instead of `
                    button.setOnClickListener {
                        counter += 2
                        textView.text = counter.toString()
                    }

                    // Hide upgrade button again
                    upgradeButton.visibility = View.INVISIBLE
                }
            }
        }
    }
}