package com.example.exampleexchanges.di

import com.example.exampleexchanges.data.ExchangeAPI
import com.example.exampleexchanges.data.remote.repository.ExchangeRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Provides
    @Singleton
    fun provideExchangeApi(moshi: Moshi): ExchangeAPI {
        return Retrofit.Builder()
            .baseUrl("https://api.coinpaprika.com")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(ExchangeAPI::class.java)
    }

    @Provides
    @Singleton
    fun provideExchangeRepository(ExchangeApi: ExchangeAPI): ExchangeRepository {
        return ExchangeRepository(ExchangeApi)
    }
}