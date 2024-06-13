package io.harshad.compuser.composables


import android.content.Context
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import io.harshad.compuser.R
import io.harshad.compuser.data.local.UserEntity
import io.harshad.compuser.util.Util.changeMillisToDateString
import io.harshad.compuser.util.navigation.NavigationAction
import io.harshad.compuser.viewmodel.UserViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.time.Instant


@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserScreen(
    navAction: NavigationAction,
    vm: UserViewModel = hiltViewModel()
) {
    val localFocusManager = LocalFocusManager.current
    val ctx: Context = LocalContext.current
    var userName by remember { mutableStateOf("") }
    var userAge by remember { mutableStateOf("") }
    var userAdr by remember { mutableStateOf("") }
    var isDatePickerDialogOpen by rememberSaveable { mutableStateOf(false) }
    var selectedDate by rememberSaveable { mutableStateOf(System.currentTimeMillis()) }
    val datePickerState = rememberDatePickerState(
        initialSelectedDateMillis = Instant.now().toEpochMilli()
    )
    DobDatePicker(
        state = datePickerState,
        isOpen = isDatePickerDialogOpen,
        onDismissRequest = { isDatePickerDialogOpen = false },
        onConfirmButtonClicked = {
            selectedDate = datePickerState.selectedDateMillis ?: System.currentTimeMillis()
            isDatePickerDialogOpen = false
        }
    )

    Scaffold(topBar = {
        TopAppBar(title = {
            Text(
                text = stringResource(id = R.string.user_title),
                fontStyle = FontStyle.Normal,
                color = Color.Black,
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(16.dp)
            )
        })
    },
        content = { paddingValues ->
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(paddingValues),
            ) {
                EditTextLabel(R.string.user_name)
                CustomEditText(
                    placeHolderTxtId = R.string.hint_user_name,
                    userInput = userName,
                    hideKeyboard = { localFocusManager.clearFocus() },
                    keyboardType = KeyboardType.Text,
                    validateIp = { false },
                    onTxtChange = { newName ->
                        userName = newName
                    }
                )
                EditTextLabel(R.string.user_age)
                CustomEditText(
                    placeHolderTxtId = R.string.hint_user_age,
                    userInput = userAge,
                    hideKeyboard = { localFocusManager.clearFocus() },
                    keyboardType = KeyboardType.Number,
                    validateIp = { false },
                    onTxtChange = { newAge ->
                        userAge = newAge
                    }
                )
                EditTextLabel(R.string.user_dob)
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 16.dp, end = 16.dp, top = 4.dp)
                        .border(
                            border = BorderStroke(width = 1.dp, color = Color.Gray),
                            shape = RoundedCornerShape(8.dp)
                        ),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = selectedDate.changeMillisToDateString(),
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 12.dp)
                    )
                    IconButton(onClick = { isDatePickerDialogOpen = true }) {
                        Icon(
                            imageVector = Icons.Default.DateRange,
                            contentDescription = "Select DOB"
                        )
                    }
                }
                EditTextLabel(R.string.user_Adr)
                CustomEditText(
                    placeHolderTxtId = R.string.hint_user_adr,
                    userInput = userAdr,
                    isSingle = false,
                    maxLine = 3,
                    hideKeyboard = { localFocusManager.clearFocus() },
                    keyboardType = KeyboardType.Text,
                    validateIp = { false },
                    onTxtChange = { newAdr ->
                        userAdr = newAdr
                    }
                )
                CustomCurveRoundedButton(R.string.btn_save) {
                    val result = isDataValid(userAdr, userAge, userName)
                    if (result == 0) {
                        val user = UserEntity(
                            uName = userName,
                            uAge = userAge.toInt(),
                            uAdr = userAdr,
                            uDob = selectedDate.changeMillisToDateString()
                        )
                        vm.insertUser(user)
                        userName = ""
                        userAge = ""
                        userAdr = ""
                        navAction.navigateToShowUsers()
                    } else {
                        when (result) {
                            1 -> {
                                showToast(errorMsg = "Please Enter name", ctx = ctx)
                            }

                            2 -> {
                                showToast(errorMsg = "Please Enter your age", ctx = ctx)
                            }

                            3 -> {
                                showToast(errorMsg = "Please Enter your address", ctx = ctx)
                            }
                        }
                    }
                }
                CustomCurveRoundedButton(btnTxt = R.string.btn_show_user) {
                    navAction.navigateToShowUsers()
                }
            }
        })

}


fun isDataValid(userAdr: String, userAge: String, userName: String): Int {
    return if (userName.isEmpty()) {
        1
    } else if (userAge.isEmpty()) {
        2
    } else if (userAdr.isEmpty()) {
        3
    } else {
        0
    }
}

fun showToast(errorMsg: String, ctx: Context) {
    Toast.makeText(
        ctx, errorMsg,
        Toast.LENGTH_SHORT
    ).show()
}

