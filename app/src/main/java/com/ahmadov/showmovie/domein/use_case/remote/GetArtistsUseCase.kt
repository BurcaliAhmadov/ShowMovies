package com.ahmadov.showmovie.domein.use_case.remote

import com.ahmadov.showmovie.data.model.remote.detail.Credits
import com.ahmadov.showmovie.data.model.remote.movie.Movies
import com.ahmadov.showmovie.data.repository.remote.MovieRepository
import com.ahmadov.showmovie.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetArtistsUseCase @Inject constructor(
    private val repo: MovieRepository

) {
    fun  getExecuteArtists(movieId:String) : Flow<Resource<Credits>> = flow {
        try{
            emit(Resource.Loading(null))
            val response=repo.getArtists(movieId=movieId)
            if(response.isSuccessful){
                println("artist geldi xos geldi")
                response.body()?.let {
                    emit(Resource.Success(it))
                }
            }else{
                emit(Resource.Error("Error Artist",null))
            }

        }catch (e:Exception){
            emit(Resource.Error(e.localizedMessage?:"Error",null))
        }

    }


}