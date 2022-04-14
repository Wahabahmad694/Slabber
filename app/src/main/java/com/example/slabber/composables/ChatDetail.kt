package com.example.slabber.composables

import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Base64
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
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
import androidx.compose.material.icons.filled.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.slabber.ChatApp
import com.example.slabber.data.DataHolder
import com.example.slabber.models.*
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import org.json.JSONObject
import java.io.ByteArrayOutputStream

@Composable
fun ChatDetail() {
    var thread by remember { mutableStateOf<Thread?>(null) }
    val gson by remember { mutableStateOf(Gson()) }
    var socketInitialize by remember { mutableStateOf(false) }

    val socket = (LocalContext.current.applicationContext as ChatApp).socket
    socket.on("getOrCreateThread") {
        it.firstOrNull()?.let {
            val jsonParser = JsonParser()
            val gsonObject = jsonParser.parse(it.toString()) as JsonObject
            val jsonObject = gson.toJson(gsonObject)
            val threadResponse = gson.fromJson(jsonObject, SocketThreadResponse::class.java)
            if (threadResponse.code != 200) {
                Log.d("socketTesting", threadResponse.message ?: "No message for error")
            } else {
                thread = threadResponse.thread!!
            }
        }
    }

    thread?._id?.let {
        socket.on(it) {
            it.firstOrNull()?.let {
                val jsonParser = JsonParser()
                val gsonObject = jsonParser.parse(it.toString()) as JsonObject
                val jsonObject = gson.toJson(gsonObject)
                val threadResponse = gson.fromJson(jsonObject, SocketThreadResponse::class.java)
                if (threadResponse.code != 200) {
                    Log.d("socketTesting", threadResponse.message ?: "No message for error")
                } else {
                    thread = threadResponse.thread!!
                }
            }
        }
    }

    MessagesList(thread) {
        val threadId = thread?._id ?: return@MessagesList
        val chat = Chat(null, it, DataHolder.to!!, "TEXT", null)
        val messageReq = SendMessageReq(threadId, chat)
        socket.emit("newMessage", JSONObject(gson.toJson(messageReq).toString()))
    }


    if (!socketInitialize) {
        socket.connect()
        val getOrCreateThreadResponse = GetOrCreateThreadReq(DataHolder.chatUsers!!)
        socket.emit(
            "getOrCreateThread",
            JSONObject(gson.toJson(getOrCreateThreadResponse).toString())
        )

        socketInitialize = true
    }
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
            Icon(
                Icons.Filled.Done,
                "MessageStatus",
                tint = Color.Gray
            )
        }
    }
}


@Composable
fun MessagesList(thread: Thread?, sendNewMessage: (msg: String) -> Unit) {

    var message by remember {
        mutableStateOf("")
    }

    val context = LocalContext.current
    val bitmap = remember {
        mutableStateOf<Bitmap?>(null)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "User Name")
                },
                navigationIcon = {
                    IconButton(onClick = {}) {
                        Icon(Icons.Filled.ArrowBack, "backIcon")
                    }
                },
                backgroundColor = Color.Blue,
                contentColor = Color.White,
                elevation = 10.dp
            )
        }) {
        Column {

            var imageUri by remember {
                mutableStateOf<Uri?>(null)
            }

            val launcher = rememberLauncherForActivityResult(
                contract =
                ActivityResultContracts.GetContent()
            ) { uri: Uri? ->
                imageUri = uri
            }

            imageUri?.let {
                if (Build.VERSION.SDK_INT < 28) {
                    bitmap.value = MediaStore.Images
                        .Media.getBitmap(context.contentResolver, it)

                } else {
                    val source = ImageDecoder
                        .createSource(context.contentResolver, it)
                    bitmap.value = ImageDecoder.decodeBitmap(source)
                    val stringBitmap = encodeImage(bitmap.value!!)
                    Log.d("Hello", stringBitmap!!)

                }
            }

            LazyColumn(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxWidth()
                    .background(Color.LightGray)
            ) {
                itemsIndexed(
                    items = thread?.chat ?: listOf()
                ) { _, item ->
                    MessageCard(msg = item.message, item.sender._id == DataHolder.to!!._id)
                }
            }

            Spacer(modifier = Modifier.height(5.dp))
            Row {

                IconButton(modifier = Modifier
                    .align(CenterVertically)
                    .then(Modifier.size(35.dp)),
                    onClick = {
                        launcher.launch("image/*")
                    }
                ) {
                    Icon(
                        Icons.Filled.Add,
                        "Add image",
                        tint = Color.Black
                    )
                }
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
                    onClick = {
                        sendNewMessage(message)
                        message = ""
                    }
                ) {
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

private fun encodeImage(bm: Bitmap): String? {
    val byteStream = ByteArrayOutputStream()
    bm.compress(Bitmap.CompressFormat.JPEG, 100, byteStream)
    val byteArray = byteStream.toByteArray()
    return Base64.encodeToString(byteArray, Base64.DEFAULT)
}
