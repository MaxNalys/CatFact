package com.example.catfact.data.repository

import com.example.catfact.data.model.CatFact
import com.example.catfact.data.remote.CatFactApiService
import retrofit2.Response

class CatFactRepository(private val catApiService: CatFactApiService) {
    suspend fun getFact(): Response<CatFact> {
        return catApiService.getFact()
    }
}
