package com.jda.randomuser.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.jda.randomuser.data.local.RandomUserEntity
import com.jda.randomuser.domain.model.PagedResult
import com.jda.randomuser.domain.model.RandomUser
import com.jda.randomuser.domain.model.UserLocation

data class ResultsDTO(
    @SerializedName("info") val info: PageInfoDTO,
    @SerializedName("results") val results: List<ResultDTO>
)

fun ResultsDTO.asDomainModel(): PagedResult<List<RandomUser>> =
    PagedResult(pageInfo = info.asDomainModel(), results = results.asDomainModel())

fun List<ResultDTO>.asDomainModel(): List<RandomUser> =
    map {
        RandomUser(
            id = it.id?.name ?: "",
            displayName = "${it.name?.first} ${it.name?.last}",
            email = it.email ?: "",
            gender = it.gender ?: "",
            dateJoined = it.dob?.date?.toDateFormat() ?: "",
            phone = it.phone ?: "",
            location = it.location?.toUserLocation() ?: UserLocation(),
            photo = it.picture?.large ?: ""
        )
    }

fun List<ResultDTO>.asEntityModel(): List<RandomUserEntity> =
    mapNotNull {
        if (it.email == null) {
            null
        } else {
            RandomUserEntity(
                id = it.email,
                displayName = "${it.name?.first} ${it.name?.last}",
                email = it.email,
                gender = it.gender ?: "",
                dateJoined = it.dob?.date?.toDateFormat() ?: "",
                phone = it.phone ?: "",
                latitude = it.location?.coordinates?.latitude ?: "",
                longitude = it.location?.coordinates?.longitude ?: "",
                photo = it.picture?.large ?: ""
            )
        }
    }

private fun String.toDateFormat() =
    this.substringBefore(
        delimiter = "T",
        missingDelimiterValue = "T not found"
    )
        .replace("-", "/")
        .split("/")
        .reversed()
        .joinToString("/")

fun LocationDTO.toUserLocation() =
    UserLocation(
        latitude = this.coordinates.latitude,
        longitude = this.coordinates.longitude
    )