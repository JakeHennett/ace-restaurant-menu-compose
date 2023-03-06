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
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.rememberNavController
import com.example.acerestaurantmenucompose.ui.theme.AceRestaurantMenuComposeTheme
//TODO: All of the below
/*
Add editable int field for total calories
Replace x bottom on calories with -
Build full detail menu item composable (expanded)
Route individual buttons on main screen differently. Debug to details view
 */

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
                    Navigation()
                }
            }
        }
    }
}

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = Screen.MainScreen.route) {
        composable(route = Screen.MainScreen.route){
            MainMenu(navController = navController)
        }
        composable(route = Screen.DetailScreen.route){
            DetailScreen()
        }
        composable(route = Screen.CalorieCounterScreen.route){
            CalorieCounterScreen(navController = navController)
        }
    }
}

@Composable
fun MainMenu(navController: NavController)
{
    val context = LocalContext.current
//    val navController = rememberNavController()
//    NavHost(navController, startDestination = "Calorie Counter") {
//        composable(route = "Calorie Counter") {
//            CalorieCounterScreen()
//            //TODO: Reference Calorie Counter Screen
//        }
//    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Magenta)
    )
    {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ){
            Text(
                text = "Ace Restaurant",
                //TODO: Remove the mutableString here after adding it for total calories
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .clickable {
                        println(navController.findDestination(Screen.DetailScreen.route).toString())
                        Toast
                            .makeText(
                                context,
                                navController
                                    .findDestination(Screen.DetailScreen.route)
                                    .toString(),
                                Toast.LENGTH_SHORT
                            )
                            .show()
                        navController.navigate(Screen.DetailScreen.route)
                    }
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ){
            val menuItems = ArrayList<String>()
            populateMenuItems(menuItems)
            MenuList(menuItems, navController)
        }
    }
}

@Composable
fun MenuList(messages: List<String>, navController: NavController) {
    Column {
        messages.forEach { message ->
            MainMenuItem(message, navController)
        }
    }
}

@Composable
fun MainMenuItem(name: String, navController: NavController)
{
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(Color.LightGray)
            .border(1.dp, Color.Black)
            .clickable {
                menuItemClicked(name, context, navController)
            }
    ){
        Column (
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .width(330.dp)
            //TODO: replace explicit width with something dynamic
                ){
            Text(
                text = name,
                modifier = Modifier
                    .align(Alignment.Start)
                    .padding(5.dp)
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AceRestaurantMenuComposeTheme {
        Navigation()
    }
}

fun populateMenuItems(arrayList: ArrayList<String>)
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
    arrayList.add("Debug")
}

fun menuItemClicked(name: String, context: Context, navController: NavController)
{
    Toast.makeText(context, name, Toast.LENGTH_SHORT).show()
    navController.navigate(Screen.CalorieCounterScreen.route){
        popUpTo(Screen.MainScreen.route)
    } //TODO: Test to see if popUpTo is necessary
}

// From from https://medium.com/google-developer-experts/navigating-in-jetpack-compose-78c78d365c6a
// From https://cs.android.com/androidx/platform/frameworks/support/+/androidx-main:navigation/navigation-compose/src/main/java/androidx/navigation/compose/NavGraphBuilder.kt
fun NavGraphBuilder.composable(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = emptyList(),
    content: @Composable (NavBackStackEntry) -> Unit
) {
    addDestination(
        ComposeNavigator.Destination(provider[ComposeNavigator::class], content).apply {
            this.route = route
            arguments.forEach { (argumentName, argument) ->
                addArgument(argumentName, argument)
            }
            deepLinks.forEach { deepLink ->
                addDeepLink(deepLink)
            }
        }
    )
}

@Composable
fun FeedScreen(navController: NavController) {
    Button(onClick = { navController.navigate("adopt") }) {
        Text("Click me to adopt!")
    }
} //TODO: Remove this button after completing UI design. (reference)

@Composable
fun DetailScreen(){
    Text(
        text = "Another Screen"
    )
}