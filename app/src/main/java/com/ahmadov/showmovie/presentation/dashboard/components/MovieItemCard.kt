package com.ahmadov.showmovie.presentation.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.SubcomposeAsyncImage
import coil.request.ImageRequest
import com.ahmadov.showmovie.R
import com.ahmadov.showmovie.data.model.remote.movie.MovieItem
import com.ahmadov.showmovie.presentation.Screen
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition


@Composable
fun MovieItemCard (item  : MovieItem?,modifier: Modifier,navController: NavController ) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .background(color = Color.White)
            .clickable {
                navController.navigate(Screen.MovieDetailsScreen.route + "?movieId=${item?.id.toString()}&moviesTitle=${item?.title}")
            },
        shape = RoundedCornerShape(10.dp)
    ) {
        Column(
            modifier = modifier
        ) {
            SubcomposeAsyncImage(
                model = ImageRequest.Builder(LocalContext.current)
                    .data("https://image.tmdb.org/t/p/original" + item?.poster_path)
                    .crossfade(true)
                    .build(),
                contentDescription = stringResource(id = R.string.description),

                contentScale = ContentScale.FillBounds,

                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp),
                loading = {
                    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(com.ahmadov.showmovie.R.raw.loading_image))
                    LottieAnimation(
                        composition = composition,
                        iterations = LottieConstants.IterateForever,
                        modifier = Modifier
                            .fillMaxSize()
                    )
                }
            )
            Spacer(modifier = Modifier.height(10.dp))
            val lineHeight = androidx.compose.material.MaterialTheme.typography.h6.fontSize * 4 / 3
            Text(
                text = item?.title ?: "",
                style = androidx.compose.material.MaterialTheme.typography.body1,
                modifier = Modifier.padding(horizontal = 10.dp),
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis,
                lineHeight = lineHeight
            )
            Text(
                text = item?.release_date?: "",
                style = androidx.compose.material.MaterialTheme.typography.caption,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
        }

    }


}