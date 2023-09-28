package com.ahmadov.showmovie.data.di

import com.ahmadov.showmovie.data.repository.remote.MovieRepository
import com.ahmadov.showmovie.data.repository.remote.MovieRepositoryImpl
import com.ahmadov.showmovie.data.retrofit.ApiService
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



}