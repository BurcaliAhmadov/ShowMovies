package com.ahmadov.showmovie.presentation.dashboard.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.ahmadov.showmovie.R
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition

@Composable
fun IsLoading(isLoading : Boolean) {

    val composition by rememberLottieComposition(LottieCompositionSpec.RawRes(R.raw.anim_loading))
    if(isLoading){

        Box (
            modifier = Modifier
                .fillMaxSize()
                .background(Color.LightGray.copy(alpha = .5f))
        ) {
            LottieAnimation(
                composition = composition,
                iterations = LottieConstants.IterateForever,
                modifier = Modifier.padding(130.dp)

                )

        }


    }
    
}