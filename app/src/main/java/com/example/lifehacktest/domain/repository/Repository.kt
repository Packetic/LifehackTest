package com.example.lifehacktest.domain.repository

import androidx.lifecycle.LiveData
import com.example.lifehacktest.domain.Organization
import com.example.lifehacktest.domain.OrganizationDetails

interface Repository {
    fun getOrganizationList(): LiveData<List<Organization>>
    fun getOrganizationDetails(id: String): LiveData<OrganizationDetails>
    fun loadData()
}