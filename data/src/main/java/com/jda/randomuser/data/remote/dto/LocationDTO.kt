package com.jda.randomuser.data.remote.dto

import com.google.gson.annotations.SerializedName

data class LocationDTO(
    @SerializedName("city") val city: String,
    @SerializedName("coordinates") val coordinates: CoordinatesDTO,
    @SerializedName("country") val country: String,
    @SerializedName("postcode") val postcode: String,
    @SerializedName("state") val state: String,
    @SerializedName("street") val street: StreetDTO,
    @SerializedName("timezone") val timezone: TimezoneDTO
)
