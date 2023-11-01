package com.ahmadov.showmovie.presentation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.ahmadov.showmovie.presentation.dashboard.DashBoardScreen
import com.ahmadov.showmovie.presentation.movie_details.MovieDetailsScreen
import com.ahmadov.showmovie.presentation.movie_details.components.YoutubePlayerScreen

@ExperimentalAnimationApi
@Composable
fun Navigation() {
    val navController= rememberNavController()
    NavHost(navController = navController, startDestination = Screen.Dashboard.route){


        //DashboardScreen
        composable(route = Screen.Dashboard.route){
            DashBoardScreen(navController = navController)
        }


        //MovieDetailsScreen

        composable(
            route=Screen.MovieDetailsScreen.route + "?movieId={movieId}&moviesTitle={moviesTitle}",
            arguments = listOf(
                navArgument(name="movieId"){
                    type= NavType.StringType
                    defaultValue= ""
                },
                navArgument(name= "moviesTitle"){
                    type= NavType.StringType
                    defaultValue = ""
                }

            )


        ){
            val moviesTitle = it.arguments?.getString("moviesTitle") ?: ""
            val movieId =it.arguments?.getString("movieId") ?: ""
            MovieDetailsScreen(navController = navController, title = moviesTitle)


        }

        //Youtube Player Screen
        composable(
            route=Screen.YoutubePlayerScreen.route + "youtubeCode={youtubeCode}",
            arguments = listOf(
                navArgument(name="youtubeCode"){
                    type= NavType.StringType
                    defaultValue=""
                }
            )

        ){
            val youtubeCode=it.arguments?.getString("youtubeCode") ?: ""
            YoutubePlayerScreen(navController = navController, youtubeCode = youtubeCode)

        }

    }



}