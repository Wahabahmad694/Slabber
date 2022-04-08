package com.example.slabber.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.slabber.R
import com.example.slabber.data.DataHolder
import com.example.slabber.screens.Screen
import com.example.slabber.viewModels.AuthViewModel

@Composable
fun LoginScreen(navController: NavController, authViewModel: AuthViewModel = viewModel()) {

    //********** State Handling ********* //
    var email by remember {
        mutableStateOf("")
    }
    var username by remember {
        mutableStateOf("")
    }
    val isFormValid by derivedStateOf {
        email.isNotBlank() && username.isNotBlank()
    }

    authViewModel.loginResponse?.let {
        DataHolder.to = it
        authViewModel.loginResponse = null
        navController.navigate(Screen.ChatList.routes) {
            popUpTo(Screen.LoginScreen.routes) {
                inclusive = true
            }
        }
    }

    //********** Main Layout ********* //
    Scaffold(backgroundColor = Color.LightGray) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_chat),
                contentDescription = "App Logo",
                modifier = Modifier
                    .weight(0.60f)
                    .size(200.dp),
            )
            //********** Card View  ********* //
            Card(
                Modifier
                    .weight(.70f),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                ) {
                    Text(
                        text = "Welcome To Slabber",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 28.sp,
                        fontFamily = FontFamily.Serif,
                        textAlign = TextAlign.Justify,
                    )
                    Column(
                        Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        //********** User Name Edit text ********* //
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = username,
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = "personIcon"
                                )
                            },
                            onValueChange = { username = it },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.Red,
                                unfocusedBorderColor = Color.Blue
                            ),
                            label = { Text(text = "Name") },
                            trailingIcon = {
                                if (username.isNotBlank())
                                    IconButton(onClick = { username = "" }) {
                                        Icon(
                                            imageVector = Icons.Filled.Clear,
                                            contentDescription = ""
                                        )
                                    }
                            }
                        )
                        Spacer(modifier = Modifier.height(8.dp))

                        // Email Edit text ********* //
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = email,
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Email,
                                    contentDescription = "emailIcon"
                                )
                            },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.Red,
                                unfocusedBorderColor = Color.Blue
                            ),
                            onValueChange = { email = it },
                            label = { Text(text = "Email") },
                            trailingIcon = {
                                if (email.isNotBlank())
                                    IconButton(onClick = { email = "" }) {
                                        Icon(
                                            imageVector = Icons.Filled.Clear,
                                            contentDescription = ""
                                        )
                                    }
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Email,
                                imeAction = ImeAction.Done
                            ),
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        Row() {
                            //********** SignIn button ********* //
                            Button(
                                modifier = Modifier.fillMaxWidth(0.50f),
                                onClick = {
                                    authViewModel.login(email)
                                },
                                enabled = isFormValid,
                                shape = RoundedCornerShape(16.dp),
                                colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
                            ) {
                                Text(
                                    text = "Log In",
                                    color = Color.White
                                )
                            }
                            Spacer(modifier = Modifier.width(5.dp))

                            //********** SignUp Button ********* //
                            Button(
                                modifier = Modifier.fillMaxWidth(1f),
                                onClick = {
                                    navController.navigate(Screen.SignUp.routes)
                                },
                                shape = RoundedCornerShape(16.dp),
                                colors = ButtonDefaults.buttonColors(Color.Blue)
                            ) {
                                Text(
                                    text = "Sign Up",
                                    color = Color.White
                                )
                            }
                        }

                    }
                }
            }
        }
    }
}

