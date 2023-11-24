package com.ahmadov.showmovie.presentation.search_movie

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmadov.showmovie.data.model.remote.movie.MovieItem
import com.ahmadov.showmovie.domein.use_case.remote.UseCases
import com.ahmadov.showmovie.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SearchPageViewModel @Inject constructor(
    private val useCase : UseCases
) :ViewModel() {
     private var _searchMoviePagingItems = mutableStateListOf<MovieItem>()
     val searchMoviePagingItems : List<MovieItem> = _searchMoviePagingItems

    private var _apiError = mutableStateOf(false)
    val apiError : State<Boolean> = _apiError

    private var _isLoading = mutableStateOf<Boolean>(false)
    val isLoading: State<Boolean> = _isLoading

    private val _listEmpty = mutableStateOf(false)
    val listEmpty: State<Boolean> = _listEmpty


    fun searchMovie(query:String){

        viewModelScope.launch {
            useCase.getSearchMovieUseCase.getExecuteSearchMovie(query).collect{
                when(it){
                    is Resource.Success -> {
                        _searchMoviePagingItems.clear()
                        val results = it.data?.results
                        if(results.isNullOrEmpty()){
                            _listEmpty.value=true
                        }
                        else{
                            _listEmpty.value = false
                            _searchMoviePagingItems.addAll(results)
                        }
                        _isLoading.value = false

                    }
                    is Resource.Error -> {
                        _apiError.value=true
                        _isLoading.value =false
                        _listEmpty.value = false
                    }
                    is Resource.Loading -> {
                        _isLoading.value = true
                        _listEmpty.value = false
                    }


                }

            }

        }


    }
    fun clearSearch() {
        _searchMoviePagingItems.clear()
        _listEmpty.value = false
    }


}