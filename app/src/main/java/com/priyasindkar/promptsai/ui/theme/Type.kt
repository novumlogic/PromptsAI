package com.priyasindkar.promptsai.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.priyasindkar.promptsai.R

val OrbitronFont = FontFamily(Font(R.font.orbitron_medium))
val MontserratRegularFont = FontFamily(Font(R.font.montserrat_regular))
val MontserratItalicsFont = FontFamily(Font(R.font.montserrat_italic))

val CustomAppBarTitleTypography = TextStyle(
    fontFamily = OrbitronFont,
    fontWeight = FontWeight.Normal,
    fontSize = 22.sp,
    lineHeight = 28.sp,
    letterSpacing = 0.sp,
)

val EmptyTextTypography = TextStyle(
    fontFamily = MontserratRegularFont,
    fontWeight = FontWeight.Normal,
    fontSize = 20.sp,
    lineHeight = 20.sp,
    letterSpacing = 0.sp,
)

// Set of Material typography styles to start with
val PromptsAITypography = Typography(
    bodyLarge = TextStyle(
        fontFamily = MontserratRegularFont,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    bodySmall = TextStyle(
        fontFamily = MontserratRegularFont,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.2.sp
    ),
    titleLarge = TextStyle(
        fontFamily = OrbitronFont,
        fontWeight = FontWeight.Normal,
        fontSize = 22.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelSmall = TextStyle(
        fontFamily = MontserratRegularFont,
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),
    titleMedium = TextStyle(
        fontFamily = OrbitronFont,
        fontWeight = FontWeight.Normal,
        fontSize = 20.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.sp
    ),
    titleSmall = TextStyle(
        fontFamily = OrbitronFont,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp,
        lineHeight = 20.sp,
        letterSpacing = 0.sp
    ),
)