package com.example.exampleexchanges.data

import com.example.exampleexchanges.data.remote.dto.Exchange
import retrofit2.http.GET
import retrofit2.http.Path

interface ExchangeAPI {
    @GET("/v1/exchanges")
    suspend fun getExchange(): List<Exchange>

    @GET("/v1/exchanges/{exchangeId}")
    suspend fun getExchange(@Path("exchangeId") exchangeId: String): Exchange
}