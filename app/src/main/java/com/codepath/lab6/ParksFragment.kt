package com.codepath.lab6

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.codepath.asynchttpclient.AsyncHttpClient
import com.codepath.asynchttpclient.callback.JsonHttpResponseHandler
import okhttp3.Headers
import org.json.JSONArray

private const val TAG = "ParksFragment"

class ParksFragment : Fragment() {

    private lateinit var parksRecyclerView: RecyclerView
    private lateinit var parksAdapter: ParksAdapter
    private val parks = mutableListOf<Park>()

    private val apiKey = BuildConfig.API_KEY
    private val parksUrl = "https://developer.nps.gov/api/v1/parks?api_key=$apiKey"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_parks, container, false)

        parksRecyclerView = view.findViewById(R.id.recyclerView)
        parksAdapter = ParksAdapter(requireContext(), parks)

        parksRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        parksRecyclerView.adapter = parksAdapter

        fetchParks()

        return view
    }

    private fun fetchParks() {
        val client = AsyncHttpClient()

        client.get(parksUrl, object : JsonHttpResponseHandler() {
            override fun onSuccess(
                statusCode: Int,
                headers: Headers?,
                json: JSON
            ) {
                val dataArray: JSONArray = json.jsonObject.getJSONArray("data")
                parks.clear()

                for (i in 0 until dataArray.length()) {
                    val parkJson = dataArray.getJSONObject(i)

                    val fullName = parkJson.optString("fullName", "No name")
                    val description = parkJson.optString("description", "No description")

                    var imageUrl = ""
                    val imagesArray = parkJson.optJSONArray("images")
                    if (imagesArray != null && imagesArray.length() > 0) {
                        imageUrl = imagesArray.getJSONObject(0).optString("url", "")
                    }

                    parks.add(
                        Park(
                            fullName = fullName,
                            description = description,
                            imageUrl = imageUrl
                        )
                    )
                }

                parksAdapter.notifyDataSetChanged()
            }

            override fun onFailure(
                statusCode: Int,
                headers: Headers?,
                response: String?,
                throwable: Throwable?
            ) {
                Log.e(TAG, "Failed to fetch parks", throwable)
            }
        })
    }
}