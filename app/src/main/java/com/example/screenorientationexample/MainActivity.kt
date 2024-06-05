package com.example.screenorientationexample

import android.content.res.Configuration
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
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
        enableEdgeToEdge()
        setContent {
            ScreenOrientationExampleTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    val configuration = LocalConfiguration.current
                    when (configuration.orientation) {
                        Configuration.ORIENTATION_PORTRAIT -> {
                            PortraitMessages(modifier = Modifier.padding(innerPadding))
                        }

                        Configuration.ORIENTATION_LANDSCAPE -> {
                            LandscapeMessages(modifier = Modifier.padding(innerPadding))
                        }

                        else -> {
                            Message(
                                "Strange orientation: ${configuration.orientation}",
                                modifier = Modifier.padding(innerPadding)
                            )
                        }
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

@Composable
fun LandscapeMessages(modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        for (i in 1..5) {
            Message(name = "Landscape $i")
        }
    }
}

@Composable
fun PortraitMessages(modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        for (i in 1..5) {
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