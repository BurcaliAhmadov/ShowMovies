package com.ahmadov.showmovie.presentation.search_movie

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.ahmadov.showmovie.presentation.dashboard.components.ErrorView
import com.ahmadov.showmovie.presentation.dashboard.components.IsLoading
import com.ahmadov.showmovie.presentation.movie_details.components.Searchbar
import com.ahmadov.showmovie.presentation.search_movie.components.SearchEmpty
import com.ahmadov.showmovie.presentation.search_movie.components.SearchMovieItemCard


@Composable
fun SearchPageScreen(
    navController: NavController,
    viewModel: SearchPageViewModel= hiltViewModel()
) {
    Column(modifier = Modifier.padding(20.dp)) {
        Card(
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(30.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Searchbar(
                    onSearch = { state ->
                        state.value.text.let { query ->
                            if (query.isNotBlank()) {
                                viewModel.searchMovie(query)
                            }
                        }
                        viewModel.searchMovie(state.value.text)
                    },
                    onCancel = { viewModel.clearSearch() }

                )
            }
        }
        Box(modifier = Modifier.padding(top = 10.dp)) {
            SearchItemList(viewModel, navController)
            IsLoading(isLoading = viewModel.isLoading.value)
            ErrorView(viewModel.apiError.value)
            SearchEmpty(viewModel.listEmpty.value)
        }


        }
    }

@Composable
fun SearchItemList(viewModel: SearchPageViewModel, navController: NavController){
    LazyColumn(){
        items(
            items =  viewModel.searchMoviePagingItems,
            key = { item ->
                item.id.toString()
            }
        ){ item ->
            SearchMovieItemCard(navController,item)
        }
    }
}

