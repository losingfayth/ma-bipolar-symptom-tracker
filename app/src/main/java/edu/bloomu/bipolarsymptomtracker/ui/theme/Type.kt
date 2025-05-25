package edu.bloomu.bipolarsymptomtracker.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import edu.bloomu.bipolarsymptomtracker.R

// Set of Material typography styles to start with

val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.baloo2)),
        fontWeight = FontWeight.Normal,
        fontSize = 57.sp,
        lineHeight = 64.sp
    ),
    displayMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.baloo2)),
        fontWeight = FontWeight.Normal,
        fontSize = 45.sp,
        lineHeight = 52.sp
    ),
    displaySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.baloo2)),
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp,
        lineHeight = 44.sp
    ),
    headlineLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.baloo2)),
        fontWeight = FontWeight.Medium,
        fontSize = 32.sp,
        lineHeight = 40.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.baloo2)),
        fontWeight = FontWeight.Medium,
        fontSize = 28.sp,
        lineHeight = 36.sp
    ),
    headlineSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.baloo2)),
        fontWeight = FontWeight.Medium,
        fontSize = 24.sp,
        lineHeight = 32.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.baloo2)),
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 28.sp
    ),
    titleMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.baloo2)),
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    titleSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.baloo2)),
        fontWeight = FontWeight.Medium,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    labelLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.baloo2)),
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    labelMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.baloo2)),
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp,
        lineHeight = 16.sp
    ),
    labelSmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.baloo2)),
        fontWeight = FontWeight.Medium,
        fontSize = 11.sp,
        lineHeight = 16.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = FontFamily(Font(R.font.baloo2)),
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = FontFamily(Font(R.font.baloo2)),
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp,
        lineHeight = 20.sp
    ),
    bodySmall = TextStyle(
        fontFamily = FontFamily(Font(R.font.baloo2)),
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp,
        lineHeight = 16.sp
    )
)

// Question: Do we think this will work?
// Answer: No. But we can give it a shot, I guess...
@Composable
fun dynamicStyle(
    staticStyle: TextStyle
) : TextStyle {
    val fontSizeDp: Dp = with(LocalDensity.current) { staticStyle.fontSize.toDp() }
    val lineHeightDp: Dp = with(LocalDensity.current) { staticStyle.lineHeight.toDp() }

    val fontSizeSp: TextUnit = with(LocalDensity.current) { fontSizeDp.toSp() }
    val lineHeightSp: TextUnit = with(LocalDensity.current) { lineHeightDp.toSp() }

    return TextStyle(
        fontFamily = staticStyle.fontFamily,
        fontWeight = staticStyle.fontWeight,
        fontSize = fontSizeSp,
        lineHeight = lineHeightSp
    )
}