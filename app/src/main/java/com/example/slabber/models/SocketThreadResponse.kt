package com.example.slabber.models

data class SocketThreadResponse(
    val code: Int,
    val message: String?,
    val thread: Thread?
)