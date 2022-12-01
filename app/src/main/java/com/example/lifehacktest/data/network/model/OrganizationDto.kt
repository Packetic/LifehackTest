package com.example.lifehacktest.data.network.model

import androidx.room.Entity

@Entity(tableName = "organizations_list")
data class OrganizationDto(
    val id: String,
    val img: String,
    val name: String
)