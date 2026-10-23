package com.example.ecoconso.data.api

import com.example.ecoconso.data.models.ApplianceResponse
import com.example.ecoconso.data.models.ConsommationTodayResponse
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface EcoApiService {
    @GET("api/consommation/today")
    suspend fun getConsommationToday(): ConsommationTodayResponse

    @GET("api/consommation/appliances")
    suspend fun getAppliances(): List<ApplianceResponse>
}

object ApiClient {
    // 127.0.0.1 fonctionne de manière fiable à 100% avec 'adb reverse tcp:3000 tcp:3000'
    private const val BASE_URL = "http://127.0.0.1:3000/"

    val ecoApiService: EcoApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(EcoApiService::class.java)
    }
}
