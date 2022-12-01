package com.example.lifehacktest.domain

import com.example.lifehacktest.domain.repository.Repository

class LoadDataUseCase(private val repository: Repository) {
    operator fun invoke() = repository.loadData()
}