package com.example.slabber.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.slabber.`interface`.ApiServices
import com.example.slabber.models.Thread
import com.example.slabber.models.User
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    var signupResponse: User? by mutableStateOf(null)
    var loginResponse: User? by mutableStateOf(null)
    var allUserResponse: List<User> by mutableStateOf(listOf())
    var allThreadResponse: List<Thread> by mutableStateOf(listOf())
    private var errorMessage: String by mutableStateOf("")

    fun signup(userReq: User) {
        viewModelScope.launch {
            val apiService = ApiServices.getInstance()
            try {
                val user = apiService.signup(userReq)
                signupResponse = user
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    fun login(userId: String) {
        viewModelScope.launch {
            val apiService = ApiServices.getInstance()
            try {
                val user = apiService.login(userId)
                loginResponse = user
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    fun allUsers() {
        viewModelScope.launch {
            val apiService = ApiServices.getInstance()
            try {
                val users = apiService.allUsers()
                allUserResponse = users
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }

    fun allThreads(userId: String) {
        viewModelScope.launch {
            val apiService = ApiServices.getInstance()
            try {
                val threads = apiService.allThreads(userId)
                allThreadResponse = threads
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}