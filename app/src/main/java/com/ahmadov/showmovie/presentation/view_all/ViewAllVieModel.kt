package com.ahmadov.showmovie.presentation.view_all

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.ahmadov.showmovie.data.model.remote.movie.MovieItem
import com.ahmadov.showmovie.domein.use_case.remote.UseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class ViewAllVieModel @Inject constructor(
    private val useCases: UseCases
) :ViewModel(){
    var popularMoviesPagingItems : Flow<PagingData<MovieItem>> = emptyFlow()
    var upcomingMoviesPagingItems : Flow<PagingData<MovieItem>> = emptyFlow()
    var nowPlayingMoviesPagingItems : Flow<PagingData<MovieItem>> = emptyFlow()
    var topRatedMoviesPagingItems : Flow<PagingData<MovieItem>> = emptyFlow()

    init {
        viewModelScope.launch {
            popularMoviesPagingItems=useCases.getPopularMoviesPagingUseCase.invoke()
            upcomingMoviesPagingItems = useCases.getUpComingMoviesPagingUseCAse.invoke()
            nowPlayingMoviesPagingItems = useCases.getNowPlayingMoviesPagingUseCase.invoke()
            topRatedMoviesPagingItems = useCases.getTopRatedMoviesPagingUseCase.invoke()


        }


    }


}