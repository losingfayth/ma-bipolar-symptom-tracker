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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import edu.bloomu.bipolarsymptomtracker.model.Symptom

@Composable
fun BasicCard(
    modifier: Modifier,
    //borderColor: Color,
    content: @Composable () -> Unit
) {
    val shape = RoundedCornerShape(24.dp)
    Card (
        shape = shape,
        colors = CardDefaults.cardColors(),
        modifier = modifier
            .padding(8.dp)
        //border = BorderStroke(2.dp, borderColor)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            content()
        }
    }
}

@Composable
fun HomeScreenNavCard(
    //borderColor: Color,
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
    BasicCard(
        modifier = modifier.size(200.dp)
    )
    {
        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxHeight()
        ) {
            Text(symptom.getName(), style = MaterialTheme.typography.titleLarge)
            Text(symptom.getDesc(), style = MaterialTheme.typography.bodyLarge)
            Switch(checked = symptom.isSymptomatic(), onCheckedChange = { symptom.toggleSymptomatic(); })
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