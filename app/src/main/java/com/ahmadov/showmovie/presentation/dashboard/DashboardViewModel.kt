package com.ahmadov.showmovie.presentation.dashboard

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmadov.showmovie.data.model.remote.movie.MovieItem
import com.ahmadov.showmovie.domein.use_case.remote.UseCases
import com.ahmadov.showmovie.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor(
    private val useCases:UseCases
):ViewModel() {

    private var _popularMovieList = mutableStateListOf<MovieItem>()
    val popularMovieList = _popularMovieList

    private var _nowPlayingMovieList = mutableStateListOf<MovieItem>()
    val nowPlayingMovieList: List<MovieItem> = _nowPlayingMovieList

    private var _upcomingMovieList = mutableStateListOf<MovieItem>()
    val upcomingMovieList: List<MovieItem> = _upcomingMovieList

    private var _topRatedMovieList = mutableStateListOf<MovieItem>()
    val topRatedMovieList: List<MovieItem> = _topRatedMovieList

    private var _error = mutableStateOf(false)
    val error: State<Boolean> = _error

    private var _isLoading = mutableStateMapOf<Int, Boolean>()
    val isLoading: Map<Int, Boolean> = _isLoading


    init {
        viewModelScope.launch {

            useCases.getPopularMovieUseCase.getExecutePopularMovies(1).collect{
                when(it){
                    is Resource.Success -> {
                        _popularMovieList.clear()
                        it.data?.results?.forEach {movieItem ->
                            _popularMovieList.add(movieItem)
                        }
                        _isLoading[0] = false
                    }
                    is Resource.Error -> {
                        _error.value=true
                        _isLoading[0] =false
                    }
                    is Resource.Loading -> {
                        _isLoading[0] = true
                    }
                }
            }
            useCases.getNowPlayingMoviesUseCase.getExecuteRecentMovies(1).collect{
                when(it){
                    is Resource.Success -> {
                        _nowPlayingMovieList.clear()
                        it.data?.results?.forEach { movieItem ->
                            _nowPlayingMovieList.add(movieItem)
                            _isLoading[1]=false
                        }
                    }
                    is Resource.Error -> {
                        _error.value = true
                        _isLoading[1] = false
                    }
                    is Resource.Loading -> {
                        _isLoading[1] = true
                    }
                }

            }

            useCases.getUpComingMoviesUseCase.getExecuteTopRatedMovie(1).collect{
                when (it) {

                    is Resource.Success -> {

                        _upcomingMovieList.clear()
                        it.data?.results?.forEach { movieItem ->
                            _upcomingMovieList.add(movieItem)
                        }
                        _isLoading[2] = false


                    }
                    is Resource.Error -> {
                        _error.value = true
                        _isLoading[2] = false
                    }

                    is Resource.Loading -> {
                        _isLoading[2] = true
                    }
                }
            }

            useCases.getTopRatedMovieUseCase.getExecuteTopRatedMovie(1).collect{
                when (it) {

                    is Resource.Success -> {

                        _topRatedMovieList.clear()
                        it.data?.results?.forEach { movieItem ->
                            _topRatedMovieList.add(movieItem)
                        }
                        _isLoading[3] = false


                    }
                    is Resource.Error -> {
                        _error.value = true
                        _isLoading[3] = false
                    }

                    is Resource.Loading -> {
                        _isLoading[3] = true
                    }
                }
            }
        }
    }
}