package com.example.exampleexchanges.data

import com.example.exampleexchanges.data.remote.dto.Exchange

data class ListStateExchange (
    val isLoading: Boolean = false,
    val exchange: List<Exchange> = emptyList(),
    val error: String = ""
)