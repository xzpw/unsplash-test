package com.dm.photo_bank.domain

import com.dm.photo_bank.api.UnsplashApi
import com.dm.photo_bank.core.Resource
import com.dm.photo_bank.data.UnsplashPhoto
import java.lang.Exception

class ListRepo(private val api: UnsplashApi):IListRepo {

    override suspend fun getPhotos(): Resource<List<UnsplashPhoto>> {
        return try {
            Resource.Success(api.getPhotos())
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }

    override suspend fun searchPhotos(query: String): Resource<List<UnsplashPhoto>> {
        return try {
            Resource.Success(api.searchPhotos(query).results)
        } catch (e: Exception) {
            Resource.Error(e)
        }
    }
}