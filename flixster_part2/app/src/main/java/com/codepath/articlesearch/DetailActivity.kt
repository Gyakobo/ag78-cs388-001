package com.codepath.articlesearch

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

private const val TAG = "DetailActivity"

class DetailActivity : AppCompatActivity() {
    private lateinit var mediaImageView: ImageView
    private lateinit var mediaImageView_1: ImageView
    private lateinit var titleTextView: TextView
    private lateinit var bylineTextView: TextView
    private lateinit var abstractTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // TODO: Find the views for the screen
        mediaImageView =    findViewById(R.id.mediaImage)
        mediaImageView_1 =    findViewById(R.id.mediaImage_1)
        titleTextView =     findViewById(R.id.mediaTitle)
        bylineTextView =    findViewById(R.id.mediaByline)
        abstractTextView =  findViewById(R.id.mediaAbstract)

        // TODO: Get the extra from the Intent
        val article = intent.getSerializableExtra(ARTICLE_EXTRA) as Article

        // TODO: Set the title, byline, and abstract information from the article
        titleTextView.text = article.headline
        bylineTextView.text = "Known For: ${article.films?.get(0)?.movie_tile}"
        abstractTextView.text = article.films?.get(0)?.movie_overview

        // TODO: Load the media image
        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500${article.multimedia}")
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_foreground)
            .into(mediaImageView)

        Glide.with(this)
            .load("https://image.tmdb.org/t/p/w500${article.films?.get(0)?.backdrop_path}")
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_foreground)
            .into(mediaImageView_1)
    }
}