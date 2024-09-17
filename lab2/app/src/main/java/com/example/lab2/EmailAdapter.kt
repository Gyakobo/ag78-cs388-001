package com.example.lab2

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EmailAdapter {
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        // TODO: Create member variables for any view that will be set
        // as you render a row.

        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each sub-view

        val senderTextView: TextView
        val titleTextView: TextView
        val summaryTextView: TextView

        init {
            // TODO: Store each of the layout's views into
            // the public final member variables created above
            senderTextView = itemView.findViewById(R.id.senderTv)
            titleTextView = itemView.findViewById(R.id.titleTv)
            summaryTextView = itemView.findViewById(R.id.summaryTv)
        }
    }
}