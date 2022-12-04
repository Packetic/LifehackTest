package com.example.lifehacktest.domain

import com.example.lifehacktest.domain.repository.Repository

class LoadOrganizationDetailsUseCase(private val repository: Repository) {
    suspend operator fun invoke(id: String) = repository.loadOrganizationDetails(id)
}