package com.example.slabber.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
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
import com.example.slabber.models.User
import com.example.slabber.screens.Screen
import com.example.slabber.viewModels.AuthViewModel

@Composable
fun SignUpScreen(navController: NavController, authViewModel: AuthViewModel = viewModel()) {

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

    val isFormValid by derivedStateOf {
        username.isNotBlank() /*&& phoneNo.isNotBlank()*/ && email.isNotBlank()
    }

    authViewModel.signupResponse?.let {
        DataHolder.to = it
        authViewModel.signupResponse = null
        navController.navigate(Screen.ChatList.routes) {
            popUpTo(Screen.LoginScreen.routes) {
                inclusive = true
            }
        }
    }

    Scaffold(backgroundColor = Color.LightGray) {
        Column(
            Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Image(
                painter = painterResource(id = R.drawable.signup),
                contentDescription = "App Logo",
                modifier = Modifier
                    .weight(0.60f)
                    .size(200.dp),
            )
            Card(
                Modifier
                    .weight(1f),
                shape = RoundedCornerShape(8.dp)
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                ) {
                    Text(
                        text = "Create an Account",
                        fontWeight = FontWeight.Bold,
                        fontSize = 30.sp,
                        fontFamily = FontFamily.Serif,
                        textAlign = TextAlign.Justify
                    )
                    Column(
                        Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {

                        //  ********** Edit text for user name ********  //
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = username,
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Person,
                                    contentDescription = "personIcon"
                                )
                            },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.Red,
                                unfocusedBorderColor = Color.Blue
                            ),
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
                            leadingIcon = {
                                Icon(
                                    imageVector = Icons.Default.Phone,
                                    contentDescription = "phoneIcon"
                                )
                            },
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                focusedBorderColor = Color.Red,
                                unfocusedBorderColor = Color.Blue
                            ),
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
                                keyboardType = KeyboardType.Text,
                                imeAction = ImeAction.Done
                            ),
                        )
                        Spacer(modifier = Modifier.height(16.dp))

                        //  ********** Button for sign up ********  //
                        Button(
                            onClick = {
                                val user = User(null, email, username)
                                authViewModel.signup(user)
                            },
                            enabled = isFormValid,
                            modifier = Modifier
                                .fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(backgroundColor = Color.Red)
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
