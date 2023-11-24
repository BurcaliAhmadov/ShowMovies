package com.ahmadov.showmovie.data.repository.remote

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ahmadov.showmovie.data.model.remote.detail.Credits
import com.ahmadov.showmovie.data.model.remote.detail.MovieDetail
import com.ahmadov.showmovie.data.model.remote.movie.MovieItem
import com.ahmadov.showmovie.data.model.remote.movie.Movies
import com.ahmadov.showmovie.data.model.remote.movie.Trailer
import com.ahmadov.showmovie.data.paging.NowPlayingPaging
import com.ahmadov.showmovie.data.paging.PopularPaging
import com.ahmadov.showmovie.data.paging.TopRatedPaging
import com.ahmadov.showmovie.data.paging.UpComingPaging
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

    //paging
    override suspend fun popularPagingPagingList(): Flow<PagingData<MovieItem>> = Pager(
        config = PagingConfig(
            pageSize = 10
        ),
        pagingSourceFactory = {PopularPaging(api)}

    ).flow


    override suspend fun nowPlayingPagingList(): Flow<PagingData<MovieItem>> = Pager(
        config = PagingConfig(
            pageSize = 10,
        ),
        pagingSourceFactory = { NowPlayingPaging(api) }
    ).flow

    override suspend fun upcomingPagingList(): Flow<PagingData<MovieItem>> = Pager(
        config = PagingConfig(
            pageSize = 10,
        ),
        pagingSourceFactory = { UpComingPaging(api) }
    ).flow

    override suspend fun topRatedPagingList(): Flow<PagingData<MovieItem>> = Pager(
        config = PagingConfig(
            pageSize = 10,
        ),
        pagingSourceFactory = { TopRatedPaging(api) }
    ).flow


}