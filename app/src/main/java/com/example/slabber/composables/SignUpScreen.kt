package com.example.slabber.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.slabber.screens.Screen

@Composable
fun SignUpScreen(navController: NavController) {

    //********** State Handling ********* //
    var username by remember {
        mutableStateOf("")
    }
    var phoneNo by remember {
        mutableStateOf("")
    }
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var confirmPassword by remember {
        mutableStateOf("")
    }
    val isFormValid by derivedStateOf {
        username.isNotBlank() && phoneNo.isNotBlank() && email.isNotBlank() && password.length >= 6 && password == confirmPassword
    }

    Scaffold(backgroundColor = MaterialTheme.colors.primary) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.weight(0.25f))
            Card(
                Modifier
                    .weight(2f)
                    .padding(8.dp),
                shape = RoundedCornerShape(32.dp)
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                ) {
                    Text(
                        text = "Create an Account",
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp
                    )
                    Spacer(modifier = Modifier.padding(20.dp))
                    Column(
                        Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        //  ********** Edit text for user name ********  //
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = username,
                            onValueChange = { username = it },
                            label = { Text(text = "User Name") },
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

                        //  ********** Edit text for phone Number ********  //
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = phoneNo,
                            onValueChange = { phoneNo = it },
                            label = { Text(text = "Phone Number") },
                            trailingIcon = {
                                if (phoneNo.isNotBlank())
                                    IconButton(onClick = { phoneNo = "" }) {
                                        Icon(
                                            imageVector = Icons.Filled.Clear,
                                            contentDescription = ""
                                        )
                                    }
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Number,
                                imeAction = ImeAction.Done
                            ),
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        //  ********** Edit text for email password ********  //
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = email,
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
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Done
                            ),
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        //  ********** Edit text for  password ********  //
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = password,
                            onValueChange = { password = it },
                            label = { Text(text = "Password") },
                            trailingIcon = {
                                if (password.isNotBlank())
                                    IconButton(onClick = { password = "" }) {
                                        Icon(
                                            imageVector = Icons.Filled.Clear,
                                            contentDescription = ""
                                        )
                                    }
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Done
                            ),
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        //  ********** Edit text for confirm password ********  //
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = confirmPassword,
                            onValueChange = { confirmPassword = it },
                            label = { Text(text = "Confirm Password") },
                            trailingIcon = {
                                if (confirmPassword.isNotBlank())
                                    IconButton(onClick = { password = "" }) {
                                        Icon(
                                            imageVector = Icons.Filled.Clear,
                                            contentDescription = ""
                                        )
                                    }
                            },
                            keyboardOptions = KeyboardOptions(
                                keyboardType = KeyboardType.Password,
                                imeAction = ImeAction.Done
                            ),
                        )
                        Spacer(modifier = Modifier.padding(25.dp))

                        //  ********** Button for sign up ********  //
                        Button(
                            onClick = {
                                navController.navigate(Screen.LoginScreen.routes)
                            },
                            enabled = isFormValid,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Text(text = "Sign Up")
                        }
                        Spacer(modifier = Modifier.weight(0.75f))
                    }
                }
            }
        }
    }
}
