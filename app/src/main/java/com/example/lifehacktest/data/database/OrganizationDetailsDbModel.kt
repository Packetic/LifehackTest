package com.example.lifehacktest.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "organization_details")
data class OrganizationDetailsDbModel(
    @PrimaryKey
    val id: String,
    val description: String,
    val img: String,
    val lat: Double,
    val lon: Double,
    val name: String,
    val phone: String,
    val www: String
)