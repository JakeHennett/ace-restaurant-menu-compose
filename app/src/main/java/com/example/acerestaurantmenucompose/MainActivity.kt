package com.example.acerestaurantmenucompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
                    MainMenuItem("Menu")
                    //PlayWithFormatting("Android")
                }
            }
        }
    }
}

@Composable
fun MainMenuItem(name: String)
{
    //TODO: create a class and pass that object as input parameter
    Row(
        modifier = Modifier
            .fillMaxWidth()
    ){
        Column(
            //modifier = Modifier.align(Alignment.Start)
        ) {
            Text(
                text = "$name",
                modifier = Modifier.align(Alignment.Start)
            )
        }
        Column() {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier.align(Alignment.End)
            )

        }
    }
}

@Composable
fun PlayWithFormatting(name: String) {
    Box(
        modifier = Modifier
            .size(400.dp)
            //.background(androidx.compose.ui.graphics.Color.Gray)
    ) {
        Text(
            text = "Hello $name!",
            fontSize = 30.sp,
            color = androidx.compose.ui.graphics.Color.Cyan,
            modifier = Modifier
                .background(color = androidx.compose.ui.graphics.Color.Blue)
                .padding(16.dp)
        )
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = null,
            modifier = Modifier
                .align(Alignment.Center)
                .size(500.dp)
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
        MainMenuItem("Menu")
        //PlayWithFormatting("Moto")
    }
}