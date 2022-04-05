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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
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
                }
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

@Composable
fun ChatItem(user: User) {
    Card(
        modifier = Modifier
            .padding(8.dp, 4.dp)
            .fillMaxWidth()
            .height(110.dp), shape = RoundedCornerShape(8.dp), elevation = 4.dp
    ) {
        Surface {
            Row(
                Modifier
                    .padding(4.dp)
                    .fillMaxSize()
            ) {

                Image(
                    painter = rememberImagePainter(
                        data = user.imageUrl,
                        builder = {
                            scale(coil.size.Scale.FILL)
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
                        .padding(4.dp)
                        .fillMaxHeight()
                        .weight(0.8f)
                ) {
                    Text(
                        text = user.name,
                        style = MaterialTheme.typography.subtitle1,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = user.category,
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier
                            .background(
                                androidx.compose.ui.graphics.Color.LightGray
                            )
                            .padding(4.dp)
                    )
                    Text(
                        text = user.desc,
                        style = MaterialTheme.typography.body1,
                        maxLines = 2,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

@Composable
fun ChatList(chatList: List<User>) {
    Scaffold(backgroundColor = MaterialTheme.colors.primary) {
        LazyColumn {
            itemsIndexed(items = chatList) { _, item ->
                ChatItem(user = item)
            }
        }
    }
}
