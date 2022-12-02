package com.example.lifehacktest.data.network.repository

import android.app.Application
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.example.lifehacktest.data.database.AppDatabase
import com.example.lifehacktest.data.database.OrganizationDao
import com.example.lifehacktest.data.mapper.Mapper
import com.example.lifehacktest.domain.Organization
import com.example.lifehacktest.domain.OrganizationDetails
import com.example.lifehacktest.domain.repository.Repository

class RepositoryImpl(private val application: Application) : Repository {

    private val organizationDao = AppDatabase.getInstance(application).organizationDao()
    private val mapper = Mapper()

    override fun getOrganizationList(): LiveData<List<Organization>> =
        Transformations.map(organizationDao.getOrganizationsList()) {
            it.map { model -> mapper.mapOrganizationDbModelToEntity(model) }
        }

    override fun getOrganizationDetails(id: String): LiveData<OrganizationDetails> =
        Transformations.map(organizationDao.getOrganizationDetails(id)) {
            mapper.mapOrganizationDetailsDbModelToEntity(it)
        }

    override fun loadData() {
        try {
            val topCoins = apiService.getTopCoinsInfo(limit = 50)
            val fSyms = mapper.mapNamesListToString(topCoins)
            val jsonContainer = apiService.getFullPriceList(fSyms = fSyms)
            val coinInfoDtoList = mapper.mapJsonContainerToListCoinInfo(jsonContainer)
            val dbModelList = coinInfoDtoList.map { mapper.mapDtoToDbModel(it) }
            organizationDao.insertPriceList(dbModelList)
        } catch (e: Exception) {
            Log.d(NAME, "Failed to load data")
        }
    }

    companion object {

    }

}