package com.adityagupta.arcompanion.api.interfaces

import com.adityagupta.data.lemma.rootWord
import com.adityagupta.data.wikipedia.PageData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WikipediaAPI {

    @GET("search/page?q=jupiter&limit=1")
    suspend fun getPageDetails(): Response<PageData>
}