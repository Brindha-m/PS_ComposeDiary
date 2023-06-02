package com.implementing.pscomposediary.ui.meetup

import android.os.Build
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.ImageLoader
import coil.compose.rememberAsyncImagePainter
import coil.decode.GifDecoder
import coil.decode.ImageDecoderDecoder
import com.implementing.pscomposediary.R
import com.implementing.pscomposediary.confetticenter.ConfettiCenterView
import com.implementing.pscomposediary.data.MeetUpProvider
import com.implementing.pscomposediary.data.model.MeetUp
import com.implementing.pscomposediary.ui.theme.PSDiaryTheme

// Start of the MeetupList UI
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MeetUpList(
    selectedMeetup: (Int) -> Unit
) {

    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            TopAppBar(
                modifier = Modifier.padding(top=30.dp),
                scrollBehavior = scrollBehavior,
//                navigationIcon = {
//                    IconButton(onClick = {}) {
//
//                    }
//                },
                title = {
                    Text(text = "")
                    Image(
                        painter = painterResource(R.drawable.please),
                        contentDescription = null,
                    )

                },

                colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Transparent),
            )
        },
    ) { paddingValues ->
        MeetupListBody(selectedMeetup, paddingValues)
    }
}

// Start of List
@Composable
private fun MeetupListBody(
    selectedMeetup: (Int) -> Unit,
    paddingValues: PaddingValues
) {
    // Save the scroll state of meetup list
    val scrollState = rememberLazyListState()
    val context = LocalContext.current
    val meetups: List<MeetUp> = MeetUpProvider.getMeetupList(context)

    LazyColumn(
        modifier = Modifier.padding(top = paddingValues.calculateTopPadding()),
        contentPadding = PaddingValues(8.dp)
    ) {
        item {
            HeaderNew()
        }
    }

    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
            .padding(16.dp)
            .padding(paddingValues)
            .fillMaxSize(),
        state = scrollState,
        verticalAlignment = Alignment.CenterVertically
    ) {

        items(meetups){meetup ->
            MeetUpRow(meetup, selectedMeetup)
        }
    }
}

// Item row for one meetup.
@Composable
private fun MeetUpRow(
    meetup: MeetUp,
    selectedMeetup: (Int) -> Unit
) {
    var arrowExpanded by remember { mutableStateOf(false) }

    Card(
        Modifier
            .fillMaxWidth()
            .clickable(onClick = { selectedMeetup(meetup.meetupId) }),
        shape = MaterialTheme.shapes.large
    ) {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {
            AnimatedVisibility(
                visible = true
            ) {
                val imageLoader = ImageLoader.Builder(LocalContext.current)
                    .components {
                        if (Build.VERSION.SDK_INT >= 28) {
                            add(ImageDecoderDecoder.Factory())
                        } else {
                            add(GifDecoder.Factory())
                        }
                    }
                    .build()
                Image(
//                    painter = painterResource(id = meetup.meetupImage),
                    painter = rememberAsyncImagePainter(meetup.meetupImage, imageLoader),
                    contentDescription = stringResource(R.string.description),
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .size(290.dp)
                        .clip(CircleShape),
                )
                ConfettiCenterView()

            }

            Spacer(Modifier.height(24.dp))

            Text(
                text = meetup.meetupTitle,
                style = MaterialTheme.typography.bodyMedium,
            )

            Spacer(Modifier.height(4.dp))

            IconButton(onClick = { arrowExpanded = !arrowExpanded }) {
                Icon(
                    painter = painterResource(getIconState(arrowExpanded)),
                    contentDescription = stringResource(R.string.description),
                    modifier = Modifier.size(32.dp),
                )
            }

            AnimatedVisibility(arrowExpanded) {
                Text(
                    text = meetup.meetupDescription,
                    style = MaterialTheme.typography.displayMedium,
                    modifier = Modifier.width(240.dp),
                    textAlign = TextAlign.Center,
                )
            }
        }
    }
}

// For animation visibility, change icon based on state remembered
// Composable is reused in CatDetails.kt
@Composable
fun getIconState(
    arrowExpanded: Boolean
): Int = if (arrowExpanded) {
    R.drawable.ic_arrow_up
} else {
    R.drawable.ic_arrow_down
}

@Preview("List Dark Theme", widthDp = 360, heightDp = 640)
@Composable
fun ListDarkPreview() {
    PSDiaryTheme (darkTheme = true){
        MeetUpList(selectedMeetup = { })
    }
}

//@Preview("List Light Theme", widthDp = 360, heightDp = 640)
//@Composable
//fun ListLightPreview() {
//    PuppyTheme(darkTheme = false) {
//        CatsList(selectedCat = { })
//    }
//}
