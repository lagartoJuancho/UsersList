package com.jda.randomuser.data.remote.dto

import com.google.gson.annotations.SerializedName

data class IdDTO(
    @SerializedName("name") val name: String? = null,
    @SerializedName("value") val value: String? = null
)
