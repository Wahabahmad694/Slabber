package com.example.slabber.composables

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@Composable
fun ChatDetail() {
    MessagesList()
}

@Composable
fun MessageCard(msg: String, ownMsg: Boolean) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(com.example.slabber.R.drawable.bg_placeholder_user),
            contentDescription = null,
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondaryVariant, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        val surfaceColor: Color by animateColorAsState(
            if (ownMsg) MaterialTheme.colors.primary else MaterialTheme.colors.surface,
        )
        Surface(
            shape = MaterialTheme.shapes.medium,
            elevation = 1.dp,
            color = surfaceColor,
        ) {
            Text(
                text = msg,
                modifier = Modifier.padding(all = 4.dp),
                style = MaterialTheme.typography.body2
            )
        }
    }
}


@Composable
fun MessagesList() {

    var message by remember {
        mutableStateOf("")
    }

    Scaffold {
        Column {
            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(Color.LightGray)
            ) {
                itemsIndexed(
                    items = listOf(
                        "Hi",
                        "Hello",
                        "How are you?",
                        "I am fine"
                    )
                ) { index, item ->
//                    MessagesListItem(item)
                    MessageCard(msg = item, ownMsg = index.rem(2) != 0)
                }
            }
            Spacer(modifier = Modifier.height(5.dp))
            Row {
                TextField(
                    value = message,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    ),
                    onValueChange = { message = it },
                    modifier = Modifier
                        .padding(start = 5.dp)
                        .background(Color.White)
                        .weight(1f)
                )
                IconButton(modifier = Modifier
                    .align(CenterVertically)
                    .then(Modifier.size(35.dp)),
                    onClick = { }) {
                    Icon(
                        Icons.Filled.Send,
                        "contentDescription",
                        tint = Color.Blue
                    )
                }
            }
        }
    }
}