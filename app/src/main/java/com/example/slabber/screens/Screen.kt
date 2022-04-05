package com.example.slabber.screens

sealed class Screen(val routes: String){
    object LoginScreen : Screen("login_screen")
    object SignUp : Screen("signup_screen")
}
