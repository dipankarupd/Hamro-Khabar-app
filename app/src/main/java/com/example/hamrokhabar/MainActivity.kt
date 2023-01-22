package com.example.hamrokhabar

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray

class MainActivity : AppCompatActivity() , ItemClicked {

    // mAdapter is a member primitive ... so we write it as mAdapter
    private lateinit var mAdapter : HamroKhabarAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView.layoutManager = LinearLayoutManager(this)
        getData()
        mAdapter = HamroKhabarAdapter(this)
        recyclerView.adapter = mAdapter
    }

    fun getData (){

        // get the data from the API:

        val url = "https://newsdata.io/api/1/news?apikey=pub_15840a12b4b14666f37359947a96e88056ffb&country=in&language=en"


        val jsonObjectRequest = JsonObjectRequest(
            Request.Method.GET, url, null,
            {
                //in the url, the news are stored in the form of JSON array with name "articles
                // so first we get that array as follows:

                var newsJsonArray = it.getJSONArray("results")
                // creating a new arraylist of type News to store the needed things from the json array
                val newsList = ArrayList<News>()

                // traversing in the loop to access each json obj from the array
                for(i in 0 until newsJsonArray.length()) {

                    // to get the elements from the json array
                    val newsJsonObj = newsJsonArray.getJSONObject(i)

                    // creating the object of news and adding the related info from json obj
                    val news  = News(
                        newsJsonObj.getString("title"),
                        newsJsonObj.getString("source_id"),
                        newsJsonObj.getString("link")
                    )
                    newsList.add(news)

                }
                mAdapter.updateList(newsList)
            },
            {

            }
        )

// Access the RequestQueue through your singleton class.
        MySingleton.getInstance(this).addToRequestQueue(jsonObjectRequest)
    }

    override fun itemClicked(x: News) {

        val builder = CustomTabsIntent.Builder();
        val customTabsIntent = builder.build()
        customTabsIntent.launchUrl(this, Uri.parse(x.url))
    }


}