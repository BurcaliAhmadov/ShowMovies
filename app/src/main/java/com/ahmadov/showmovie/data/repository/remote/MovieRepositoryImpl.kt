package com.ahmadov.showmovie.data.repository.remote

import com.ahmadov.showmovie.data.model.remote.detail.Credits
import com.ahmadov.showmovie.data.model.remote.detail.MovieDetail
import com.ahmadov.showmovie.data.model.remote.movie.Movies
import com.ahmadov.showmovie.data.model.remote.movie.Trailer
import com.ahmadov.showmovie.data.retrofit.ApiService
import com.ahmadov.showmovie.util.Resource
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(
    private val api:ApiService) :MovieRepository    {
    override suspend fun getPopularMovies(page: Int): Response<Movies> {
        return api.getPopularMovies(page)
    }

    override suspend fun getRecentMovies(page: Int): Response<Movies> {
        return api.getNowPlaying(page)
    }

    override suspend fun getRatedMovies(page: Int): Response<Movies> {
        return api.getTopRated(page)
    }

    override suspend fun getUpComingMovies(page: Int): Response<Movies> {
        return api.getUpComingMovies(page)
    }

    override suspend fun getSearchMovies(searchMovieName: String): Response<Movies> {
        return api.getSearchMovie(searchMovieName)
    }

    override suspend fun getArtists(movieId: String): Response<Credits> {
    return  api.getMovieArtists(movieId)   }

    override suspend fun getMovieTrailer(movieId: String): Response<Trailer> {
        return api.getMovieTrailer(movieId)

    }

    override suspend fun getMovieDetails(movieId: String): Response<MovieDetail> {
        return api.getMovieDetails(movieId)
    }
}