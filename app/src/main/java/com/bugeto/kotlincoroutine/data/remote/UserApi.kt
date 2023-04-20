package com.bugeto.kotlincoroutine.data.remote

import com.bugeto.kotlincoroutine.data.UserList
import retrofit2.Response
import retrofit2.http.GET

interface UserApi {

    @GET("users")
    suspend fun getUsers(): Response<UserList>
}