package com.ahmadov.showmovie.data.model.remote.detail

import com.google.gson.annotations.SerializedName

data class Credits(
    @field:SerializedName("cast")
    val cast: List<Cast?>? = null,
    @field:SerializedName("crew")
    val crew: List<Crew?>? = null,

    @field:SerializedName("id")
    val id: Int? = null


)