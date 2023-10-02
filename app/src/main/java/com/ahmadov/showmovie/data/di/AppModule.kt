package com.ahmadov.showmovie.data.di

import com.ahmadov.showmovie.data.repository.remote.MovieRepository
import com.ahmadov.showmovie.data.repository.remote.MovieRepositoryImpl
import com.ahmadov.showmovie.data.retrofit.ApiService
import com.ahmadov.showmovie.domein.use_case.remote.GetArtistsUseCase
import com.ahmadov.showmovie.domein.use_case.remote.GetMovieDetailsUseCase
import com.ahmadov.showmovie.domein.use_case.remote.GetMovieTrailerUseCase
import com.ahmadov.showmovie.domein.use_case.remote.GetNowPlayingMoviesUseCase
import com.ahmadov.showmovie.domein.use_case.remote.GetPopularMovieUseCase
import com.ahmadov.showmovie.domein.use_case.remote.GetSearchMovieUseCase
import com.ahmadov.showmovie.domein.use_case.remote.GetTopRatedMovieUseCase
import com.ahmadov.showmovie.domein.use_case.remote.GetUpComingMoviesUseCase
import com.ahmadov.showmovie.domein.use_case.remote.UseCases
import com.ahmadov.showmovie.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideApiService ():ApiService{
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(ApiService::class.java)
    }


    @Provides
    @Singleton
    fun provideMovieRepository(api:ApiService) :MovieRepository {
        return MovieRepositoryImpl(api=api)
    }
    @Provides
    @Singleton
    fun useCase(repo:MovieRepository):UseCases=UseCases(
        GetArtistsUseCase(repo),
        GetMovieDetailsUseCase(repo),
        GetMovieTrailerUseCase(repo),
        GetNowPlayingMoviesUseCase(repo),
        GetPopularMovieUseCase(repo),
        GetUpComingMoviesUseCase(repo),
        GetSearchMovieUseCase(repo),
        GetTopRatedMovieUseCase(repo)
    )



}