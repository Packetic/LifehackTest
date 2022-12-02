package com.example.lifehacktest.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "organizations_list")
data class OrganizationDbModel(
    @PrimaryKey
    val id: String,
    val img: String,
    val name: String
)