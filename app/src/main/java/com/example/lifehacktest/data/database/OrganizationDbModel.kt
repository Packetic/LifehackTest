package com.example.lifehacktest.data.database

import androidx.room.PrimaryKey

data class OrganizationDbModel(
    @PrimaryKey
    val id: String,
    val img: String,
    val name: String
)