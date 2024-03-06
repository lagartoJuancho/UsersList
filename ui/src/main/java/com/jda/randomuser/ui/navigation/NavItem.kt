package com.jda.randomuser.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.navigation.NavType
import androidx.navigation.navArgument
import com.jda.randomuser.ui.toEncodedString

sealed class NavItem(
    val baseRoute: String,
    private val navArgs: List<NavArg> = emptyList()
) {
    object Main : NavItem("main")
    object Detail : NavItem(
        "detail",
        listOf(
            NavArg.Photo,
            NavArg.Name,
            NavArg.Email,
            NavArg.Gender,
            NavArg.DateJoined,
            NavArg.Phone,
            NavArg.Latitude,
            NavArg.Longitude
        )
    ) {
        @RequiresApi(Build.VERSION_CODES.O)
        fun createRoute(
            photo: String,
            name: String,
            email: String,
            gender: String,
            date: String,
            phone: String,
            latitude: String,
            longitude: String
        ): String {
            return "$baseRoute/${photo.toEncodedString()}/$name/$email/$gender/${date.toEncodedString()}/$phone/$latitude/$longitude"
        }

    }

    val route = run {
        val argKeys = navArgs.map { "{${it.key}}" }
        listOf(baseRoute)
            .plus(argKeys)
            .joinToString("/")
    }

    val args = navArgs.map {
        navArgument(it.key) { type = it.navType }
    }
}

enum class NavArg(val key: String, val navType: NavType<*>) {
    Photo("photo", NavType.StringType),
    Name("name", NavType.StringType),
    Email("email", NavType.StringType),
    Gender("gender", NavType.StringType),
    DateJoined("date", NavType.StringType),
    Phone("phone", NavType.StringType),
    Latitude("latitude", NavType.StringType),
    Longitude("longitude", NavType.StringType)
}
