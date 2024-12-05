package edu.bloomu.bipolarsymptomtracker.ui.theme

import androidx.compose.ui.unit.dp

object Units {
    object Padding {
        val Icon = 12.dp
        val CardTitle = 16.dp
        object TopBar {
            val Horizontal = 24.dp
            val Vertical = 16.dp
            val Height = 80.dp
        }

    }
    object Row {
        object Padding {
            val Horizontal = 36.dp
            val Vertical = 16.dp
            val ExtraHorizontal = 48.dp
            val ExtraVertical = 24.dp
        }
    }
    object Icons {
        val Standard = 36.dp
        val Large = 48.dp
        val ExtraLarge = 60.dp
        val ExxtraLarge = 96.dp
        val ExxxtraLarge = 144.dp
    }
    object Scaffold {
        object TopBar {}
        object BottomBar {}
        object Fab {
            val Size = 64.dp
            val Padding = 16.dp
        }
        object NavButton {
            object Button {
                val PaddingHorizontal = 16.dp
                val PaddingVertical = 0.dp
            }
            object Icon {
                val PaddingHorizontal = 2.dp
                val PaddingVertical = 4.dp
            }
            object Text {
                val PaddingHorizontal = 4.dp
                val PaddingVertical = 1.dp
                val PaddingBottom = 1.dp
            }
        }
    }
}