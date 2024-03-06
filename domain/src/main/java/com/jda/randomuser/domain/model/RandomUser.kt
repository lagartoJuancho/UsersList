package com.jda.randomuser.domain.model

data class RandomUser(
    val id: String,
    val displayName: String,
    val email: String,
    val gender: String,
    val dateJoined: String,
    val phone: String,
    val location: UserLocation,
    val photo: String,
)
