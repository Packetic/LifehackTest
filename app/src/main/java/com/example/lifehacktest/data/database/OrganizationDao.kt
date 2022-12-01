package com.example.lifehacktest.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface OrganizationDao {
    @Query("SELECT * FROM organizations_list ORDER BY id")
    fun getOrganizationsList(): LiveData<List<OrganizationDbModel>>

    @Query("SELECT * FROM organization_details WHERE id == :id LIMIT 1")
    fun getOrganizationDetails(id: String): LiveData<OrganizationDetailsDbModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrganization(organization: OrganizationDbModel)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrganizationDetails(organizationDetails: OrganizationDetailsDbModel)
}