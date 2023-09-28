package com.ahmadov.showmovie.domein.use_case.remote

import com.ahmadov.showmovie.data.model.remote.movie.Movies
import com.ahmadov.showmovie.data.repository.remote.MovieRepository
import com.ahmadov.showmovie.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetPopularMovieUseCase @Inject constructor(
    private val repo:MovieRepository
) {
    fun getExecutePopularMovies(page:Int): Flow<Resource<Movies>> = flow {
        try {
            emit(Resource.Loading(null))

            val response=repo.getPopularMovies(page=page)
            if(response.isSuccessful){
                response.body()?.let {
                    emit(Resource.Success(it))
                }

            }else{
                emit(Resource.Error("Error Popular Movies",null))
            }

        }catch (e:Exception){
            emit((Resource.Error(e.localizedMessage ?:"Error")))
        }

    }







}