package com.implementing.pscomposediary.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.implementing.pscomposediary.R

private val MontserratAlternatives = FontFamily(
    Font(R.font.avenirheavy),
    Font(R.font.avenirmedium),
    Font(R.font.jost_book),
    Font(R.font.jost_medium)
)

private val Montserrat = FontFamily(
    Font(R.font.montserrat_alternates_light, FontWeight.W300),
    Font(R.font.montserrat_alternates_regular, FontWeight.W400),
    Font(R.font.montserrat_alternates_medium, FontWeight.W500),
    Font(R.font.montserrat_alternates_semibold, FontWeight.W600),
    Font(R.font.montserrat_alternates_bold, FontWeight.W700),
)

private val Jost = FontFamily(
    Font(R.font.jost_book),
    Font(R.font.jost_medium)
)

private val Avenir = FontFamily(
    Font(R.font.avenirheavy),
    Font(R.font.avenirmedium)
)

// Set of Material typography styles to start with
val typography = Typography(

    bodyLarge = TextStyle(
        fontFamily = MontserratAlternatives,
        fontWeight = FontWeight.W800,
        fontSize = 32.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = MontserratAlternatives,
        fontWeight = FontWeight.W700,
        fontSize = 32.sp
    ),

    bodySmall = TextStyle(
        fontFamily = MontserratAlternatives,
        fontWeight = FontWeight.W600,
        fontSize = 24.sp
    ),

    titleLarge = TextStyle(
        fontFamily = MontserratAlternatives,
        fontWeight = FontWeight.W600,
        fontSize = 20.sp
    ),

    titleMedium = TextStyle(
        fontFamily = MontserratAlternatives,
        fontWeight = FontWeight.W600,
        fontSize = 16.sp
    ),

    titleSmall = TextStyle(
        fontFamily = MontserratAlternatives,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
// For Header Font - Making Memories
    labelSmall  = TextStyle(
        fontFamily = Montserrat,
        fontSize = 15.sp
    ),

    displayMedium = TextStyle(
        fontFamily = Jost,
        fontSize = 14.sp
    ),

    displaySmall = TextStyle(
        fontFamily = Avenir,
        fontSize = 14.sp
    )





    /* Other default text styles to override

    labelSmall = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )
    */
)