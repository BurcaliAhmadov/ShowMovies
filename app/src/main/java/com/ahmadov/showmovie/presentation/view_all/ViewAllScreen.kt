package com.ahmadov.showmovie.presentation.view_all

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.ahmadov.showmovie.data.model.remote.movie.MovieItem
import com.ahmadov.showmovie.presentation.dashboard.MoviesType
import com.ahmadov.showmovie.presentation.dashboard.components.MovieItemCard
import com.ahmadov.showmovie.presentation.view_all.components.PaginationProgress

import com.ahmadov.showmovie.presentation.view_all.components.ToolBar
import com.ahmadov.showmovie.presentation.view_all.components.handlePagingResult

@Composable
fun ViewAllScreen(
    navController: NavController,
    moviesType: String,
    viewModel: ViewAllVieModel= hiltViewModel()

) {
    println("moviesTypeViewAll: $moviesType")
    Scaffold(topBar = {
        ToolBar(title = moviesType, onBack = {
            navController.popBackStack()
        })
    })
    { paddingValues ->
        Box(
            modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding())
        ) {
            val movieItems= selectList(moviesType = moviesType, viewModel = viewModel)
            val modifier=
                if (movieItems.loadState.append == LoadState.Loading)
                    Modifier.padding(bottom = 80.dp)
                else Modifier.fillMaxSize()
            LazyVerticalGrid(modifier = modifier, columns = GridCells.Adaptive(128.dp), content = {
                items(movieItems.itemCount){i ->
                    Row {
                        movieItems[i]?.let {
                            MovieItemCard(
                                item = it,
                                modifier = Modifier.fillMaxSize(),
                                navController = navController
                            )
                        }

                    }

                }

            })
            if (movieItems.loadState.append == LoadState.Loading)
                PaginationProgress()
            else {
                handlePagingResult(movieItems)
            }


        }

    }

}

@Composable
fun selectList(moviesType: String,viewModel:ViewAllVieModel):LazyPagingItems<MovieItem> {
    return when(moviesType){
        MoviesType.POPULAR.value -> viewModel.popularMoviesPagingItems.collectAsLazyPagingItems()
        MoviesType.NOW_PLAYING.value -> viewModel.nowPlayingMoviesPagingItems.collectAsLazyPagingItems()
        MoviesType.UPCOMING.value -> viewModel.upcomingMoviesPagingItems.collectAsLazyPagingItems()
        else -> {
            viewModel.topRatedMoviesPagingItems.collectAsLazyPagingItems()
        }


    }



}