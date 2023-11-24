package com.ahmadov.showmovie.presentation.search_movie.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ahmadov.showmovie.R
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun SearchEmpty(empty:Boolean) {
    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.empty_lottie))

    if(empty){
        Box (
            modifier = Modifier.fillMaxSize()

        ){
            LottieAnimation(
                composition = composition,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier
                    .fillMaxSize()
                    .padding(100.dp)
            )


        }
    }

}