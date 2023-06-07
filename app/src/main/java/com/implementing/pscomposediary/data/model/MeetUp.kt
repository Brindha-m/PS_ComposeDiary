package com.implementing.pscomposediary.data.model

import androidx.compose.runtime.Immutable
import java.io.Serializable

//@Immutable
data class MeetUp(
    val meetupId: Int = 1,
    val meetupTitle: String,
    val meetupDescription: String,
    val meetupImage: String,
    val meetupDesImage: String,
    val meetupDate: String,
    val meetupLocation: String,
    val meetupSpecial: String,
    val meetupHighlight : List<String>,
    val meetupTags: List<String>
): Serializable {
    // Default, no-argument constructor
    constructor() : this(
        1,
        "",
        "",
        "",
        "",
        "",
        "",
        "",
        emptyList(),
        emptyList()
    )
}


