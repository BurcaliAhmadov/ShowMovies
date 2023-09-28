package com.ahmadov.showmovie.domein.use_case.remote

import com.ahmadov.showmovie.data.model.remote.movie.Movies
import com.ahmadov.showmovie.data.repository.remote.MovieRepository
import com.ahmadov.showmovie.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetNowPlayingMoviesUseCase @Inject constructor(
    private val repo : MovieRepository
){



    fun getExecuteRecentMovies(page: Int) : Flow<Resource<Movies>> = flow {
        try{
            emit(Resource.Loading(null))
            val response=repo.getRecentMovies(page=page)
            if(response.isSuccessful){
                response.body()?.let {
                    emit(Resource.Success(it))
                }
            }else{
                emit(Resource.Error("Error Recent Movies",null))
            }

        }catch (e:Exception){
            emit(Resource.Error(e.localizedMessage?:"Error"))
        }

    }
}