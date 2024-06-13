package io.harshad.compuser.composables

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import io.harshad.compuser.util.navigation.NavigationAction
import io.harshad.compuser.viewmodel.UserViewModel

@Composable
fun ShowUsers(
    navAction: NavigationAction,
    vm: UserViewModel = hiltViewModel()
) {

}