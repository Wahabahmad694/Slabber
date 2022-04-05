package com.example.slabber.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.slabber.R
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

    val isFormValid by derivedStateOf {
        username.isNotBlank() && phoneNo.isNotBlank() && email.isNotBlank()
    }

    Scaffold(backgroundColor = MaterialTheme.colors.primary) {
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
                    .weight(0.75f),
                shape = RoundedCornerShape(5.dp)
            ) {
                Column(
                    Modifier
                        .fillMaxSize()
                        .padding(32.dp)
                ) {
                    Text(
                        text = "Create an Account",
                        fontWeight = FontWeight.Bold,
                        fontSize = 32.sp,
                        fontFamily = FontFamily.Serif,
                        textAlign = TextAlign.Justify
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

                        //  ********** Button for sign up ********  //
                        Button(
                            onClick = {
                                navController.navigate(Screen.ChatList.routes)
                            },
                            enabled = isFormValid,
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp)
                        ) {
                            Text(text = "Sign Up")
                        }
                    }
                }
            }
        }
    }
}
