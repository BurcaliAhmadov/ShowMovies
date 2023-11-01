package com.ahmadov.showmovie.presentation.movie_details


import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ahmadov.showmovie.R
import com.ahmadov.showmovie.data.model.remote.detail.Credits
import com.ahmadov.showmovie.data.model.remote.detail.MovieDetail
import com.ahmadov.showmovie.data.model.remote.movie.ResultItem
import com.ahmadov.showmovie.data.model.remote.movie.Trailer
import com.ahmadov.showmovie.presentation.Screen
import com.ahmadov.showmovie.presentation.dashboard.components.ErrorView
import com.ahmadov.showmovie.presentation.dashboard.components.IsLoading
import com.ahmadov.showmovie.presentation.movie_details.components.CircularProgress
import com.ahmadov.showmovie.presentation.movie_details.components.ItemCastCard
import com.ahmadov.showmovie.presentation.view_all.components.ToolBar
import com.ahmadov.showmovie.ui.theme.ShowMovieTheme
import com.ahmadov.showmovie.util.formattedYear
import com.ahmadov.showmovie.util.minuteToTime
import java.util.Locale

@Composable
fun MovieDetailsScreen(
    navController: NavController,title:String,viewModel: MovieDetailsViewModel= hiltViewModel()
) {
    Scaffold(topBar = {
        ToolBar(title = title, onBack = {
            navController.popBackStack()
        })
    }){paddingValues ->
        Box(modifier = Modifier.padding(
            bottom = paddingValues.calculateBottomPadding()
        )){
            val details =viewModel.movieDetails.value
            val cast = viewModel.movieCredits.value
            val videos =viewModel.trailer.value
            if(details.id != null && cast.id != null){
                println(details.overview)
                LazyColumn(content = {
                    item { ItemPoster(response = details)}
                    item {ItemTitle(navController = navController, response = details, videos = videos)}
                    item {ItemOverView(response = details)}
                    item { ItemCast(credits = cast)}
                })
            }
            IsLoading(isLoading = viewModel.isLoading.containsValue(true))
            ErrorView(error = viewModel.apiError.value)


        }


    }

    




}

@Composable
fun ItemPoster(response:MovieDetail) {
    Box(modifier = Modifier.padding(horizontal = 15.dp)){
        
        AsyncImage(model = ImageRequest.Builder(LocalContext.current)
            .data("https://image.tmdb.org/t/p/original" + response.backdropPath).crossfade(true)
            .build()
            ,contentDescription = stringResource(id = R.string.description)
            , contentScale = ContentScale.FillBounds
            , modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
                .padding(start = 60.dp)
                .clip(shape = RoundedCornerShape(10.dp))
        )
        AsyncImage(model = ImageRequest.Builder(LocalContext.current)
            .data("https://image.tmdb.org/t/p/original" + response.posterPath)
            .build()
            , contentDescription =stringResource(id = R.string.description)
            , contentScale = ContentScale.FillBounds
            , modifier = Modifier
                .width(120.dp)
                .height(160.dp)
                .align(Alignment.CenterStart)
                .clip(shape = RoundedCornerShape(10.dp))
        )


    }

}

@Composable
fun ItemTitle(navController: NavController, response:MovieDetail,videos:Trailer) {
    Spacer(modifier = Modifier.height(20.dp))
    val title=response.title ?: ""
    val year = formattedYear(response.releaseDate) ?: ""
    
    Text(
        text = buildAnnotatedString {
        append(title); append(" ");withStyle(style = SpanStyle(color= Color.Gray)){
            append("($year)")
        }
        },
        style = MaterialTheme.typography.h5,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .padding(20.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Center
    )

    Row(
        modifier= Modifier
            .fillMaxWidth()
            .padding(horizontal = 15.dp)
        , horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "R",
            style = MaterialTheme.typography.body1,
            maxLines = 1,
            modifier = Modifier.padding(end = 10.dp)
        )
        val originalLanguage = if (response.originalLanguage != null) {
            "(${response.originalLanguage.uppercase(Locale.ROOT)})"
        } else ""

        Text(
            text = response.releaseDate + originalLanguage + response.runtime?.let {
                minuteToTime(it)
            },
            modifier = Modifier.padding(end = 10.dp),
            style = MaterialTheme.typography.body1,
            maxLines = 1
        )
    }

    LazyRow(
        horizontalArrangement = Arrangement.Center,
        modifier=Modifier.fillMaxWidth(),
        content ={
            response.genres?.forEach {
                item {
                    Text(
                        text = if(it==response.genres.last()) it?.name.toString() else it?.name + ", ",
                        style = MaterialTheme.typography.body1,
                        maxLines = 1,
                        color = Color.Gray

                    )
                }

            }
        } )
    Spacer(modifier = Modifier.height(20.dp))

    Row(
        modifier= Modifier
            .fillMaxWidth()
            .padding(horizontal = 20.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {

        CircularProgress(progressValue = response.voteAverage?.toFloat()?.div(10) ?: 0f)

        Text(
            text = "User Score",
            style = MaterialTheme.typography.body1,
            modifier = Modifier.padding(start = 10.dp, end = 15.dp)

        )
        Divider(
            modifier= Modifier
                .height(20.dp)
                .width(3.dp)
                .background(Color.LightGray)

        )
        Row(
            modifier= Modifier
                .clickable(onClick = {
                    val item = videos.results.last {
                        it.type == "Trailer"
                    }
                    navController.navigate(Screen.YoutubePlayerScreen.route + "youtubeCode=${item.key}")
                })
                .padding(start = 15.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                imageVector = Icons.Default.PlayArrow,
                contentDescription = stringResource(id = R.string.description),
                tint= Color.Black,
                modifier = Modifier.size(18.dp)
            )
            Text(
                text = "Play Trailer",
                style = MaterialTheme.typography.body1,
                maxLines = 1,
                modifier=Modifier.padding(end=10.dp)

            )

        }
    }
}

@Composable
fun ItemOverView(response:MovieDetail) {
    Spacer(modifier = Modifier.height(15.dp))
    Text(
        text = "Overview",
        style = MaterialTheme.typography.h5,
        maxLines = 1,
        modifier = Modifier.padding(start = 15.dp)
        
        )
    Spacer(modifier = Modifier.height(10.dp))
    val lineHeight=MaterialTheme.typography.h6.fontSize * 4/3
    Text(
        text = response.overview ?: "",
        style = MaterialTheme.typography.body1,
        lineHeight = lineHeight,
        modifier = Modifier.padding(horizontal = 15.dp)

    )
}

@Composable
fun ItemCast(credits: Credits) {
    Spacer(modifier = Modifier.height(15.dp))
    Text(
        text = "Top Billed Cast" ,
        style = MaterialTheme.typography.h5,
        maxLines = 1,
        modifier = Modifier.padding(horizontal = 15.dp)
    )
    Spacer(modifier=Modifier.height(10.dp))
    LazyRow(content = {
        credits.cast?.forEach {
            item {
                ItemCastCard(castItem = it)
            }
        }

    })
    Spacer(modifier = Modifier.height(15.dp))

}
