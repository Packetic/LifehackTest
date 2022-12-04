package com.example.lifehacktest.data.network.model

import androidx.room.Entity

data class OrganizationDetailsDto(
    val description: String,
    val id: String,
    val img: String,
    val lat: Double,
    val lon: Double,
    val name: String,
    val phone: String,
    val www: String
)