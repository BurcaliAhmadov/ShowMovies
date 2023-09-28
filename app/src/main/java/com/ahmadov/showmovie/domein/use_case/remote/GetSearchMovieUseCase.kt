package com.ahmadov.showmovie.domein.use_case.remote

import com.ahmadov.showmovie.data.model.remote.movie.Movies
import com.ahmadov.showmovie.data.repository.remote.MovieRepository
import com.ahmadov.showmovie.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetSearchMovieUseCase @Inject constructor(
    private val repo:MovieRepository
) {
    fun getExecuteSearchMovie(movieName:String) : Flow<Resource<Movies>> = flow {
        try{
            emit(Resource.Loading(null))

            val response=repo.getSearchMovies(searchMovieName = movieName)
            if (response.isSuccessful){
                response.body()?.let {
                    emit(Resource.Success(it))
                }
            }else{
                emit(Resource.Error("Error Search Movies",null))
            }
        }catch (e:Exception){
            emit(Resource.Error(e.localizedMessage?:"Error"))
        }

    }

}