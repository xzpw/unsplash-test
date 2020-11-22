package com.dm.photo_bank.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UnsplashPhoto(
    val id: String,
    val urls: UnsplashPhotoUrls,
) : Parcelable {

    @Parcelize
    data class UnsplashPhotoUrls(
        val thumb: String,
        val small: String,
        val regular: String,
        val raw: String,
        val full: String
    ) : Parcelable
}