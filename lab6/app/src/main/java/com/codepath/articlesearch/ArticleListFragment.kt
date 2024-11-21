package com.codepath.articlesearch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

private const val TAG = "ArticleListFragment"
private const val SEARCH_API_KEY = BuildConfig.API_KEY
private const val ARTICLE_SEARCH_URL =
    "https://api.nytimes.com/svc/search/v2/articlesearch.json?api-key=${SEARCH_API_KEY}"

class ArticleListFragment : Fragment() {

    // Add these properties
    private val articles = mutableListOf<Article>()
    private lateinit var articlesRecyclerView: RecyclerView
    private lateinit var articleAdapter: ArticleAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_article_list, container, false)
    }

    companion object {
        fun newInstance(): ArticleListFragment {
            return ArticleListFragment()
        }
    }
}