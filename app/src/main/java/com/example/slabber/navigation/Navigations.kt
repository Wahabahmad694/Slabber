package com.example.slabber.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.slabber.composables.*
import com.example.slabber.screens.Screen

@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.LoginScreen.routes) {
        composable(route = Screen.LoginScreen.routes) {
            LoginScreen(navController = navController)
        }
        composable(route = Screen.SignUp.routes)
        {
            SignUpScreen(navController = navController)
        }
        composable(route = Screen.ChatList.routes)
        {
            ChatList(navController = navController)
        }
        composable(route = Screen.NewChat.routes)
        {
            NewChat(navController = navController)
        }
        composable(route = Screen.ChatDetail.routes)
        {
            ChatDetail()
        }

    }
}