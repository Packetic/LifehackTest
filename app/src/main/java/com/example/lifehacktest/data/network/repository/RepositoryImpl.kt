package com.example.lifehacktest.data.network.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.lifehacktest.data.database.AppDatabase
import com.example.lifehacktest.data.database.OrganizationDbModel
import com.example.lifehacktest.data.database.OrganizationDetailsDbModel
import com.example.lifehacktest.data.mapper.Mapper
import com.example.lifehacktest.data.network.ApiFactory
import com.example.lifehacktest.domain.Organization
import com.example.lifehacktest.domain.OrganizationDetails
import com.example.lifehacktest.domain.repository.Repository

class RepositoryImpl(private val application: Application) : Repository {

    private val organizationDao = AppDatabase.getInstance(application).organizationDao()
    private val mapper = Mapper()
    private val apiService = ApiFactory.apiService

    override fun getOrganizationList(): LiveData<List<Organization>> =
        Transformations.map(organizationDao.getOrganizationsList()) {
            it.map { model -> mapper.mapOrganizationDbModelToEntity(model) }
        }

    override fun getOrganizationDetails(id: String): LiveData<OrganizationDetails> =
        Transformations.map(organizationDao.getOrganizationDetails(id)) {
            if (it != null) {
                mapper.mapOrganizationDetailsDbModelToEntity(it)
            } else {
                mapper.mapOrganizationDetailsDbModelToEntity(
                    OrganizationDetailsDbModel(
                        id = id,
                        description = "",
                        img = "http://clipart-library.com/images/BcgE5k4xi.png",
                        lat = 0.0,
                        lon = 0.0,
                        name = "Не удалось загрузить данные",
                        phone = "",
                        www = ""
                    )
                )
            }
        }

    override suspend fun loadOrganizationData() {
        try {
            val organizations = apiService.getOrganizationsList()
            val dbModelList = organizations.map { mapper.mapOrganizationDtoToDbModel(it) }
            organizationDao.insertOrganizationsList(dbModelList)
        } catch (e: Exception) {
            Log.d(NAME_ORGANIZATION, "Failed to load data")
        }
    }

    override suspend fun loadOrganizationDetails(id: String) {
        try {
            val details = apiService.getOrganizationDetails(id)[0]
            Log.d(NAME_DETAILS, details.toString())
            val dbModelDetails = mapper.mapOrganizationDetailsDtoToDbModel(details)
            organizationDao.insertOrganizationDetails(dbModelDetails)
        } catch (e: Exception) {
            Log.d(NAME_DETAILS, "Failed to load data")
        }
    }

    companion object {
        private const val NAME_ORGANIZATION = "loadOrganizationData"
        private const val NAME_DETAILS = "loadOrganizationDetails"
    }
}