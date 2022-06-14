package com.adityagupta.arcompanion.api.interfaces

import com.adityagupta.data.WordJson
import com.adityagupta.data.WordJsonItem
import com.adityagupta.data.entries.Definition
import com.adityagupta.data.lemma.rootWord
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path


interface Api {

    @GET("lemmas/en/{word}")
    suspend fun getRootWord(@Path("word") word: String): Response<rootWord>

    @GET("entries/en-gb/{rootedWord}")
    suspend fun getDefinition(@Path("rootedWord") word: String): Response<Definition>

}