package com.jda.randomuser.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TimezoneDTO(
    @SerializedName("description") val description: String,
    @SerializedName("offset") val offset: String
)
