package com.example.acerestaurantmenucompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
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
                    MainMenu()
                    //PlayWithFormatting("Android")
                }
            }
        }
    }
}

@Composable
fun MainMenu()
{
    Column(
        modifier = Modifier
            .fillMaxSize()
    )
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ){
            Text(
                text = "Ace Restaurant",
                modifier = Modifier
                    .align(Alignment.TopCenter)
                //.padding(20.dp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ){
            var menuItems = ArrayList<String>()
            PopulateMenuItems(menuItems)
            MenuList(menuItems)
        }
    }
}

@Composable
fun MenuList(messages: List<String>) {
    Column {
        messages.forEach {
                message ->
            //Text(text = "$message")
            MainMenuItem("$message")
        }
    }
}

@Composable
fun MainMenuItem(name: String)
{
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
    ){
        Column {
            Text(
                text = "$name",
                modifier = Modifier.align(Alignment.Start)
            )
        }
        Column {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier.align(Alignment.End)
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AceRestaurantMenuComposeTheme {
        MainMenu()
    }
}

fun PopulateMenuItems(arrayList: ArrayList<String>)
{
    //TODO: use this subroutine to fetch JSON from gist
    arrayList.add("Menu")
    arrayList.add("Online Ordering")
    arrayList.add("Calorie Counter")
    arrayList.add("Catering")
    arrayList.add("Beverage Mixer")
    arrayList.add("Reservations")
    arrayList.add("Get Directions")
    arrayList.add("About Us")
    arrayList.add("Contact Us")
}