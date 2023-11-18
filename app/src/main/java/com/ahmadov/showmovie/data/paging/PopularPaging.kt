package com.ahmadov.showmovie.data.paging


import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.ahmadov.showmovie.data.model.remote.movie.MovieItem
import com.ahmadov.showmovie.data.retrofit.ApiService
import retrofit2.HttpException
import java.io.IOException

class PopularPaging(private val apiService:ApiService):PagingSource<Int, MovieItem>() {
    override fun getRefreshKey(state: PagingState<Int, MovieItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, MovieItem> {
        val position=params.key ?: 1
        return try{
            val data =apiService.getPopularMovies(
                page = position
            )
            val nextKey=if(data.body()!!.results.isEmpty()){
                null
            }
            else{
                position + (params.loadSize/10)
            }
            val prevKey=if(position==1) null else position - 1
            LoadResult.Page(
                data=data.body()!!.results,
                prevKey=prevKey,
                nextKey=nextKey
            )
        }
        catch (e:IOException){
            return LoadResult.Error(e)
        }
        catch (e:HttpException){
            return LoadResult.Error(e)
        }


    }
}