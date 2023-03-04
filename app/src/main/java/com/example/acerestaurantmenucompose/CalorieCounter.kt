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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.acerestaurantmenucompose.ui.theme.AceRestaurantMenuComposeTheme

//class CalorieCounter : ComponentActivity() {
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        setContent {
//            AceRestaurantMenuComposeTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colors.background
//                ) {
//                    val navController = rememberNavController()
//                    CalorieCounterScreen(navController)
//                }
//            }
//        }
//    }
//}

@Composable
fun CalorieCounterScreen(navController: NavController) {
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
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
                .fillMaxWidth()
        ) {
            AceItemList(items = aceItems)
        }
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ){
            Text(
                text = "Total Calories",
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    //.size(30.dp)
            )
        }
    }
}

@Composable
fun AceItemCollapsed(oneItem: AceItem) {
    val context = LocalContext.current
    val itemName = oneItem.name
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
                //TODO: Change this to not require another explicit variable declaration
                text = "$itemName",
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
                //TODO: Replace with minus icon if available
                imageVector = Icons.Default.Close,
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.End)
                    .padding(5.dp)
            )
        }
        Column {
            Text(
                text = "0",
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
        val navController = rememberNavController()
        CalorieCounterScreen(navController)
    }
}

@Composable
fun AceItemList(items: List<AceItem>) {
    Column {
        items.forEach {
                item ->
            AceItemCollapsed(oneItem = item)
        }
    }
}

fun populateAceItems(arrayList: ArrayList<AceItem>) {
    arrayList.add(AceItem("Mozzarella Sticks", "Appetizers", 850, "https://gist.github.com/JakeHennett/18d375fb14faaf9a000c1410ed4e8857?permalink_comment_id=4473116#gistcomment-4473116", 9.99, "Six sticks, filled with gooey mozzarella cheese."))
    arrayList.add(AceItem("Spinach Dip", "Appetizers", 500, "", 8.99, "Spinach and artichoke dip, served with pita slices."))
    arrayList.add(AceItem("Fried Pickles", "Appetizers", 550, "", 9.00, "Dill chips, battered and fried."))
    arrayList.add(AceItem("Chips and Salsa", "Appetizers", 300, "", 6.50, "Salty tortilla chips and house-made tomato salsa."))
    arrayList.add(AceItem("Buffalo Blasts", "Appetizers", 600, "", 10.99, "Spicy chicken in a fried skin. Served with blue cheese and buffalo sauce."))
    arrayList.add(AceItem("Pepperoni Pizza", "Entrees", 900, "", 10.00, "Deep dish Detroit style pizza covered in pepperonis and cheese"))
    arrayList.add(AceItem("Hamburger with Fries", "Entrees", 815, "", 8.50, "hamburger with lettuce, tomato, and pickles served with fries"))
    arrayList.add(AceItem("Chicken sandwich with fries", "Entrees", 650, "", 7.50, "Crispy fried chicken sandwich served with fries"))
    arrayList.add(AceItem("Steak and potatoes", "Entrees", 450, "", 10.25, "Steak served just the way you want it. This comes with a baked potato. "))
    arrayList.add(AceItem("Grilled Tenders with fries", "Entrees", 250, "", 6.50, "marinated and grilled chicken tenders served with fries"))
    arrayList.add(AceItem("French Fries", "Sides", 80, "", 2.00, "Thin cut fries, sprinkled with the signature Ace spice mix."))
    arrayList.add(AceItem("Fruit Cup", "Sides", 50, "", 3.00, "seasonal fruit"))
    arrayList.add(AceItem("Baked Potato", "Sides", 500, "", 3.50, "A gigantic Idaho spud, baked and filled with butter, sour cream, cheese, and bacon."))
    arrayList.add(AceItem("Broccoli Casserole", "Sides", 300, "", 4.50, "Mama's original recipe. Cheesy in the best way."))
    arrayList.add(AceItem("Onion Rings", "Sides", 350, "", 3.00, "Thick cut onion rings, battered and deep fried."))
    arrayList.add(AceItem("Carrot Cake", "Desserts", 350, "", 5.00, "It sounds healthy, but it probably isn't."))
    arrayList.add(AceItem("Fried Oreos", "Desserts", 700, "", 1.00, "Two Oreo cookies, battered and deep fried until golden brown and delicious."))
    arrayList.add(AceItem("French Silk Pie", "Desserts", 900, "", 5.50, "Rich and creamy, but not made of silk nor is it from France."))
    arrayList.add(AceItem("Ice Cream", "Desserts", 650, "", 3.00, "A heaping bowl of ice cream. Vanilla, chocolate, and strawberry available."))
    arrayList.add(AceItem("Cheesecake", "Desserts", 800, "", 4.00, "A decadent slice of cheesecake, garnished with strawberry slices."))
}

data class AceItem(
    val name: String,
    val category: String,
    val calories: Int,
    val picture: String,
    val price: Double,
    val description: String
)
