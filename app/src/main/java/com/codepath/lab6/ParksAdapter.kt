package com.codepath.lab6

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ParksAdapter(
    private val context: Context,
    private val parks: List<Park>
) : RecyclerView.Adapter<ParksAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val parkImage: ImageView = view.findViewById(R.id.parkImage)
        val parkName: TextView = view.findViewById(R.id.parkName)
        val parkDescription: TextView = view.findViewById(R.id.parkDescription)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_park, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = parks.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val park = parks[position]

        holder.parkName.text = park.fullName
        holder.parkDescription.text = park.description

        Glide.with(context)
            .load(park.imageUrl)
            .into(holder.parkImage)
    }
}