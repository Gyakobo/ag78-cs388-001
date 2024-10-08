package com.example.flixster

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.ContentLoadingProgressBar
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.RequestParams
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import com.google.firebase.crashlytics.buildtools.reloc.com.google.common.reflect.TypeToken
import com.google.gson.Gson
import okhttp3.Headers
import org.json.JSONArray
import org.json.JSONObject

// import com.codepath.bestsellerlistapp.R

// --------------------------------//
// CHANGE THIS TO BE YOUR API KEY  //
// --------------------------------//
private const val API_KEY = "3184d2c7bfb87d2da34ad6df28b46b79"

class BestSellerMovieFragment : Fragment(), OnListFragmentInteractionListener {

    /*
     * Constructing the view
     */
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_best_seller_movie_list, container, false)
        val progressBar = view.findViewById<View>(R.id.progress) as ContentLoadingProgressBar
        val recyclerView = view.findViewById<View>(R.id.list) as RecyclerView
        val context = view.context
        recyclerView.layoutManager = GridLayoutManager(context, 1)
        updateAdapter(progressBar, recyclerView)
        return view
    }

    /*
     * Updates the RecyclerView adapter with new data.  This is where the
     * networking magic happens!
     */
    private fun updateAdapter(progressBar: ContentLoadingProgressBar, recyclerView: RecyclerView) {
        progressBar.show()

        // Create and set up an AsyncHTTPClient() here
        val client = AsyncHttpClient()
        // Headers.addHeader("Authorization", "Bearer " + API_KEY);
        // client.addHeader("accept", "application/json");

        val params = RequestParams()
        params["api_key"] = API_KEY

        // Using the client, perform the HTTP request
        client[
            "https://api.themoviedb.org/3/movie/now_playing",
            // "https://api.themoviedb.org/3/movie/now_playing?&api_key=3184d2c7bfb87d2da34ad6df28b46b79",
            params,
            object : JsonHttpResponseHandler()
            { //connect these callbacks to your API call
                override fun onSuccess(
                    statusCode: Int,
                    headers: Headers,
                    json: JSON
                ) {
                    // The wait for a response is over
                    progressBar.hide()

                    //TODO - Parse JSON into Models
                    // val resultsJSON : JSONObject = json.jsonObject.get("results") as JSONObject
                    // val booksRawJSON : String = resultsJSON.get("books").toString()

                    val booksRawJSON : String = (json.jsonObject.get("results") as JSONArray).toString()

                    Log.d("BestSellerMovieFragment", json.toString());

                    val gson = Gson()
                    val arrayBookType = object : TypeToken<List<BestSellerMovie>>() {}.type

                    val models : List<BestSellerMovie> = gson.fromJson(booksRawJSON, arrayBookType); // Fix me!
                    recyclerView.adapter = BestSellerMovieRecyclerViewAdapter(models, this@BestSellerMovieFragment)

                    // Look for this in Logcat:
                    Log.d("BestSellerMovieFragment", "response successful")
                }

                /*
                 * The onFailure function gets called when
                 * HTTP response status is "4XX" (eg. 401, 403, 404)
                 */
                override fun onFailure(
                    statusCode: Int,
                    headers: Headers?,
                    errorResponse: String,
                    t: Throwable?
                ) {
                    // The wait for a response is over
                    progressBar.hide()

                    // If the error is not null, log it!
                    t?.message?.let {
                        Log.e("BestSellerMovieFragment", errorResponse)
                    }
                }
            }]
    }

    /*
     * What happens when a particular book is clicked.
     */
    override fun onItemClick(item: BestSellerMovie) {
        Toast.makeText(context, "test: " + item.title, Toast.LENGTH_LONG).show()

    }

}
