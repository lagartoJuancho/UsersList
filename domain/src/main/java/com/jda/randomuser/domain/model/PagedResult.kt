package com.jda.randomuser.domain.model

data class PagedResult<T>(val results: T, val pageInfo: PageInfo)