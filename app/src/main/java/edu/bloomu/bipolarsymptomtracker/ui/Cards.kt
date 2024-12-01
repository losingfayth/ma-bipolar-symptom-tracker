package edu.bloomu.bipolarsymptomtracker.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import edu.bloomu.bipolarsymptomtracker.model.Symptom

@Composable
fun BasicCard(
    modifier: Modifier,
    borderColor: Color,
    content: @Composable () -> Unit
) {
    val shape = RoundedCornerShape(24.dp)
    Card (
        shape = shape,
        colors = CardDefaults.cardColors(),
        modifier = modifier
            .padding(8.dp),
        border = BorderStroke(2.dp, borderColor)
    ) {
        Box {
            content()
        }
    }
}

@Composable
fun SymptomCard(
    borderColor: Color,
    symptom: Symptom,
    icon: ImageVector,
    modifier: Modifier
) {
    BasicCard(borderColor = borderColor,
        modifier = modifier.size(200.dp))
    {

        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxHeight()
        ) {
            Text(symptom.getName())
            Text(symptom.getDesc())
            Switch(checked = symptom.isSymptomatic(), onCheckedChange = { symptom.toggleSymptomatic(); })
        }
    }
}

@Composable
fun MoodCard() {

}