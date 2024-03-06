package com.jda.randomuser.data

import androidx.paging.PagingSource
import com.jda.randomuser.data.local.RandomUserMetadata
import com.jda.randomuser.data.remote.dto.IdDTO
import com.jda.randomuser.data.remote.dto.NameDTO
import com.jda.randomuser.data.remote.dto.ResultDTO
import com.jda.randomuser.data.remote.dto.asEntityModel

fun getResultDtoList(page: Int) = ((page * 10)..(page * 10) + 9).map {
    getResultDto(it)
}

fun getResultDto(index: Int) = ResultDTO(
    id = IdDTO(name = "ID-$index", value = "value-$index"),
    name = NameDTO(first = "FIRST-$index", last = "LAST-$index", title = "TITLE-$index"),
    email = "email@email-$index.com",
)

fun getUserEntityList(page: Int) = ((page * 10)..(page * 10) + 9).map {
    getResultDto(it)
}.asEntityModel()

val PAGES = (1..10).map {
    PagingSource.LoadResult.Page(
        data = getUserEntityList(it),
        prevKey = if (it == 1) null else it - 1,
        nextKey = if (it == 10) null else it + 1
    )
}

val PAGES_3_10 = PAGES.takeLast(7)

val KEYS = PAGES.map { page ->
    page.data.map {
        RandomUserMetadata(
            id = it.id,
            nextPage = page.nextKey,
            prevPage = page.prevKey
        )
    }
}
