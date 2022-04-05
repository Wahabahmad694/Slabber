package com.example.slabber.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.slabber.R
import com.example.slabber.models.User
import com.example.slabber.screens.Screen
import com.example.slabber.viewModels.ChatListViewModel

@Composable
fun ChatList(navController: NavController) {
    val chatViewModel = ChatListViewModel()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    navController.navigate(Screen.NewChat.routes)
                },
                backgroundColor = Color.Red,
                modifier = Modifier
                    .size(80.dp)
                    .padding(10.dp)
            ) {
                Icon(Icons.Filled.Add, "")
            }
        }
    ) {
        Surface {
            ChatList(chatList = chatViewModel.chatListResponse)
            chatViewModel.getChatList()
        }
    }

}

@ExperimentalCoilApi
@Composable
fun ChatItem(user: User) {
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
                        data = user.imageUrl,
                        builder = {
                            scale(coil.size.Scale.FIT)
                            placeholder(R.drawable.bg_placeholder_user)
                            transformations(CircleCropTransformation())

                        }
                    ),
                    contentDescription = user.desc,
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
                        text = user.category,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier
                            .background(
                                Color.LightGray
                            )
                            .padding(4.dp)
                    )
                    Text(
                        text = user.desc,
                        style = MaterialTheme.typography.body1,
                        maxLines = 1,
                        color = Color.White
                    )
                }
            }
        }
    }
    Divider(
        color = Color.LightGray,
        thickness = 1.dp,
        modifier = Modifier.padding(horizontal = 8.dp)
    )
}

@Composable
fun ChatList(chatList: List<User>) {
    Scaffold(backgroundColor = MaterialTheme.colors.onSecondary) {
        Column(
            Modifier
                .padding(top = 20.dp, start = 8.dp, end = 8.dp)
                .fillMaxSize()
        ) {
            Text(
                text = "Chats",
                fontWeight = FontWeight.ExtraBold,
                fontSize = 32.sp,
                fontFamily = FontFamily.Serif,
                textAlign = TextAlign.Justify,
                color = Color.White
            )

            Spacer(modifier = Modifier.height(15.dp))

            LazyColumn {
                itemsIndexed(items = chatList) { _, item ->
                    ChatItem(user = item)
                }
            }
        }
    }
}
