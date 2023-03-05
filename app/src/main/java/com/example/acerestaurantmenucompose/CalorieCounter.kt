package com.example.acerestaurantmenucompose

import android.content.Context
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.acerestaurantmenucompose.ui.theme.AceRestaurantMenuComposeTheme

@Composable
fun CalorieCounterScreen(navController: NavController) {
    var categories = ArrayList<String>()
    populateCategories(categories)

    var aceItems = ArrayList<AceItem>()
    populateAceItems(aceItems)

    var totalCalorieCount by remember {
        mutableStateOf(sumCalories(aceItems))
    }

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
                    .clickable {
                        sumCalories(aceItems)
                    }
                //.padding(20.dp)
            )
        }
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
                .fillMaxWidth()
            //.border(1.dp, color = Color.Black)
        ) {
            AceItemList(items = aceItems)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(70.dp)
                .padding(10.dp)
        ) {
            Text(
                text = "Total Calories",
                modifier = Modifier
                    .align(Alignment.End)
                //.size(30.dp)
            )
            Text(
                text = totalCalorieCount.toString(),
                modifier = Modifier
                    .align(Alignment.End)
            )
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
                .width(270.dp)
            //TODO: replace explicit width with something dynamic
            //.fillMaxWidth()
        ) {
            Text(
                text = oneItem.name,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(5.dp)
            )
        }
        Column {
            Text(
                text = "-",
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(5.dp)
                    .clickable {
                        oneItem.quantity = maxOf((oneItem.quantity - 1), 0)
                        println("Decrement " + oneItem.name + " to " + oneItem.quantity)
                    }
            )
//            Icon(
//                //TODO: Replace with minus icon if available
//                imageVector = Icons.Default.Close,
//                contentDescription = null,
//                modifier = Modifier
//                    .align(Alignment.End)
//                    .padding(5.dp)
//            )
        }
        Column {
            Text(
                text = oneItem.quantity.toString(),
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(5.dp)
            )
        }
        Column {
            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(5.dp)
                    .clickable {
                        oneItem.quantity += 1
                        println("Increment " + oneItem.name + " to " + oneItem.quantity)
                    }
            )
        }
    }
}

@Composable
fun AceItemExpanded(oneItem: AceItem) {
    val context = LocalContext.current
    //val itemName = oneItem.name
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(Color.LightGray)
            .border(1.dp, Color.Black)
            .height(200.dp)
    ) {
        Row(
            modifier = Modifier
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .width(270.dp)
                //TODO: replace explicit width with something dynamic
                //.fillMaxWidth()
            ) {
                Text(
                    //TODO: Change this to not require another explicit variable declaration
                    text = oneItem.name,
                    modifier = Modifier
                        .align(Alignment.Start)
                        .padding(5.dp)
                    //.fillMaxSize()
                    //.fillMaxWidth()
                    //TODO: Find a way to right adjust the >
                )
            }
            Column {
                Text(
                    text = "-",
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(5.dp)
                        .clickable {
                            oneItem.quantity = maxOf((oneItem.quantity - 1), 0)
                            println("Decrement " + oneItem.name + " to " + oneItem.quantity)
                        }
                )
            }
            Column {
                Text(
                    text = oneItem.quantity.toString(),
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(5.dp)
                )
            }
            Column {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    modifier = Modifier
                        .align(Alignment.End)
                        .padding(5.dp)
                        .clickable {
                            oneItem.quantity += 1
                            println("Increment " + oneItem.name + " to " + oneItem.quantity)
                        }
                )
            }
        }
        Row(
            modifier = Modifier
                .offset(0.dp, 50.dp)
        ){
            Column(){
                Text(
                    text = oneItem.description
                )
            }
        }
    }
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
        val navController = rememberNavController()
        CalorieCounterScreen(navController)
    }
}

@Composable
fun AceItemList(items: List<AceItem>) {
    Column {
        items.forEach { item ->
            AceItemExpanded(oneItem = item)
        }
    }
}

fun populateAceItems(arrayList: ArrayList<AceItem>) {
    arrayList.add(
        AceItem(
            "Mozzarella Sticks",
            "Appetizers",
            850,
            "https://gist.github.com/JakeHennett/18d375fb14faaf9a000c1410ed4e8857?permalink_comment_id=4473116#gistcomment-4473116",
            9.99,
            "Six sticks, filled with gooey mozzarella cheese.",
            3
        )
    )
    arrayList.add(
        AceItem(
            "Spinach Dip",
            "Appetizers",
            500,
            "",
            8.99,
            "Spinach and artichoke dip, served with pita slices.",
            0
        )
    )
    arrayList.add(
        AceItem(
            "Fried Pickles",
            "Appetizers",
            550,
            "",
            9.00,
            "Dill chips, battered and fried.",
            0
        )
    )
    arrayList.add(
        AceItem(
            "Chips and Salsa",
            "Appetizers",
            300,
            "",
            6.50,
            "Salty tortilla chips and house-made tomato salsa.",
            0
        )
    )
    arrayList.add(
        AceItem(
            "Buffalo Blasts",
            "Appetizers",
            600,
            "",
            10.99,
            "Spicy chicken in a fried skin. Served with blue cheese and buffalo sauce.",
            0
        )
    )
    arrayList.add(
        AceItem(
            "Pepperoni Pizza",
            "Entrees",
            900,
            "",
            10.00,
            "Deep dish Detroit style pizza covered in pepperonis and cheese",
            0
        )
    )
    arrayList.add(
        AceItem(
            "Hamburger with Fries",
            "Entrees",
            815,
            "",
            8.50,
            "hamburger with lettuce, tomato, and pickles served with fries",
            0
        )
    )
    arrayList.add(
        AceItem(
            "Chicken sandwich with fries",
            "Entrees",
            650,
            "",
            7.50,
            "Crispy fried chicken sandwich served with fries",
            0
        )
    )
    arrayList.add(
        AceItem(
            "Steak and potatoes",
            "Entrees",
            450,
            "",
            10.25,
            "Steak served just the way you want it. This comes with a baked potato. ",
            0
        )
    )
    arrayList.add(
        AceItem(
            "Grilled Tenders with fries",
            "Entrees",
            250,
            "",
            6.50,
            "marinated and grilled chicken tenders served with fries",
            0
        )
    )
    arrayList.add(
        AceItem(
            "French Fries",
            "Sides",
            80,
            "",
            2.00,
            "Thin cut fries, sprinkled with the signature Ace spice mix.",
            0
        )
    )
    arrayList.add(AceItem("Fruit Cup", "Sides", 50, "", 3.00, "seasonal fruit", 0))
    arrayList.add(
        AceItem(
            "Baked Potato",
            "Sides",
            500,
            "",
            3.50,
            "A gigantic Idaho spud, baked and filled with butter, sour cream, cheese, and bacon.",
            0
        )
    )
    arrayList.add(
        AceItem(
            "Broccoli Casserole",
            "Sides",
            300,
            "",
            4.50,
            "Mama's original recipe. Cheesy in the best way.",
            0
        )
    )
    arrayList.add(
        AceItem(
            "Onion Rings",
            "Sides",
            350,
            "",
            3.00,
            "Thick cut onion rings, battered and deep fried.",
            0
        )
    )
    arrayList.add(
        AceItem(
            "Carrot Cake",
            "Desserts",
            350,
            "",
            5.00,
            "It sounds healthy, but it probably isn't.",
            0
        )
    )
    arrayList.add(
        AceItem(
            "Fried Oreos",
            "Desserts",
            700,
            "",
            1.00,
            "Two Oreo cookies, battered and deep fried until golden brown and delicious.",
            0
        )
    )
    arrayList.add(
        AceItem(
            "French Silk Pie",
            "Desserts",
            900,
            "",
            5.50,
            "Rich and creamy, but not made of silk nor is it from France.",
            0
        )
    )
    arrayList.add(
        AceItem(
            "Ice Cream",
            "Desserts",
            650,
            "",
            3.00,
            "A heaping bowl of ice cream. Vanilla, chocolate, and strawberry available.",
            0
        )
    )
    arrayList.add(
        AceItem(
            "Cheesecake",
            "Desserts",
            800,
            "",
            4.00,
            "A decadent slice of cheesecake, garnished with strawberry slices.",
            0
        )
    )
}

data class AceItem(
    val name: String,
    val category: String,
    val calories: Int,
    val picture: String,
    val price: Double,
    val description: String,
    var quantity: Int
)

fun sumCalories(arrayList: ArrayList<AceItem>): Int {
    var total = 0

    for (i in arrayList) {
        total += (i.calories * i.quantity)
        println(
            " calories: " + i.calories +
                    " quantity: " + i.quantity +
                    " total: " + total
        )
    }

    return total
//    val myList = listOf(
//        Activity("A", hashMapOf("day_5" to 70, "day_4" to 70)),
//        Activity("B", hashMapOf("day_5" to 40, "day_4" to 80)),
//    )
//
//    for (i in myList) {
//        val total = i.prize.values.sum()
//        i.prize.clear()
//        i.prize["total"] = total
//    }
}