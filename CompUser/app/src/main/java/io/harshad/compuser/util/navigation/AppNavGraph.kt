package io.harshad.compuser.util.navigation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import io.harshad.compuser.composables.ShowUsers
import io.harshad.compuser.composables.SplashScreen
import io.harshad.compuser.composables.UserScreen

@Composable
fun AppNavGraph(
    navController: NavHostController = rememberNavController(),
    navActions: NavigationAction = remember(navController) {
        NavigationAction(navController)
    }
) {
    NavHost(navController = navController, startDestination = AppDestinations.SPLASH_ROUTE) {
        composable(route = AppDestinations.SPLASH_ROUTE) {
            SplashScreen(navAction = navActions)
        }

        composable(route = AppDestinations.USER_ROUTE) {
            UserScreen(navAction = navActions)
        }

        composable(route = AppDestinations.SHOW_USER_ROUTE) {
            ShowUsers(navAction = navActions)
        }

    }
}