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
fun CalorieCounterScreen() {
    var categories = ArrayList<String>()
    populateCategories(categories)

    var aceItems = ArrayList<AceItem>()
    populateAceItems(aceItems)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Yellow)
    )
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
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
        ) {
            //MenuList(menuItems)
            AceItemCollapsed(oneItem = aceItems.get(0))
        }
    }
}

@Composable
fun AceItemCollapsed(oneItem: AceItem) {
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(Color.LightGray)
            .border(1.dp, Color.Black)
        //.clickable {menuItemClicked(name, context) }
    ) {
        Column(
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .width(330.dp)
            //TODO: replace explicit width with something dynamic
            //.fillMaxWidth()
        ) {
            Text(
                text = "$oneItem.name",
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(5.dp)
                //.fillMaxSize()
                //.fillMaxWidth()
                //TODO: Find a way to right adjust the >
            )
        }
        Column {
            Icon(
                imageVector = Icons.Default.KeyboardArrowRight,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(5.dp)
            )

        }
    }
}

@Composable
fun AceItemExpanded(oneItem: AceItem) {

}

fun populateCategories(arrayList: ArrayList<String>) {
    //TODO: use this subroutine to fetch JSON from gist
    arrayList.add("Appetizers")
    arrayList.add("Entrees")
    arrayList.add("Sides")
    arrayList.add("Desserts")
}

@Preview(showBackground = true)
@Composable
fun CalorieCounterPreview() {
    AceRestaurantMenuComposeTheme {
        CalorieCounterScreen()
    }
}

fun populateAceItems(arrayList: ArrayList<AceItem>) {
    arrayList.add(
        AceItem(
            "item name",
            "appetizers",
            100,
            "a link",
            2.00,
            "a description"
        )
    )
}

data class AceItem(
    val name: String,
    val category: String,
    val calories: Int,
    val picture: String,
    val price: Double,
    val description: String
)
