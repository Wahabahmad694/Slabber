package com.example.slabber.viewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.slabber.`interface`.ApiServices
import com.example.slabber.models.User
import kotlinx.coroutines.launch

class ChatListViewModel : ViewModel() {
     var chatListResponse: List<User> by mutableStateOf(listOf())
     private var errorMessage: String by mutableStateOf("")

    fun getChatList() {
        viewModelScope.launch {
            val apiService = ApiServices.getInstance()
            try {
                val chatList = apiService.getChat()
                chatListResponse = chatList
            } catch (e: Exception) {
                errorMessage = e.message.toString()
            }
        }
    }
}