package com.codepath.lab6

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CampgroundsFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: CampgroundAdapter
    private val campgrounds = mutableListOf<Campground>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_campgrounds, container, false)

        recyclerView = view.findViewById(R.id.recyclerView)
        adapter = CampgroundAdapter(requireContext(), campgrounds)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter

        loadSampleCampgrounds()

        return view
    }

    private fun loadSampleCampgrounds() {
        campgrounds.clear()

        campgrounds.add(
            Campground(
                name = "Yosemite Valley Campground",
                description = "Beautiful campground with scenic mountain views.",
                latLong = "37.7456, -119.5936",
                imageUrl = "https://images.unsplash.com/photo-1506744038136-46273834b3fb"
            )
        )

        campgrounds.add(
            Campground(
                name = "Grand Canyon Campground",
                description = "Campground near trails and canyon overlooks.",
                latLong = "36.0544, -112.1401",
                imageUrl = "https://images.unsplash.com/photo-1500530855697-b586d89ba3ee"
            )
        )

        adapter.notifyDataSetChanged()
    }
}