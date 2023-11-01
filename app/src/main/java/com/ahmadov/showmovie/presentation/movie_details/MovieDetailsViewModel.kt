package com.ahmadov.showmovie.presentation.movie_details

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ahmadov.showmovie.data.model.remote.detail.Credits
import com.ahmadov.showmovie.data.model.remote.detail.MovieDetail
import com.ahmadov.showmovie.data.model.remote.movie.Trailer
import com.ahmadov.showmovie.domein.use_case.remote.UseCases
import com.ahmadov.showmovie.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectIndexed
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    useCase : UseCases,savedStateHandle: SavedStateHandle
) :ViewModel()
{
    private val _movieDetails: MutableState<MovieDetail> = mutableStateOf(MovieDetail())
    val movieDetails : State<MovieDetail> = _movieDetails

    private val _movieCredits :MutableState<Credits> = mutableStateOf(Credits(emptyList(), emptyList(),0))
    val movieCredits : State<Credits> = _movieCredits

    private val _trailer : MutableState<Trailer> = mutableStateOf(Trailer(0, emptyList()))
    val trailer : State<Trailer> = _trailer

    private val _apiError  = mutableStateOf(false)
    val apiError : State<Boolean> = _apiError

    private val _isLoading = mutableStateMapOf<Int,Boolean>()
    val isLoading :Map<Int,Boolean> = _isLoading

    init {
        savedStateHandle.get<String>("movieId")?.let { movieId ->
            println(movieId)
            if(movieId.isNotEmpty()){
                viewModelScope.launch {
                    useCase.getMovieDetailsUseCase.getExecuteMovieDetail(movieId).collect{
                        when(it){

                            is Resource.Success -> {
                                it.data?.let {movieDetail ->
                                    _movieDetails.value=movieDetail
                                }
                                _isLoading[0]=false
                            }
                            is Resource.Error ->{
                                _apiError.value=true
                                _isLoading[0] = false
                            }
                            is Resource.Loading -> {
                                _isLoading[0] =true
                            }
                        }

                    }
                    useCase.getArtistsUseCase.getExecuteArtists(movieId).collect{ it ->
                        when(it){

                            is Resource.Success -> {
                                it.data?.let {credits ->
                                    _movieCredits.value=credits

                                }
                                _isLoading[1]=false
                            }
                            is Resource.Error ->{
                                _apiError.value=true
                                _isLoading[1]=false

                            }
                            is Resource.Loading -> {
                                _isLoading[1]=true
                            }
                        }
                    }
                    useCase.getMovieTrailerUseCase.getExecuteMovieTrailer(movieId).collect{
                        when(it){
                            is Resource.Success ->{
                                it.data?.let {trailer ->
                                    _trailer.value=trailer

                                }
                                _isLoading[2]=false
                            }
                            is Resource.Error -> {
                                _apiError.value=true
                                _isLoading[2]=false
                            }
                            is Resource.Loading -> {
                                _isLoading[2]=true
                            }
                        }
                    }
                }
            }


        }







    }







}