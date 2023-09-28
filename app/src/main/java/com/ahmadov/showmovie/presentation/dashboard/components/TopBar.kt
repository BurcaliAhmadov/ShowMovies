package com.ahmadov.showmovie.presentation.dashboard.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ahmadov.showmovie.R

@Composable
fun TopBar(navController: NavController,visiable : Boolean) {
    if(visiable){
        Box (
            contentAlignment = Alignment.CenterStart
        ) {
            AsyncImage(model = ImageRequest.Builder(LocalContext.current)
                .data("https://image.tmdb.org/t/p/w1920_and_h600_multi_faces_filter%28duotone,032541,01b4e4%29/q719jXXEzOoYaps6babgKnONONX.jpg")
                .crossfade(true)
                .build(),
                contentDescription = stringResource(id = R.string.description),
                contentScale= ContentScale.FillBounds,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(250.dp)
            )
            Column(
                modifier = Modifier.padding(horizontal = 15.dp)
            ){
                Text(
                    text = "Welcome",
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
                Text(
                    text = "Every Film Holds a Story",
                    style = MaterialTheme.typography.bodySmall,
                    color = Color.White,
                    modifier = Modifier.padding(horizontal = 10.dp)
                )
                Spacer(modifier = Modifier.height(20.dp))
                SearchBar(navController)

            }

        }

    }



}