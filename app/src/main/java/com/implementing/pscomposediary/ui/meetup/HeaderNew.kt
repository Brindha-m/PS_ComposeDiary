package com.implementing.pscomposediary.ui.meetup

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.implementing.pscomposediary.R

@Preview
@Composable
fun HeaderNew() {
    Box(
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            "Making Memories 💭",
            style = MaterialTheme.typography.labelSmall,
            modifier = Modifier
                .padding(start = 45.dp, top = 20.dp)
                .align(Alignment.CenterStart)
        )
        Image(
            imageVector = ImageVector.vectorResource(R.drawable.ic_baseline_bubble_chart_24),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary),
            modifier = Modifier
                .padding(start = 15.dp)
                .align(Alignment.BottomStart)
        )
    }
    Spacer(modifier = Modifier.height(15.dp))

    Text(
        "Let's Recall!",
        style = MaterialTheme.typography.labelSmall,
        modifier = Modifier.padding(start = 25.dp, bottom = 20.dp)
    )
}
