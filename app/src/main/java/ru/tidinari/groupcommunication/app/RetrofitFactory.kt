package ru.tidinari.groupcommunication.app

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit

class RetrofitFactory private constructor() {

    companion object {
        val instance = RetrofitFactory()
    }

    private val httpInterceptor: HttpLoggingInterceptor
        get() {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            return interceptor
        }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(httpInterceptor)
        .build()

    private val converterFactory = Json.asConverterFactory("application/json".toMediaType())

    val retrofitClient: Retrofit = Retrofit.Builder()
        .baseUrl("https://google.com/") // TODO: add base url
        .client(okHttpClient)
        .addConverterFactory(converterFactory)
        .build()
}