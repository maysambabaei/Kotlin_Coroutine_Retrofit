package com.bugeto.kotlincoroutine.ui

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bugeto.kotlincoroutine.data.User
import com.bugeto.kotlincoroutine.data.remote.UserService
import kotlinx.coroutines.*

class ListViewModel : ViewModel() {
    val userService = UserService().getUsersService()
    var job: Job? = null
    val exceptionHandler = CoroutineExceptionHandler { coroutineContext, throwable ->
        error("Exception handled: ${throwable.localizedMessage}")
    }
    val users = MutableLiveData<List<User>>()
    val userLoadError = MutableLiveData<String?>()
    val loading = MutableLiveData<Boolean>()

    private fun fetchUsers() {
        loading.value = true
        job = CoroutineScope(Dispatchers.IO + exceptionHandler).launch {
            val response = userService.getUsers()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    users.value = response.body()?.data
                    userLoadError.value = null
                    loading.value = false
                } else {
                    error("Error : ${response.message()}")
                }
            }
        }
        userLoadError.value = ""
        loading.value = false
    }

    fun refresh() {
        fetchUsers()
    }

    private fun onError(message: String) {
        userLoadError.value = message
        loading.value = false
    }

    override fun onCleared() {
        super.onCleared()
        job?.cancel()
    }

}