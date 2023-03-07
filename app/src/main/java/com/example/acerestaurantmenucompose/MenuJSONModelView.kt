package com.example.acerestaurantmenucompose

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class menuJSONViewModel: ViewModel(){
    var data by mutableStateOf("")
        private set //only viewmodel can modify values

    fun onLoadData(){
        data = "json extraction logic"
    }

    fun onDataChange(newData: String){
        data = newData
    }

}