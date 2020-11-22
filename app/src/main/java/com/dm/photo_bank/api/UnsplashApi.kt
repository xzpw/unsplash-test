package com.dm.photo_bank.api

import com.dm.photo_bank.data.SearchResult
import com.dm.photo_bank.data.UnsplashPhoto
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface UnsplashApi {

    @Headers("Accept-Version: v1", "Authorization: Client-ID ${Config.CLIENT_ID}")
    @GET("photos")
    suspend fun getPhotos(): List<UnsplashPhoto>


    @Headers("Accept-Version: v1", "Authorization: Client-ID ${Config.CLIENT_ID}")
    @GET("search/photos")
    suspend fun searchPhotos(
        @Query("query") query: String,
    ): SearchResult
}

