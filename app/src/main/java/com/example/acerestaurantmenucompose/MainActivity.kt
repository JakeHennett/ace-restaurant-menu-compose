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
    Box(
        modifier = Modifier
            //.size(400.dp)
            .fillMaxSize()
    ) {
        Text(
                text = "Ace Restaurant",
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    //.padding(20.dp)
            )
        //MenuListLazy()
        //MainMenuItem("Menu")
        //TODO: Replace the hard coded items with JSON
        var menuItems = ArrayList<String>()
        PopulateMenuItems(menuItems)
        menuItems.add("Menu")
        menuItems.add("Online Ordering")
        menuItems.add("Calorie Counter")
        menuItems.add("Catering")
        menuItems.add("Beverage Mixer")
        menuItems.add("Reservations")
        menuItems.add("Get Directions")
        menuItems.add("About Us")
        menuItems.add("Contact Us")
        MenuList(menuItems)
    }
}

@Composable
fun MenuListLazy()
{
    LazyColumn {
        // Add a single item
        item {
            Text(text = "First item")
        }

        // Add 5 items
        items(5) { index ->
            Text(text = "Item: $index")
        }

        // Add another single item
        item {
            Text(text = "Last item")
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
    //TODO: create a class and pass that object as input parameter
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
            color = Color.Cyan,
            modifier = Modifier
                .background(color = Color.Blue)
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
        MainMenu()
        //MainMenuItem("Menu")
        //PlayWithFormatting("Moto")
    }
}

fun PopulateMenuItems(arrayList: ArrayList<String>)
{
    
}