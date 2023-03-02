package com.example.acerestaurantmenucompose

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.acerestaurantmenucompose.ui.theme.AceRestaurantMenuComposeTheme

class CalorieCounter : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AceRestaurantMenuComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    CalorieCounterScreen()
                    //PlayWithFormatting("Android")
                }
            }
        }
    }
}

@Composable
fun CalorieCounterScreen()
{
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Yellow)
    )
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ){
            Text(
                text = "Calorie Counter",
                modifier = Modifier
                    .align(Alignment.TopCenter)
                //.padding(20.dp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ){
            //var menuItems = ArrayList<String>()
            //populateMenuItems(menuItems)
            //MenuList(menuItems)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CalorieCounterPreview() {
    AceRestaurantMenuComposeTheme {
        CalorieCounterScreen()
    }
}