package com.example.acerestaurantmenucompose

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.rememberNavController
import com.example.acerestaurantmenucompose.ui.theme.AceRestaurantMenuComposeTheme
import com.example.acerestaurantmenucompose.ui.theme.Purple700
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//TODO: All of the below
/*
Pull menu items from gist json
Rework - button on calorie counter to look closer to + button
Alternatively, import full icon list to support - icon
Add image resources for each menu item.
Properly display image on menu item expanded
Replace boxes with cards where appropriate
Utilize viewmodels to leverage modular design
Route individual buttons on main screen differently. Debug to details view

Useful Design links
https://paulallies.medium.com/jetpack-compose-api-data-to-list-view-35cb5ea66a95
https://dev.to/ethand91/android-jetpack-compose-api-tutorial-1kh5
 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        //TODO: Declare viewmodel object instance here
        //val vm = MyViewModel()
        super.onCreate(savedInstanceState)
        setContent {
            AceRestaurantMenuComposeTheme {
                // A surface container using the 'background' color from the theme
                //TODO: Convert main screen to accept a viewmodel parameter
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
        composable(route = Screen.ApiTestScreen.route){
            ApiTestScreen()
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

data class ProfileModel(
    var age: String,
    var name: String,
    var email: String,
)

data class UserModel(
    var profile: ProfileModel
)

data class MenuItemModel(
    val name: String,
    val category: String,
    val calories: Int,
    val picture: String,
    val price: Double,
    val description: String,
    var quantity: Int
)

data class MenuModel(
    var menuList: ArrayList<MenuItemModel>
)

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ApiTestScreen() {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Purple700,
                title = {
                    Text(
                        text = "Simple API Request",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                }
            )
        },
        content = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val id = remember {
                    mutableStateOf(TextFieldValue())
                }

                val profile = remember {
                    mutableStateOf(ProfileModel(
                        age = "",
                        name = "",
                        email = ""
                    ))
                }

                Text(
                    text="API Sample",
                    style= TextStyle(
                        fontSize = 40.sp,
                        fontFamily = FontFamily.Cursive
                    )
                )

                Spacer(modifier = Modifier.height(15.dp))

                TextField(
                    label = { Text(text = "User ID")},
                    value = id.value,
                    onValueChange = { id.value = it }
                )

                Spacer(modifier = Modifier.height(15.dp))

                Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                    Button(
                        onClick = {
                            val data = sendRequest(
                                id = id.value.text,
                                profileState = profile
                            )

                            Log.d("Main Activity", profile.toString())
                        }
                    ) {
                        Text(text = "Get Data")
                    }
                }

                Spacer(modifier = Modifier.height(15.dp))

                Text(text = profile.component1().toString(), fontSize = 40.sp)
            }
        }
    )
}

fun sendRequest(
    id: String,
    profileState: MutableState<ProfileModel>
) {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.0.109:3000")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(UserApi::class.java)

    val call: Call<UserModel?>? = api.getUserById(id);

    call!!.enqueue(object: Callback<UserModel?> {
        override fun onResponse(call: Call<UserModel?>, response: Response<UserModel?>) {
            if(response.isSuccessful) {
                Log.d("Main", "success!" + response.body().toString())
                profileState.value = response.body()!!.profile
            }
        }

        override fun onFailure(call: Call<UserModel?>, t: Throwable) {
            Log.e("Main", "Failed mate " + t.message.toString())
        }
    })
}

fun sendRequestAce(
    id: String,
    menuItemState: MutableState<MenuItemModel>
) {
    val retrofit = Retrofit.Builder()
        .baseUrl(R.string.menu_gist_url.toString())
        //TODO: Make sure this is the actual URL value. Also, maybe make a subcategory of URL under R.string
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(UserApi::class.java)

//    val call: Call<MenuModel?>? = api.getUserById(id);
//
//    call!!.enqueue(object: Callback<UserModel?> {
//        override fun onResponse(call: Call<UserModel?>, response: Response<UserModel?>) {
//            if(response.isSuccessful) {
//                Log.d("Main", "success!" + response.body().toString())
//                profileState.value = response.body()!!.profile
//            }
//        }
//
//        override fun onFailure(call: Call<UserModel?>, t: Throwable) {
//            Log.e("Main", "Failed mate " + t.message.toString())
//        }
//    })
}