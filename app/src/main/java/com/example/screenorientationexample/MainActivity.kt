package com.example.screenorientationexample

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowColumn
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
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
                        Text(
                            text = "Another component that is always below the messages",
                            modifier = Modifier.padding(8.dp)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Message(name: String, modifier: Modifier = Modifier) {
    Card(border = BorderStroke(1.dp, Color.Black), modifier = modifier.padding(8.dp)) {
        Text(
            text = name,
            modifier = modifier.padding(8.dp)
        )
    }
}

@OptIn(ExperimentalLayoutApi::class) // FlowRow is experimental
@Composable
fun LandscapeMessages(modifier: Modifier = Modifier) {
    // FlowRow is a horizontal layout that wraps when there is not enough space
    Row(modifier = modifier) {
        for (i in 1..5) {
            Message(name = "Landscape $i")
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun PortraitMessages(modifier: Modifier = Modifier) {
    // FlowColumn is a vertical layout that wraps when there is not enough space
    Column(modifier = modifier) {
        for (i in 1..50) {
            Message(name = "Portrait $i")
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

@Preview(showBackground = true)
@Composable
fun PortraitMessagesPreview() {
    ScreenOrientationExampleTheme {
        PortraitMessages()
    }
}