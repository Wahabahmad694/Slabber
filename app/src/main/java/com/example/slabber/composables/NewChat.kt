package com.example.slabber.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.slabber.R
import com.example.slabber.models.User
import com.example.slabber.screens.Screen
import com.example.slabber.viewModels.AuthViewModel

@Composable
fun NewChat(navController: NavController) {
    val chatViewModel = AuthViewModel()
    Surface {
        NewChat(newChatList = chatViewModel.allUserResponse, navController = navController)
        chatViewModel.allUsers()
    }
}

//********** List View Item  ********* //
@Composable
fun NewChatItem(user: User) {
    Card(
        modifier = Modifier
            .background(Color.Black)
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .height(80.dp), shape = RoundedCornerShape(8.dp), elevation = 4.dp

    ) {
        Surface {
            Row(
                Modifier
                    .background(Color.Black)
                    .padding(4.dp)
                    .fillMaxSize()
            ) {

                Image(
                    painter = rememberImagePainter(
                        data = "",
                        builder = {
                            scale(coil.size.Scale.FIT)
                            placeholder(R.drawable.bg_placeholder_user)
                            transformations(CircleCropTransformation())

                        }
                    ),
                    contentDescription = "",
                    modifier = Modifier
                        .fillMaxHeight()
                        .weight(0.2f)
                )


                Column(
                    verticalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(start = 8.dp)
                        .fillMaxHeight()
                        .weight(0.8f)
                ) {
                    Text(
                        text = user.name,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = user.email,
                        style = MaterialTheme.typography.body1,
                        maxLines = 1,
                        color = Color.White
                    )
                }
            }
        }
    }
    //********** Line Separator  ********* //
    Divider(
        color = Color.LightGray,
        thickness = 1.dp,
        modifier = Modifier.padding(horizontal = 8.dp)
    )
}
// endregion list view item

@Composable
fun NewChat(newChatList: List<User>, navController: NavController) {
    Scaffold(backgroundColor = Color.LightGray) {
        Column(
            Modifier
                .padding(top = 20.dp, start = 8.dp, end = 8.dp)
                .fillMaxSize()
        ) {
            Row(
                Modifier
                    .fillMaxWidth()
            ) {
                Text(
                    text = "New Chat",
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 32.sp,
                    textAlign = TextAlign.Center,
                    fontFamily = FontFamily.Serif,
                    color = Color.Black,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(15.dp))
            LazyColumn {
                itemsIndexed(items = newChatList) { _, item ->
                    Surface(modifier = Modifier.clickable {
                        navController.navigate(Screen.ChatDetail.routes)
                    }) {
                        NewChatItem(user = item)

                    }
                }
            }
        }
    }
}
