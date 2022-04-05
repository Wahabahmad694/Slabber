package com.example.slabber.screens

sealed class Screen(val routes: String){
    object LoginScreen : Screen("login_screen")
    object SignUp : Screen("signup_screen")
    object ChatList : Screen("chatList_screen")
    object NewChat : Screen("new_chat_screen")
}
