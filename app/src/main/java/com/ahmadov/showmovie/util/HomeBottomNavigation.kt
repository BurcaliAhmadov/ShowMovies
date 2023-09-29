package com.ahmadov.showmovie.util

import androidx.compose.material.BottomNavigation
import androidx.compose.material3.BottomAppBar
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.ahmadov.showmovie.R


sealed class HomeBottomNavigation(val title: String, val icon: Int, val route: String) {

    object Home : HomeBottomNavigation("Home", R.drawable.ic_home, "home")
    object Favorite : HomeBottomNavigation("Favorite", R.drawable.ic_favorite, "favorite")


    @Composable
    fun BottomNavigationBar(navController: NavController) {

        val navigationItems= listOf(
            HomeBottomNavigation.Home,
            HomeBottomNavigation.Favorite
        )
        BottomNavigation {

        }







    }


}