package com.example.lifehacktest.domain

import com.example.lifehacktest.domain.repository.Repository

class LoadOrganizationDataUseCase(private val repository: Repository) {
    suspend operator fun invoke() = repository.loadOrganizationData()
}