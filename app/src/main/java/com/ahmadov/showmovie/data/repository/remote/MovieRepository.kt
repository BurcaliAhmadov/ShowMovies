package com.ahmadov.showmovie.data.repository.remote

import androidx.paging.PagingData
import com.ahmadov.showmovie.data.model.remote.detail.Credits
import com.ahmadov.showmovie.data.model.remote.detail.MovieDetail
import com.ahmadov.showmovie.data.model.remote.movie.MovieItem
import com.ahmadov.showmovie.data.model.remote.movie.Movies
import com.ahmadov.showmovie.data.model.remote.movie.Trailer
import com.ahmadov.showmovie.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response


interface MovieRepository {

    suspend fun getPopularMovies(page :Int) : Response<Movies>
    suspend fun getRecentMovies(page : Int) : Response<Movies>
    suspend fun getRatedMovies(page :Int) : Response<Movies>
    suspend fun getUpComingMovies(page:Int):Response<Movies>
    suspend fun getSearchMovies(searchMovieName :String ) : Response<Movies>
    suspend fun getArtists(movieId:String) :Response<Credits>
    suspend fun getMovieTrailer(movieId  : String) : Response<Trailer>
    suspend fun getMovieDetails(movieId: String) : Response<MovieDetail>

    suspend fun popularPagingPagingList(): Flow<PagingData<MovieItem>>
    suspend fun nowPlayingPagingList(): Flow<PagingData<MovieItem>>
    suspend fun upcomingPagingList(): Flow<PagingData<MovieItem>>
    suspend fun topRatedPagingList(): Flow<PagingData<MovieItem>>

}