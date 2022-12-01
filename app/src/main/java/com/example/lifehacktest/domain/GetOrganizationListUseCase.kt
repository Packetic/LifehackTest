package com.example.lifehacktest.domain

import android.app.Application
import com.example.lifehacktest.data.network.repository.RepositoryImpl
import com.example.lifehacktest.domain.repository.Repository

class GetOrganizationListUseCase(private val repository: Repository) {
    operator fun invoke() = repository.getOrganizationList()
}