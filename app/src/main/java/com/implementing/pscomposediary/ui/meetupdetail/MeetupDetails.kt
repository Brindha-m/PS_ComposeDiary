package com.implementing.pscomposediary.ui.meetupdetail

import android.content.res.Configuration
import android.os.Build.VERSION.SDK_INT
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.expandVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.implementing.pscomposediary.R
import com.implementing.pscomposediary.data.MeetUpProvider
import com.implementing.pscomposediary.data.model.MeetUp
import com.implementing.pscomposediary.ui.theme.PSDiaryTheme


// Start of the Detail screen for each cat.
@Composable
fun MeetupDetails(
    meetupId: Int,
    navigateUp: () -> Unit
) {
    val context = LocalContext.current

    val meetup: MeetUp = remember(meetupId) {
        MeetUpProvider.getMeetup(
            meetupId, context
        )
    }

    AnimatedVisibility(
        visible = true,
        enter = expandVertically(
            expandFrom = Alignment.Top,
            initialHeight = { 0 }
        )
    ) {
        SetMeetupDetails(meetup, navigateUp)
    }
}

// This UI starts with AnimatedVisibility
@Composable
private fun SetMeetupDetails(
    meetup: MeetUp,
    navigateUp: () -> Unit
) {
    Surface {
        Column(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyColumn {
                item { MeetupHeader(meetup, navigateUp) }
            }
        }
    }
}

// Show up button, banner of the cat, custom app bar.
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
private fun MeetupHeader(
    meetup: MeetUp,
    navigateUp: () -> Unit
) {

    TopAppBar(
        title = {
            Text(text = "Back to Home 🏡")
        },
        Modifier.background(Color.Transparent),
        navigationIcon = {
            IconButton(onClick = navigateUp) {
                Icon(imageVector = Icons.Rounded.ArrowBack, contentDescription = "Go Back")
            }
        }
    )

    Box (modifier = Modifier.padding(10.dp)){
        Card(
            //shape = MaterialTheme.shapes.medium,
            shape = RoundedCornerShape(20.dp),
            modifier = Modifier.padding(5.dp, 5.dp, 5.dp, 5.dp),
            //set card elevation of the card
            elevation = CardDefaults.cardElevation(
                defaultElevation = 10.dp,
            ),
            colors = CardDefaults.cardColors(
                containerColor = Color(0xffF4EAEE),
//            containerColor = Color.blue - alternate way to change the color of the card
            ),
        ) {
            Column(modifier = Modifier.clickable(onClick = { })) {

                val imageLoader = ImageLoader.Builder(LocalContext.current)
                    .components {
                        if (SDK_INT >= 28) {
                            add(ImageDecoderDecoder.Factory())
                        } else {
                            add(GifDecoder.Factory())
                        }
                    }
                    .build()

                Image(
//                painter = painterResource(R.drawable.tangled),
                    painter = rememberAsyncImagePainter(meetup.meetupImage, imageLoader),
//                    bitmap = ImageBitmap.imageResource(meetup.meetupImage),
                    contentDescription = "null", // decorative
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(250.dp)
                        .fillMaxWidth()
                )

                Column(modifier = Modifier.padding(16.dp)) {

                    Text(
                        text = meetup.meetupTitle,
                        style = MaterialTheme.typography.titleMedium,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    HeaderInfoDivider()

                    Spacer(modifier = Modifier.height(10.dp))

                    FlowRow(modifier = Modifier.padding(10.dp))
                    {

                        Text(
                            text = meetup.meetupSpecial,
                            overflow = TextOverflow.Ellipsis,
                            style = MaterialTheme.typography.displayMedium,
                        )

                        var checked by rememberSaveable { mutableStateOf(false) }
                        Spacer(modifier = Modifier.width(250.dp))
                        IconToggleButton(checked = checked, onCheckedChange = { checked = it }) {
                            val tint by animateColorAsState(
                                if (checked) Color(0xFFEC407A) else Color(0xFFB0BEC5),
                                label = "Click Here"
                            )
                            Icon(
                                Icons.Filled.Favorite,
                                contentDescription = "Localized description",
                                tint = tint,
                            )
                        }
                    }
                }
            }
        }
    }
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(25.dp, top= 5.dp, 25.dp, bottom = 10.dp),
        colors = CardDefaults.cardColors(
            containerColor =  Color(0xffF4EAEE),
        ),
    ) {
        Column (
            modifier = Modifier.padding(13.dp).align(Alignment.CenterHorizontally),
        ) {
            Text(
                buildAnnotatedString {
                    withStyle(
                        style = SpanStyle( fontSize = 14.sp) ,
                    ) {
                        append(" 📌 ${meetup.meetupLocation} ")
                    }

                    withStyle(
                        style = SpanStyle(color = Color.Red, fontSize = 14.sp)
                    ) {
                        append("  📅 ${meetup.meetupDate}")
                    }
                },
            )
        }
    }
    Column (modifier = Modifier.padding(15.dp, end = 5.dp, top = 15.dp)){
        Button(onClick = {},
            colors = ButtonDefaults.buttonColors(Color(0xFFEE1C70)))
        {
            Icon(Icons.Filled.Favorite, contentDescription = "Localized description",
                modifier = Modifier.size(ButtonDefaults.IconSize))
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("Highlights",
                style = MaterialTheme.typography.titleSmall
            )
        }

    }

    Row(modifier = Modifier.padding(18.dp)){
        meetup.meetupHighlight.take(3).forEach { highlight ->
            Image(
                painter = painterResource(highlight),
                contentDescription = stringResource(R.string.description),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(100.dp)
                    .clip(CircleShape)
                    .align(Alignment.CenterVertically)
            )
            Spacer(modifier = Modifier.width(10.dp))
        }
    }

    val configuration = LocalConfiguration.current
    val isPortrait = configuration.orientation == Configuration.ORIENTATION_PORTRAIT
    if (isPortrait) {
        FlowRow(modifier = Modifier.padding(start = 15.dp))
        {
            meetup.meetupTags.take(10).forEach { tags ->
                // * Elevated Button
                ElevatedButton(onClick = {})
                {
                    Text(
                        text = tags,
                        style = MaterialTheme.typography.displayMedium,

                    )
                }
                Spacer(modifier = Modifier.width(9.dp))
            }
        }
    }
    else{
        Row(modifier = Modifier.padding(start = 15.dp, end = 5.dp))
        {
            meetup.meetupTags.take(10).forEach { tags ->
                // * Elevated Button
                ElevatedButton(onClick = {})
                {
                    Text(
                        text = tags,
                        style = MaterialTheme.typography.displayMedium,
                    )
                }
                Spacer(modifier = Modifier.width(11.dp))
            }
        }
    }
}

// Horizontal divider
@Composable
private fun HeaderInfoDivider() {
    Divider(
        color = MaterialTheme.colorScheme.secondary,
        thickness = 1.dp,
        modifier = Modifier.padding(horizontal = 8.dp)
    )
}


@Preview("Detail Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun DetailLightPreview() {
    PSDiaryTheme(darkTheme = false) {
        MeetupDetails(meetupId = 1, navigateUp = { })
    }

}



