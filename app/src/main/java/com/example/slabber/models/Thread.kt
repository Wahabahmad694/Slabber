package com.example.slabber.models

data class Thread(
    val _id: String?,
    val chat: List<Chat>,
    val users: List<String> // add user id's
)