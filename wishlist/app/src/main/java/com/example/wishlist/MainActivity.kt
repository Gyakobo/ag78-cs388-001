package com.example.wishlist

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var itemAdapter: ItemAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val itemList = mutableListOf<Item>()
        itemAdapter = ItemAdapter(itemList)

        recyclerView = findViewById(R.id.recyclerView)
        recyclerView.adapter = itemAdapter
        recyclerView.layoutManager = LinearLayoutManager(this)

        val etItemName = findViewById<EditText>(R.id.etItemName)
        val etItemDescription = findViewById<EditText>(R.id.etItemDescription)
        val etItemPrice = findViewById<EditText>(R.id.etItemPrice)
        val btnAddItem = findViewById<Button>(R.id.btnAddItem)

        btnAddItem.setOnClickListener {
            val name = etItemName.text.toString()
            val description = etItemDescription.text.toString()
            val price = etItemPrice.text.toString().toDoubleOrNull()

            if (name.isNotEmpty() && description.isNotEmpty() && price != null) {
                val newItem = Item(name, description, price)
                itemAdapter.addItem(newItem)

                etItemName.text.clear()
                etItemDescription.text.clear()
                etItemPrice.text.clear()
            } else {
                Toast.makeText(this, "Please enter valid data", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
