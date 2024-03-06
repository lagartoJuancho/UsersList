package com.jda.randomuser.ui.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jda.randomuser.ui.screens.details.DetailScreen
import com.jda.randomuser.ui.screens.main.MainScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun Navigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = NavItem.Main.route
    ) {
        composable(NavItem.Main) {
            MainScreen(
                onItemClick = {
                    navController.navigate(
                        NavItem.Detail.createRoute(
                            it.photo,
                            it.displayName,
                            it.email,
                            it.gender,
                            it.dateJoined,
                            it.phone,
                            it.location.latitude,
                            it.location.longitude
                        )
                    )
                }
            )
        }
        composable(NavItem.Detail) { backStackEntry ->
            DetailScreen(
                onBackPressed = { navController.popBackStack() },
                photo = backStackEntry.findArg(NavArg.Photo),
                name = backStackEntry.findArg(NavArg.Name),
                email = backStackEntry.findArg(NavArg.Email),
                gender = backStackEntry.findArg(NavArg.Gender),
                date = backStackEntry.findArg(NavArg.DateJoined),
                phone = backStackEntry.findArg(NavArg.Phone),
                latitude = backStackEntry.findArg(NavArg.Latitude),
                longitude = backStackEntry.findArg(NavArg.Longitude),
            )
        }
    }
}

private fun NavGraphBuilder.composable(
    navItem: NavItem,
    content: @Composable (NavBackStackEntry) -> Unit
) {
    composable(
        route = navItem.route,
        arguments = navItem.args
    ) {
        content(it)
    }
}

inline fun <reified T> NavBackStackEntry.findArg(arg: NavArg): T {
    val value = arguments?.getString(arg.key)
    requireNotNull(value)
    return value as T
}

