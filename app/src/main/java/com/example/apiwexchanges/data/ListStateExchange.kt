package com.example.apiwexchanges.data

import com.example.apiwexchanges.data.remote.dto.Exchange

data class ListStateExchange (
    val isLoading: Boolean = false,
    val exchange: List<Exchange> = emptyList(),
    val error: String = ""
)