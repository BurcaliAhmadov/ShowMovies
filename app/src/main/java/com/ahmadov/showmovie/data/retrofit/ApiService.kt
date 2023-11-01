package com.ahmadov.showmovie.data.retrofit

import com.ahmadov.showmovie.data.model.remote.detail.Credits
import com.ahmadov.showmovie.data.model.remote.detail.MovieDetail
import com.ahmadov.showmovie.data.model.remote.movie.Movies
import com.ahmadov.showmovie.data.model.remote.movie.Trailer
import com.ahmadov.showmovie.util.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page :Int,
        @Query("api_key") apiKey : String = API_KEY
    ):Response<Movies>

    @GET("movie/now_playing")
    suspend fun getNowPlaying(
        @Query("page") page : Int ,
        @Query("api_key") apiKey :String= API_KEY
    ):Response<Movies>

    @GET("movie/upcoming")
    suspend fun getUpComingMovies(
        @Query("page") page : Int ,
        @Query("api_key") apiKey :String= API_KEY
    ):Response<Movies>


    @GET("movie/top_rated")
    suspend fun getTopRated(
        @Query("page") page:Int,
        @Query("api_key") apiKey : String= API_KEY
    ):Response<Movies>

    @GET("movie/{movie_id}/videos")
    suspend fun getMovieTrailer(
        @Path("movie_id") movieId :String,
        @Query("api_key") apiKey :String = API_KEY
    ):Response<Trailer>


    @GET("movie/{movie_id}/credits")
    suspend fun getMovieArtists(
        @Path("movie_id") movieId :String,
        @Query("api_key") apiKey :String= API_KEY
    ):Response<Credits>

    @GET("search/movie")
    suspend fun  getSearchMovie(
        @Query("query") search :String,
        @Query("api_key") apiKey :String = API_KEY
    ) :Response<Movies>


    @GET("movie/{movieId}")
    suspend fun getMovieDetails(
        @Path("movieId") movieId :String,
        @Query("api_key") apiKey :String= API_KEY

    ):Response<MovieDetail>


}