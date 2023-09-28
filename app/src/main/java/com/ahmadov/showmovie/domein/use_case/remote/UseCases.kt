package com.ahmadov.showmovie.domein.use_case.remote

data class UseCases(
    val getArtistsUseCase: GetArtistsUseCase,
    val getMovieDetailsUseCase: GetMovieDetailsUseCase,
    val getMovieTrailerUseCase: GetMovieTrailerUseCase,
    val getNowPlayingMoviesUseCase: GetNowPlayingMoviesUseCase,
    val getPopularMovieUseCase: GetPopularMovieUseCase,
    val getUpComingMoviesUseCase: GetUpComingMoviesUseCase,
    val getSearchMovieUseCase: GetSearchMovieUseCase,
    val getTopRatedMovieUseCase: GetTopRatedMovieUseCase

)
