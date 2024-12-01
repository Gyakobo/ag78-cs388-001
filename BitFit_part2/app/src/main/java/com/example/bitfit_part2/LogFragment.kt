package com.example.bitfit_part2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class LogFragment : Fragment() {

    private lateinit var foodViewModel: FoodViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_log, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        foodViewModel = ViewModelProvider(this).get(FoodViewModel::class.java)

        // Observe data and update RecyclerView
        foodViewModel.allFoods.observe(viewLifecycleOwner) { foods ->
            recyclerView.adapter = FoodAdapter(foods)
        }

        return view
    }
}
