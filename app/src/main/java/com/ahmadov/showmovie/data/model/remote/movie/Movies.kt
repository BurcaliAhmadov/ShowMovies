package com.ahmadov.showmovie.data.model.remote.movie

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movies(
    val page: Int,
    val results: List<MovieItem>,
    val total_pages: Int,
    val total_results: Int
) :Parcelable