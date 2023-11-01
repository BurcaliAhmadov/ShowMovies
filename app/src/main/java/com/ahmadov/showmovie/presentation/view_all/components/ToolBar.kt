package com.ahmadov.showmovie.presentation.view_all.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ahmadov.showmovie.R

@Composable
fun ToolBar(title:String,onBack :() -> Unit) {
    TopAppBar(
        backgroundColor = Color.White,
        elevation = 0.dp

    ) {
        val iconSize=28.dp
        
        Row (
            modifier=Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                imageVector = Icons.Default.ArrowBack,
                contentDescription = "Arrow Back",
                modifier= Modifier
                    .padding(start = 15.dp)
                    .clickable {
                        onBack()
                    }
                    .size(iconSize)
            )
            Text(
                text = title,
                style = MaterialTheme.typography.h6,
                fontSize = 20.sp,
                color=Color.Black,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
                )
            Box(
                modifier = Modifier
                    .padding(end = 15.dp)
                    .size(iconSize)
            ) { }
            
        }

    }

}