package com.example.apiwexchanges.data.remote.dto

data class Exchange(
    val name: String,
    val description: String ?,
    val active: Boolean = false,
    val last_updated: String
)