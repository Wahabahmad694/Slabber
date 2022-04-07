package com.example.slabber

import android.app.Application
import io.socket.client.IO
import io.socket.client.Socket
import java.net.URISyntaxException

class ChatApp : Application() {
    val socket: Socket

    init {
        try {
            socket = IO.socket("ws://192.168.2.216:3000/")
        } catch (e: URISyntaxException) {
            throw RuntimeException(e)
        }
    }
}