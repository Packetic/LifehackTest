package com.example.lifehacktest.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import com.example.lifehacktest.data.network.repository.RepositoryImpl
import com.example.lifehacktest.domain.GetOrganizationDetailsUseCase
import com.example.lifehacktest.domain.GetOrganizationListUseCase
import com.example.lifehacktest.domain.LoadDataUseCase

class OrganizationViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = RepositoryImpl(application)

    private val getOrganizationListUseCase = GetOrganizationListUseCase(repository)
    private val getOrganizationDetailsUseCase = GetOrganizationDetailsUseCase(repository)
    private val loadDataUseCase = LoadDataUseCase(repository)

    val organizationList = getOrganizationListUseCase()

    fun getOrganizationDetails(id: String) = getOrganizationDetailsUseCase(id)

    fun loadData() = loadDataUseCase()
}