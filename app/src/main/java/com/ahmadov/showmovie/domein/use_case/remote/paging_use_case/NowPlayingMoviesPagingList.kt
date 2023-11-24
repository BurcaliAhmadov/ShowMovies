package com.ahmadov.showmovie.domein.use_case.remote.paging_use_case

import androidx.paging.PagingData
import com.ahmadov.showmovie.data.model.remote.movie.MovieItem
import com.ahmadov.showmovie.data.repository.remote.MovieRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class NowPlayingMoviesPagingList @Inject constructor(
    private val repository: MovieRepository
) {
    suspend operator fun invoke () : Flow<PagingData<MovieItem>> {
        return repository.nowPlayingPagingList()
    }
}