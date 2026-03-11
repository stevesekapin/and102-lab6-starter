package com.codepath.lab6

import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide

const val PARK_EXTRA = "PARK_EXTRA"

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val detailImageView: ImageView = findViewById(R.id.detailImageView)
        val detailTitleTextView: TextView = findViewById(R.id.detailTitleTextView)
        val detailDescriptionTextView: TextView = findViewById(R.id.detailDescriptionTextView)

        val park = intent.getSerializableExtra(PARK_EXTRA) as? Park
        val campground = intent.getSerializableExtra(CAMPGROUND_EXTRA) as? Campground

        when {
            park != null -> {
                detailTitleTextView.text = park.fullName
                detailDescriptionTextView.text = park.description
                Glide.with(this)
                    .load(park.imageUrl)
                    .into(detailImageView)
            }

            campground != null -> {
                detailTitleTextView.text = campground.name
                detailDescriptionTextView.text = campground.description
                Glide.with(this)
                    .load(campground.imageUrl)
                    .into(detailImageView)
            }
        }
    }
}