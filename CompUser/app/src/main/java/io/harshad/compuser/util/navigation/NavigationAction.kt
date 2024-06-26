package io.harshad.compuser.util.navigation

import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import io.harshad.compuser.util.navigation.AppDestinations.SHOW_USER_ROUTE
import io.harshad.compuser.util.navigation.AppDestinations.USER_ROUTE

class NavigationAction(private val navController: NavHostController) {

    fun navigateToUserScreen() {
        navController.navigate(USER_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }

    fun navigateToShowUsers(){
        navController.navigate(SHOW_USER_ROUTE) {
            popUpTo(navController.graph.findStartDestination().id) {
                inclusive = true
            }
            launchSingleTop = true
        }
    }

    fun popBack(){
        navController.popBackStack()
    }
}