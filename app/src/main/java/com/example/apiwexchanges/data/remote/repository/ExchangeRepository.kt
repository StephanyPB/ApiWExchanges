package com.example.exampleexchanges.data.remote.repository

import com.example.exampleexchanges.data.ExchangeAPI
import com.example.exampleexchanges.data.remote.dto.Exchange
import com.example.exampleexchanges.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class ExchangeRepository @Inject constructor(
    private val api: ExchangeAPI
) {
    fun getExchanges(): Flow<Resource<List<Exchange>>> = flow {
        try {
            emit(Resource.Loading()) //Indicar que estamos cargando

            val exchanges = api.getExchange() //Descargando las monedas de internet

            emit(Resource.Success(exchanges)) //Se cargo correctamente y pasarle las monedas
        } catch (e: HttpException) {
            emit(Resource.Error(e.message ?: "Error HTTP GENERAL"))//Error general HTTP
        } catch (e: IOException) {
            emit(Resource.Error(e.message ?: "verificar conexion a internet"))//Verificar conexion a internet
        }
    }
}