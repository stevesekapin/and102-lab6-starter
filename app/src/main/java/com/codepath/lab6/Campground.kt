package com.codepath.lab6

import java.io.Serializable

data class Campground(
    val name: String,
    val description: String,
    val latLong: String,
    val imageUrl: String
) : Serializable