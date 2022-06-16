package com.adityagupta.arcompanion.api.interfaces

import com.adityagupta.data.lemma.rootWord
import com.adityagupta.data.wikipedia.PageData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WikipediaAPI {


    @GET("search/page")
    suspend fun getPageDetails(@Query("q") word: String,
                                @Query("limit") limit: Int): Response<PageData>
}