package com.ahmadov.showmovie.presentation.dashboard.components

import android.R
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.ahmadov.showmovie.presentation.Screen
import com.ahmadov.showmovie.ui.theme.WhiteTransparent

@Composable
fun SearchBar(navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .clip(shape = RoundedCornerShape(30.dp))
            .background(WhiteTransparent)
            .clickable {
                navController.navigate(Screen.SearchScreen.route)
            }

    ) {
        Row (
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ){
            Text(
                text = "Search for a movie...",
                style = androidx.compose.material.MaterialTheme.typography.body1,
                color = Color.Gray,
                modifier = Modifier.padding(horizontal = 15.dp)
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_menu_search),
                contentDescription = "search_icon",
                Modifier.padding(end = 20.dp),
                tint = Color.Black
            )

        }


    }
}

