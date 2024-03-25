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

@Module // Аннотация модуля Dagger, указывает, что этот объект является Dagger модулем.
@InstallIn(SingletonComponent::class) // Указывает, что зависимости из этого модуля будут жить в Singleton области видимости.
object NetworkModule {
    // Метод предоставляет перехватчик для OkHttp клиента.
    @Provides // Указывает Dagger'у, что этот метод предоставляет зависимость.
    @Singleton // Указывает, что предоставляемая зависимость будет существовать в единственном экземпляре.
    fun getInterceptor(): Interceptor {
        return Interceptor {
            val request = it.request().newBuilder()
            request.addHeader(AUTHORIZATION, getBearer()) // Добавляет заголовок авторизации к каждому запросу.
            val actualRequest = request.build()
            it.proceed(actualRequest) // Продолжает выполнение запроса с модифицированным заголовком.
        }
    }

    // Метод предоставляет клиент OkHttpClient, сконфигурированный с перехватчиком.
    @Provides
    @Singleton
    fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addNetworkInterceptor(
                HttpLoggingInterceptor().setLevel(
                    HttpLoggingInterceptor.Level.NONE // Уровень логирования установлен в NONE для выпуска.
                )
            )
            .addInterceptor(interceptor) // Добавляет наш персонализированный перехватчик.
            .build()
    }

    // Метод предоставляет сервис API для взаимодействия с сетью.
    @Provides
    @Singleton
    fun provideMovieApiService(client: OkHttpClient): MovieApiService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL) // Базовый URL для сетевых запросов.
            .addConverterFactory(GsonConverterFactory.create()) // Конвертер для преобразования JSON в объекты.
            .client(client) // Использует настроенный клиент OkHttpClient.
            .build()
            .create(MovieApiService::class.java) // Создает реализацию MovieApiService из интерфейса.
    }
}
