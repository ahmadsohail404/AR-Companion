package com.adityagupta.arcompanion.api.helpers

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object WikipediaHelper {

    val baseUrl = "https://en.wikipedia.org/w/rest.php/v1/"


    fun getInstance(): Retrofit {
        return Retrofit.Builder().baseUrl(baseUrl)
            // we need to add converter factory to
            // convert JSON object to Java object
            .addConverterFactory(GsonConverterFactory.create())

            .build()
    }


}