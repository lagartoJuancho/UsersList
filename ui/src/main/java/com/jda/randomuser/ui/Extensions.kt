package com.jda.randomuser.ui

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.lazy.LazyItemScope
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.runtime.Composable
import androidx.paging.LoadState
import java.util.Base64

@RequiresApi(Build.VERSION_CODES.O)
fun String.toEncodedString(): String = Base64.getUrlEncoder().encodeToString(this.toByteArray())

@RequiresApi(Build.VERSION_CODES.O)
fun String.toDecodedString() = String(Base64.getUrlDecoder().decode(this))
fun LazyListScope.pagingLoadStateItem(
    loadState: LoadState,
    keySuffix: String? = null,
    loading: (@Composable LazyItemScope.() -> Unit)? = null,
    error: (@Composable LazyItemScope.(LoadState.Error) -> Unit)? = null,
) {
    if (loading != null && loadState == LoadState.Loading) {
        item(
            key = keySuffix?.let { "loadingItem_$it" },
            content = loading,
        )
    }
    if (error != null && loadState is LoadState.Error) {
        item(
            key = keySuffix?.let { "errorItem_$it" },
            content = { error(loadState)},
        )
    }
}
