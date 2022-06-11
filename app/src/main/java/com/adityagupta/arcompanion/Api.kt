package com.adityagupta.arcompanion

import com.adityagupta.data.WordJson
import com.adityagupta.data.WordJsonItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface Api {
    @GET("{word}")
    suspend fun getWordMeaning(@Path("word") word:String): Response<List<WordJsonItem>?>?


}