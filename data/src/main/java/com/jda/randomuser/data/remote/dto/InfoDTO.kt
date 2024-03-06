package com.jda.randomuser.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.jda.randomuser.domain.model.PageInfo

data class PageInfoDTO(
    @SerializedName("page") val page: Int? = null,
    @SerializedName("results") val results: Int? = null,
    @SerializedName("seed") val seed: String? = null,
    @SerializedName("version") val version: String? = null
)

fun PageInfoDTO.asDomainModel() =
    PageInfo(
        seed = seed?:"",
        results = results?:0,
        page = page?:0,
        version = version?:""
   )
