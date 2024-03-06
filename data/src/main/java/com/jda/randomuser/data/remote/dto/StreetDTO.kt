package com.jda.randomuser.data.remote.dto

import com.google.gson.annotations.SerializedName

data class StreetDTO(
    @SerializedName("name") val name: String,
    @SerializedName("number") val number: Int
)
