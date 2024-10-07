package com.example.catfact.data.remote

import com.example.catfact.data.model.CatFact
import retrofit2.Response
import retrofit2.http.GET

interface CatFactApiService {
    @GET("/fact")
    suspend fun getFact():Response<CatFact>
}