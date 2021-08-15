package com.iyaliyul.movieeyalapps.data.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieResponse(
    val page: Int,
    val results: List<MovieItem>,
    val total_pages: Int,
    val total_results: Int
): Parcelable