package com.ahmadov.showmovie.util

import com.ahmadov.showmovie.R

sealed class BottomNavigation(val title: String, val icon: Int, val route: String) {

    object Home : BottomNavigation("Home", R.drawable.ic_home, "home")
    object Favorite : BottomNavigation("Favorite", R.drawable.ic_favorite, "favorite")
    object Profile : BottomNavigation("Profile", R.drawable.ic_profile, "profile")


}