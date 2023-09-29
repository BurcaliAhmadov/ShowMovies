package com.ahmadov.showmovie.presentation.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.modifier.modifierLocalProvider
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.ahmadov.showmovie.R
import com.ahmadov.showmovie.presentation.Screen
import com.ahmadov.showmovie.presentation.dashboard.components.MovieItemCard
import com.ahmadov.showmovie.util.HomeBottomNavigation

@Composable
fun DashBoardScreen(navController: NavController,viewModel: DashboardViewModel = hiltViewModel()) {



    @Composable
    fun BottomNavigationBar(navController: NavController) {

        val navigationItems = listOf(
            HomeBottomNavigation.Home,
            HomeBottomNavigation.Favorite

        )
        BottomNavigation (
            backgroundColor = Color.Black,
            contentColor = Color.White
        ){
            val navBackStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = navBackStackEntry?.destination?.route
            navigationItems.forEach{
                BottomNavigationItem(
                    selected = it.route == currentRoute,
                    onClick = {},
                    icon = {
                        Icon(
                            painter = painterResource(id = it.icon),
                            contentDescription = it.title
                        )
                    })
            }
        }
    }

    @Composable
    fun Title(
        navController: NavController,
        visibitly:Boolean,
        moviesType: MoviesType

    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .padding(15.dp)
                .fillMaxWidth()


        ) {
            Text(
                text = moviesType.value,
                style = MaterialTheme.typography.h6
            )
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.clickable {
                    navController.navigate(Screen.ViewAll.route + "?moviesType=${moviesType.value}")
                }
            ){

                Text(
                    text ="View All",
                    style = MaterialTheme.typography.body1,
                    color = Color.Gray,
                    modifier = Modifier.padding(end = 10.dp)
                )
                Icon(
                    painter = painterResource(id = R.drawable.double_arrow_right_14214),
                    contentDescription = "arrow_forward",
                    Modifier.size(10.dp),
                    tint = Color.Gray
                )

            }


        }

    }

}

@Composable
fun PopularList(viewModel: DashboardViewModel,navController: NavController) {
    LazyRow(Modifier.padding(top = 10.dp)){
        items(
            items=viewModel.popularMovieList,
            key = {item ->
                item.id.toString()
            }
        ){movieItem->
            MovieItemCard(
                item = movieItem,
                modifier = Modifier.width(140.dp), 
                navController = navController)
        }
    }
}
@Composable
fun NowPlayingList(viewModel: DashboardViewModel,navController: NavController){
    LazyRow(
        Modifier.padding(top=10.dp)
    ){
        items(
            items=viewModel.nowPlayingMovieList,
            key = {item ->
                item.id.toString()
            }
        ){movieItem ->
            MovieItemCard(
                item = movieItem,
                modifier = Modifier.width(140.dp),
                navController = navController
            )
        }
    }
    
}

@Composable
fun TopRatedList(viewModel: DashboardViewModel,navController: NavController) {
    LazyRow(
        Modifier.padding(top=10.dp)
    ){
        items(
            items = viewModel.topRatedMovieList,
            key ={item ->
                item.id.toString()
            }
        ){movieItem ->
            MovieItemCard(
                item = movieItem,
                modifier = Modifier.width(140.dp),
                navController = navController
            )
        }
    }
}

@Composable
fun UpComingList(viewModel: DashboardViewModel,navController: NavController) {
    LazyRow(Modifier.padding(top=10.dp)){
        items(
            items=viewModel.upcomingMovieList,
            key = {item ->
                item.id.toString()
            }
        ){movieItem ->
            MovieItemCard(
                item = movieItem ,
                modifier =Modifier.width(140.dp) ,
                navController =navController )

        }
    }

}
