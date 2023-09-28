package com.ahmadov.showmovie.data.model.remote.detail

data class Credits(
    val cast: List<Cast>,
    val crew: List<Crew>,
    val id: Int
)