package io.harshad.compuser.composables

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import io.harshad.compuser.R
import io.harshad.compuser.data.local.UserEntity
import io.harshad.compuser.util.navigation.NavigationAction
import io.harshad.compuser.viewmodel.UserViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShowUsers(
    navAction: NavigationAction,
    vm: UserViewModel = hiltViewModel()
) {
    val users: List<UserEntity> = vm.userItems.collectAsState(emptyList()).value
    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                text = stringResource(id = R.string.users),
                fontStyle = FontStyle.Normal,
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(16.dp)
            )
        },
            navigationIcon = {
                IconButton(
                    onClick = { navAction.popBack() }, modifier = Modifier.size(30.dp)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "back arrow",
                        tint = Color.Black
                    )
                }
            }
        )
    },
        content = { paddingValues ->
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(users.size) { idx ->
                    UserItemCard(users[idx])
                }
            }
        }
    )
}