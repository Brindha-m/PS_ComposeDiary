package com.implementing.pscomposediary.data

import android.content.Context
import com.implementing.pscomposediary.R
import com.implementing.pscomposediary.data.model.MeetUp

object MeetUpProvider{

    fun getMeetup(
        meetupId: Int,
        context: Context
    ): MeetUp = getMeetupList(context).find{
        it.meetupId == meetupId
    }!!

    fun getMeetupList(context: Context) = listOf(
        MeetUp(
            1,
            "First MeetUp",
            "Hello",
            R.drawable.tangled,
            "24-12-2022",
            "",
            "",
            listOf(R.drawable.brindz,R.drawable.group,R.drawable.onboarding1)
        ),

        MeetUp(
            2,
            "Second MeetUp",
            "Hellooo",
            R.drawable.brindz,
            "14-01-2023",
            "",
            "Thunivu no guts no glory",
            listOf(R.drawable.brindz,R.drawable.group,R.drawable.onboarding1)

        ),

        MeetUp(
            3,
            "Birthday MeetUp",
            "Hellooo",
            R.drawable.brindz,
            "",
            "Chennai",
            "",
            listOf(R.drawable.brindz,R.drawable.group,R.drawable.onboarding1)
        ),

        MeetUp(
            4,
            "Fourth MeetUp",
            "Hellooo",
            R.drawable.brindz,
            "",
            "",
            "Thunivu no guts no glory",
            listOf(R.drawable.brindz,R.drawable.group,R.drawable.onboarding1)
        ),
    )
}

