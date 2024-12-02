package edu.bloomu.bipolarsymptomtracker.ui.components

import android.content.Context
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import edu.bloomu.bipolarsymptomtracker.R
import edu.bloomu.bipolarsymptomtracker.model.Symptom
import kotlin.math.exp

@Composable
fun BasicCard(
    modifier: Modifier,
    content: @Composable () -> Unit
) {
    val shape = RoundedCornerShape(24.dp)
    Card (
        shape = shape,
        colors = CardDefaults.cardColors(),
        modifier = modifier
            .padding(8.dp)
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(8.dp),
            //horizontalAlignment = Alignment.CenterHorizontally,
            //verticalArrangement = Arrangement.Center
        ) {
            content()
        }
    }
}

@Composable
fun HomeScreenNavCard(
    title: String,
    desc: String,
    icon: Painter,
    modifier: Modifier
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp
    val screenHeight = configuration.screenHeightDp.dp
    BasicCard(
        modifier = modifier
            .size(screenWidth, screenHeight / 4)
            .padding(8.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top,
            modifier = modifier.padding(8.dp)
        ) {
            Image(icon, "")
            Text(
                text = title,
                style = MaterialTheme.typography.displayLarge,
                modifier = Modifier
            )
            //icon
        }
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.Top,
            modifier = modifier.padding(8.dp)
        ) {
            Text(
                text = desc,
                style = MaterialTheme.typography.displayMedium,
                modifier = Modifier
            )
        }
    }
}

@Composable
fun SymptomCard(
    symptom: Symptom,
    modifier: Modifier
) {
    var expanded by remember { mutableStateOf(false) }

    BasicCard(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
    )
    {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .fillMaxWidth()
        ) {
            // Checkbox and Name
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.weight(1f)
            ) {
                Checkbox(
                    checked = symptom.isSymptomatic(),
                    onCheckedChange = { symptom.toggleSymptomatic() }
                )
                Text(
                    symptom.getName(),
                    style = MaterialTheme.typography.displaySmall,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }

            // Icon Button
            IconButton(onClick = { expanded = !expanded }) {
                val icon = if (expanded) R.drawable.arrow_drop_down_24px else R.drawable.arrow_left_24px
                Icon(painter = painterResource(id = icon), contentDescription = null)
            }
        }

        // Description (Expanded Content)
        if (expanded) {
            Text(
                symptom.getDesc(),
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun EntryCard(
    icon: ImageVector,
    modifier: Modifier
) {
    BasicCard(
        modifier = modifier,
    ) {
        Image(
            imageVector = icon,
            contentDescription = ""
        )
    }
}