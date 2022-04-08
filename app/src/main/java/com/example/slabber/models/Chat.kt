package com.example.slabber.models

data class Chat(
    val _id: String?,
    val message: String,
    val sender: User,
    val mimiType: String, // value must be 'TEXT', "IMAGE"
    val time: String? //ISO Fromat
)