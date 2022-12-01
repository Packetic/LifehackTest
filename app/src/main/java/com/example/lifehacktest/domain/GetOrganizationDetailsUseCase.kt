package com.example.lifehacktest.domain

import com.example.lifehacktest.domain.repository.Repository

class GetOrganizationDetailsUseCase(private val repository: Repository) {
    operator fun invoke(id: String) = repository.getOrganizationDetails(id)
}