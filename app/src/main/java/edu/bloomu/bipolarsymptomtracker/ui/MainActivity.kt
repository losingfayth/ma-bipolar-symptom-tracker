package edu.bloomu.bipolarsymptomtracker.ui

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.room.util.TableInfo
import edu.bloomu.bipolarsymptomtracker.R
import edu.bloomu.bipolarsymptomtracker.ui.components.BasicCard
import edu.bloomu.bipolarsymptomtracker.ui.components.HomeScreenNavCard
import edu.bloomu.bipolarsymptomtracker.ui.theme.BipolarSymptomTrackerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BipolarSymptomTrackerTheme {
                MainContainer(modifier = Modifier)
            }
        }
    }
}

@Composable
fun MainContainer(
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier
            .fillMaxSize()
    ) {
        val context = LocalContext.current

        HomeScreenNavCard(
            stringResource(id = R.string.md_ref_home_new_entry_title),
            stringResource(id = R.string.md_ref_home_new_entry_desc),
            painterResource(id = R.drawable.edit_square_24px),
            modifier = Modifier.clickable(onClick = {
                context.startActivity(Intent(context, EntryScreenActivity::class.java))
            })
        )

        HomeScreenNavCard(
            stringResource(id = R.string.md_ref_home_view_entries_title),
            stringResource(id = R.string.md_ref_home_view_entries_desc),
            painterResource(id = R.drawable.library_books_24px),
            modifier = Modifier.clickable(onClick = {
                context.startActivity(Intent(context, ViewEntriesActivity::class.java))
            })
        )

        HomeScreenNavCard(
            stringResource(id = R.string.md_ref_home_cycle_analysis_title),
            stringResource(id = R.string.md_ref_home_cycle_analysis_desc),
            painterResource(id = R.drawable.sentiment_very_satisfied_24px),
            modifier = Modifier.clickable(onClick = {
                context.startActivity(Intent(context, EntryScreenActivity::class.java))
            })
        )
    }
}