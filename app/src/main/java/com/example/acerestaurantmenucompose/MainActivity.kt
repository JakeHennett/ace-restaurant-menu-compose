package com.example.acerestaurantmenucompose

import android.graphics.Color
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.acerestaurantmenucompose.ui.theme.AceRestaurantMenuComposeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AceRestaurantMenuComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    PlayWithFormatting("Android")
                }
            }
        }
    }
}

@Composable
fun PlayWithFormatting(name: String) {
    Box(
        modifier = Modifier
            .size(400.dp),
    ) {
        Text(
            text = "Hello $name!",
            fontSize = 30.sp,
            color = androidx.compose.ui.graphics.Color.Cyan,
            modifier = Modifier
                .background(color = androidx.compose.ui.graphics.Color.Blue)
                .padding(16.dp)
        )
        Text(
            text = "Bottom Right Button",
            modifier = Modifier
                .align(Alignment.BottomEnd)
        )

    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AceRestaurantMenuComposeTheme {
        PlayWithFormatting("Moto")
    }
}