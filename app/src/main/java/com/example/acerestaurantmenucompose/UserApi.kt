package com.example.acerestaurantmenucompose

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

public interface UserApi {
    @Headers(
        "Accept: application/json"
    )
    @GET("users/{id}")
    abstract fun getUserById(@Path("id") id: String): Call<UserModel?>?

    @GET("menu.json")
    abstract fun getMenuList(): Call<MenuModel?>?
}