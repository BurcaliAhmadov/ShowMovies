package com.ahmadov.showmovie.domein.use_case.remote

import com.ahmadov.showmovie.data.model.remote.detail.MovieDetail
import com.ahmadov.showmovie.data.repository.remote.MovieRepository
import com.ahmadov.showmovie.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetMovieDetailsUseCase @Inject constructor(
    private val repo:MovieRepository
) {
    fun getExecuteMovieDetail(movieId:String): Flow<Resource<MovieDetail>> = flow {

        try {
            emit(Resource.Loading(null))
            val response = repo.getMovieDetails(movieId=movieId)
            if(response.isSuccessful){

                response.body()?.let {
                    emit(Resource.Success(it))

                }
            }else{
                emit(Resource.Error("Error Movie Details",null))

            }


        }catch (e:Exception){
            emit(Resource.Error(e.localizedMessage?:"Error",null))
        }
    }

}