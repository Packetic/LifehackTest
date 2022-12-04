package com.example.lifehacktest.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.lifehacktest.data.network.repository.RepositoryImpl
import com.example.lifehacktest.domain.*
import kotlinx.coroutines.launch

class OrganizationViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = RepositoryImpl(application)

    private val getOrganizationListUseCase = GetOrganizationListUseCase(repository)
    private val getOrganizationDetailsUseCase = GetOrganizationDetailsUseCase(repository)
    private val loadOrganizationDataUseCase = LoadOrganizationDataUseCase(repository)
    private val loadOrganizationDetailsUseCase = LoadOrganizationDetailsUseCase(repository)

    val organizationList = getOrganizationListUseCase()

    fun getOrganizationDetails(id: String): LiveData<OrganizationDetails> =
        getOrganizationDetailsUseCase(id)

    fun loadOrganizationDetails(id: String) {
        viewModelScope.launch {
            loadOrganizationDetailsUseCase(id)
        }
    }

    init {
        viewModelScope.launch {
            loadOrganizationDataUseCase()
        }
    }
}