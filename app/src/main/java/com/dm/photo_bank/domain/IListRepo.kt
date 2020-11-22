package com.dm.photo_bank.domain

import com.dm.photo_bank.core.Resource
import com.dm.photo_bank.data.UnsplashPhoto

interface IListRepo {
    suspend fun getPhotos(): Resource<List<UnsplashPhoto>>
    suspend fun searchPhotos(query: String): Resource<List<UnsplashPhoto>>
}