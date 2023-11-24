package com.ahmadov.showmovie.domein.use_case.remote

import com.ahmadov.showmovie.domein.use_case.remote.paging_use_case.NowPlayingMoviesPagingList
import com.ahmadov.showmovie.domein.use_case.remote.paging_use_case.PopularMoviePagingList
import com.ahmadov.showmovie.domein.use_case.remote.paging_use_case.TopRatedMoviesPagingList
import com.ahmadov.showmovie.domein.use_case.remote.paging_use_case.UpComingMoviesPagingList

data class UseCases(
    val getArtistsUseCase: GetArtistsUseCase,
    val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    val getMovieTrailerUseCase: GetMovieTrailerUseCase,
    val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    val getPopularMovieUseCase: GetPopularMovieUseCase,
    val getUpComingMoviesUseCase: GetUpComingMoviesUseCase,
    val getSearchMovieUseCase: GetSearchMovieUseCase,
    val getTopRatedMovieUseCase: GetTopRatedMovieUseCase,
    //paging
    val getPopularMoviesPagingUseCase : PopularMoviePagingList,
    val getTopRatedMoviesPagingUseCase : TopRatedMoviesPagingList,
    val getUpComingMoviesPagingUseCAse : UpComingMoviesPagingList,
    val getNowPlayingMoviesPagingUseCase : NowPlayingMoviesPagingList


)
