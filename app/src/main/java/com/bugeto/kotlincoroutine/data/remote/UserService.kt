package com.bugeto.kotlincoroutine.data.remote

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class UserService {

    fun getUsersService(): UserApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserApi::class.java)
    }

    companion object {
        const val BASE_URL = "https://reqres.in/api/"
    }

}