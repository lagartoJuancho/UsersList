package com.jda.randomuser.data.remote.dto

import com.google.gson.annotations.SerializedName

data class RegisteredDTO(
    @SerializedName("age") val age: Int,
    @SerializedName("date") val date: String
)
