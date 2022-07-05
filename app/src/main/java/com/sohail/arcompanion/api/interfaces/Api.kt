package com.sohail.arcompanion.api.interfaces

import com.sohail.data.entries.Definition
import com.sohail.data.lemma.rootWord
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path


interface Api {

    @GET("lemmas/en/{word}")
    suspend fun getRootWord(@Path("word") word: String): Response<rootWord>

    @GET("entries/en-gb/{rootedWord}")
    suspend fun getDefinition(@Path("rootedWord") word: String): Response<Definition>

}