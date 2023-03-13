package com.kakao.data

import com.kakao.data.model.SearchData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface ApiService {

    @GET("search/image")
    suspend fun search(
        @Header("Authorization") key: String,
        @Query("query") query: String,
        @Query("page") page: Int,
        @Query("size") size: Int = 30
    ) : Response<SearchData>

}