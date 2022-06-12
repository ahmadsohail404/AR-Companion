package com.adityagupta.arcompanion

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    val baseUrl = "https://od-api.oxforddictionaries.com/api/v2/"
    val appId = "adc85d41"
    val appKey = "a17dedc41e84427519d4e78041ec6272"

    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            .client(getHttpClient())
            // we need to add converter factory to
            // convert JSON object to Java object
            .addConverterFactory(GsonConverterFactory.create())

            .build()
    }

    fun getHttpClient(): OkHttpClient {

        return  OkHttpClient.Builder().apply {
            addInterceptor(
                Interceptor { chain ->
                    val builder = chain.request().newBuilder()
                    builder.header("Accept", "application/json")
                    builder.header("app_id", appId)
                    builder.header("app_key", appKey)
                    return@Interceptor chain.proceed(builder.build())
                }
            )
        }.build()
    }
}