package com.example.slabber.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.slabber.composables.LoginScreen
import com.example.slabber.composables.SignUpScreen
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
    }
}