package com.ahmadov.showmovie.presentation

sealed class Screen( val route:String) {
    object Dashboard : Screen("dashboard_screen")
    object ViewAll : Screen("view_all_screen")
    object MovieDetailsScreen : Screen("movie_details_screen")
    object YoutubePlayerScreen : Screen("youtube_player_screen")
    object SearchScreen : Screen("search_screen")
}