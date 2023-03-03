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
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.rememberNavController
import androidx.navigation.get
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
                }
            }
        }
    }
}

@Composable
fun MainMenu()
{
//    val navController = rememberNavController()
//    NavHost(navController, startDestination = "feed") {
//        composable(route = "feed") {
//            //FeedScreen()
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
                modifier = Modifier
                    .align(Alignment.TopCenter)
                //.padding(20.dp)
            )
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ){
            val menuItems = ArrayList<String>()
            populateMenuItems(menuItems)
            MenuList(menuItems)
        }
    }
}

@Composable
fun MenuList(messages: List<String>) {
    Column {
        messages.forEach {
                message ->
            MainMenuItem(message)
        }
    }
}

@Composable
fun MainMenuItem(name: String)
{
    val context = LocalContext.current
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(Color.LightGray)
            .border(1.dp, Color.Black)
            .clickable {
                menuItemClicked(name, context)
            }
    ){
        Column (
            modifier = Modifier
                .align(Alignment.CenterVertically)
                .width(330.dp)
            //TODO: replace explicit width with something dynamic
                //.fillMaxWidth()
                ){
            Text(
                text = name,
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

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    AceRestaurantMenuComposeTheme {
        MainMenu()
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

fun menuItemClicked(name: String, context: Context)
{
    Toast.makeText(context, name, Toast.LENGTH_SHORT).show()
    //CalorieCounter()
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