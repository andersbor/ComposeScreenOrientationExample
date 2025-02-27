package com.example.screenorientationexample

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.screenorientationexample.ui.theme.ScreenOrientationExampleTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ScreenOrientationExampleTheme {
                MainScreen()
            }
        }
    }
}

@Composable
private fun MainScreen() {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Text(
                text = "Another component that is always below the messages",
                modifier = Modifier
                    .padding(8.dp)
                    .weight(1f)
            )
            val configuration = LocalConfiguration.current
            when (configuration.orientation) {
                Configuration.ORIENTATION_PORTRAIT -> {
                    PortraitMessages()
                }

                Configuration.ORIENTATION_LANDSCAPE -> {
                    LandscapeMessages()
                }

                else -> {
                    Message(
                        "Strange orientation: ${configuration.orientation}",
                    )
                }
            }
        }
    }
}

@Composable
fun Message(message: String, modifier: Modifier = Modifier) {
    Card(
        border = BorderStroke(1.dp, Color.Black),
        modifier = modifier.padding(8.dp)
    ) {
        Text(
            text = message,
            modifier = modifier.padding(8.dp)
        )
    }
}

@OptIn(ExperimentalLayoutApi::class) // FlowRow is experimental
@Composable
fun LandscapeMessages(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    // FlowRow is a horizontal layout that wraps when there is not enough space
    FlowRow(
        modifier = modifier
            .padding(8.dp)
            .verticalScroll(scrollState)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        //maxItemsInEachRow = 4
    ) {
        for (i in 1..50) {
            Message(message = "Landscape $i")
        }
    }
}

@Composable
fun PortraitMessages(modifier: Modifier = Modifier) {
    // FlowColumn is a vertical layout that wraps when there is not enough space
    // built in scrolling
    LazyColumn(modifier = modifier.padding(8.dp)) {
        items(50) { i ->
            Message(message = "Portrait $i")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun MessagePreview() {
    ScreenOrientationExampleTheme {
        Message("Android")
    }
}

@Preview
@Composable
fun MainScreenPreview() {
    ScreenOrientationExampleTheme {
        MainScreen()
    }
}

@Preview(showBackground = true)
@Composable
fun PortraitMessagesPreview() {
    ScreenOrientationExampleTheme {
        PortraitMessages()
    }
}