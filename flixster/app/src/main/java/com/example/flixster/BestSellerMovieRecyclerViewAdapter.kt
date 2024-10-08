package com.example.flixster

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy


class BestSellerMovieRecyclerViewAdapter(
    private val books: List<BestSellerMovie>,
    private val mListener: OnListFragmentInteractionListener?
)
    : RecyclerView.Adapter<BestSellerMovieRecyclerViewAdapter.BookViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_best_seller_movie, parent, false)
        return BookViewHolder(view)
    }

    /**
     * This inner class lets us refer to all the different View elements
     * (Yes, the same ones as in the XML layout files!)
     */
    inner class BookViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        var mItem: BestSellerMovie? = null
        val mBookTitle: TextView = mView.findViewById<View>(R.id.book_title) as TextView

        // My adjustments

        val mBookDescription: TextView = mView.findViewById<View>(R.id.book_description) as TextView
        val mBookImage: ImageView = mView.findViewById<View>(R.id.book_image) as ImageView

        /*override fun toString(): String {
            return mBookTitle.toString() + " '" + mBookAuthor.text + "'"
        }*/
    }

    /**
     * This lets us "bind" each Views in the ViewHolder to its' actual data!
     */
    override fun onBindViewHolder(holder: BookViewHolder, position: Int) {
        val book = books[position]

        holder.mItem = book
        holder.mBookTitle.text = book.title
        holder.mBookDescription.text = book.description

        Glide.with(holder.mView)
            .load("https://image.tmdb.org/t/p/w500${book.bookImageUrl}")
            .placeholder(R.drawable.ic_launcher_background)  // Show while loading
            .error(R.drawable.ic_launcher_foreground)          // Show on error
            .into(holder.mBookImage)

        holder.mView.setOnClickListener {
            holder.mItem?.let { movie ->
                mListener?.onItemClick(movie)
            }
        }
    }

    /**
     * Remember: RecyclerView adapters require a getItemCount() method.
     */
    override fun getItemCount(): Int {
        return books.size
    }
}