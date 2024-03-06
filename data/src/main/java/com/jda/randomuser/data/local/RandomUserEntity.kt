package com.jda.randomuser.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jda.randomuser.data.utils.RANDOM_USER_TABLE
import com.jda.randomuser.domain.model.RandomUser
import com.jda.randomuser.domain.model.UserLocation

@Entity(tableName = RANDOM_USER_TABLE)
data class RandomUserEntity(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val displayName: String,
    val email: String,
    val gender: String,
    val dateJoined: String,
    val phone: String,
    val latitude: String,
    val longitude: String,
    val photo: String,
)

fun RandomUserEntity.asDomainModel() =
    RandomUser(
        id = email,
        displayName = displayName,
        email = email,
        gender = gender,
        dateJoined = dateJoined,
        phone = phone,
        location = UserLocation(latitude = latitude, longitude = longitude),
        photo = photo
    )
