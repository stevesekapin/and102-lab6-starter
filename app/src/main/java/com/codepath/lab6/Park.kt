package com.codepath.lab6

import java.io.Serializable

data class Park(
    val fullName: String,
    val description: String,
    val imageUrl: String
) : Serializable