package com.example.lifehacktest.data.network

import com.example.lifehacktest.data.network.model.OrganizationDetailsDto
import com.example.lifehacktest.data.network.model.OrganizationDto
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("test.php")
    suspend fun getOrganizationsList(): List<OrganizationDto>

    @GET("test.php")
    suspend fun getOrganizationDetails(
        @Query(ORGANIZATION_ID) id: String = "1"
    ): List<OrganizationDetailsDto>

    companion object {
        private const val ORGANIZATION_ID = "id"
    }
}