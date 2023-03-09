package com.example.acerestaurantmenucompose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.ViewModel
import java.net.URL

class menuJSONViewModel: ViewModel(){
    var data by mutableStateOf("")
        private set //only viewmodel can modify values

    init {
        val apiResponse = URL("https://gist.githubusercontent.com/JakeHennett/18d375fb14faaf9a000c1410ed4e8857/raw/269160dc6491c8769e995a8fd455e5ac292f3ffd/menu.json").readText()
        data = apiResponse
    }

    fun onLoadData(){
        val apiResponse = URL("https://gist.githubusercontent.com/JakeHennett/18d375fb14faaf9a000c1410ed4e8857/raw/269160dc6491c8769e995a8fd455e5ac292f3ffd/menu.json").readText()
        data = apiResponse
    }

    fun onDataChange(newData: String){
        data = newData
    }

}

//Example Use
//class mActiviry: AppCompatActivity(){
//    val vm by viewmodels<mViewModel>() //See docs for better approaches of initialisation
//
////...
//    setContent {
//        Text(vm.data)
//    }
//}