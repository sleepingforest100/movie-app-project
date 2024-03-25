package com.salt.apps.moov.di

import com.salt.apps.moov.data.source.remote.network.MovieApiService
import com.salt.apps.moov.utilities.Constants.AUTHORIZATION
import com.salt.apps.moov.utilities.Constants.BASE_URL
import com.salt.apps.moov.utilities.Constants.getBearer
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {
    @Provides
    @Singleton
    fun getInterceptor(): Interceptor {
        return Interceptor {
            val request = it.request().newBuilder()
            request.addHeader(AUTHORIZATION, getBearer())
            val actualRequest = request.build()
            it.proceed(actualRequest)
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(
                HttpLoggingInterceptor().setLevel(
                    HttpLoggingInterceptor.Level.NONE
                )
            )
            .addInterceptor(interceptor)
            .build()
    }

    @Provides
    @Singleton
    fun provideMovieApiService(client: OkHttpClient): MovieApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
            .create(MovieApiService::class.java)
    }
}