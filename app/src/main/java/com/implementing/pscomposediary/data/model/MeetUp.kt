package com.implementing.pscomposediary.data.model

import androidx.compose.runtime.Immutable
import java.io.Serializable

@Immutable
data class MeetUp(
    val meetupId: Int,
    val meetupTitle: String,
    val meetupDescription: String,
    val meetupImage: Int,
    val meetupDate: String,
    val meetupLocation: String,
    val meetupSpecial: String,
    val meetupHighlight : List<Int>,
    val meetupTags: List<String>

) : Serializable

