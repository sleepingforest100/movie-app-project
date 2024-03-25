package com.salt.apps.moov.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.salt.apps.moov.R

val Hanken = FontFamily(
    Font(R.font.hanken_grotesk_regular, FontWeight.Normal),
    Font(R.font.hanken_grotesk_light, FontWeight.Light),
    Font(R.font.hanken_grotesk_medium, FontWeight.Medium),
    Font(R.font.hanken_grotesk_bold, FontWeight.Bold),
    Font(R.font.hanken_grotesk_semibold, FontWeight.SemiBold),
    Font(R.font.hanken_grotesk_extrabold, FontWeight.ExtraBold),
)


val Typography = Typography(
    bodySmall = TextStyle(
        fontFamily = Hanken,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
    ),
    bodyMedium = TextStyle(
        fontFamily = Hanken,
        fontWeight = FontWeight.Normal,
        fontSize = 17.sp,
    ),
    bodyLarge = TextStyle(
        fontFamily = Hanken,
        fontWeight = FontWeight.Normal,
        fontSize = 19.sp,
    ),

    titleSmall = TextStyle(
        fontFamily = Hanken,
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
    ),

    titleMedium = TextStyle(
        fontFamily = Hanken,
        fontWeight = FontWeight.Medium,
        fontSize = 17.sp,
    ),

    titleLarge = TextStyle(
        fontFamily = Hanken,
        fontWeight = FontWeight.Medium,
        fontSize = 19.sp,
    ),
)