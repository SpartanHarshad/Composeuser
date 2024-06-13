package io.harshad.compuser.composables


import android.content.Context
import android.os.Build
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import io.harshad.compuser.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomEditText(
    placeHolderTxtId: Int,
    userInput: String,
    isSingle: Boolean = true,
    maxLine: Int = 1,
    onTxtChange: (String) -> Unit,
    keyboardType: KeyboardType,
    hideKeyboard: () -> Unit = {},
    validateIp: (String) -> Boolean
) {
    var isIpEmpty by rememberSaveable { mutableStateOf(false) }
    var userStr by remember { mutableStateOf(userInput) }
    TextField(
        value = userInput,
        onValueChange = { newTxt ->
            userStr = newTxt
            isIpEmpty = validateIp(newTxt)
            onTxtChange(newTxt)
        },
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, top = 4.dp)
            .border(
                border = BorderStroke(width = 1.dp, color = Color.Gray),
                shape = RoundedCornerShape(8.dp)
            ),
        placeholder = { Text(text = stringResource(id = placeHolderTxtId)) },
        keyboardOptions = KeyboardOptions.Default.copy(
            imeAction = ImeAction.Done, keyboardType = keyboardType
        ),
        keyboardActions = KeyboardActions(onDone = { hideKeyboard() }),
        colors = TextFieldDefaults.textFieldColors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            containerColor = Color.White,
        ),
        singleLine = isSingle,
        maxLines = maxLine,
        visualTransformation = VisualTransformation.None,
        trailingIcon = {
            if (isIpEmpty) Icon(
                painter = painterResource(id = R.drawable.ic_solid_error),
                "Error",
                tint = MaterialTheme.colorScheme.error
            )
        }
    )
}

@Composable
fun EditTextLabel(textLabel: Int) {
    Text(
        text = stringResource(id = textLabel),
        fontStyle = FontStyle.Normal,
        fontSize = 16.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = Modifier.padding(top = 16.dp, bottom = 0.dp, start = 16.dp, end = 8.dp)
    )
}

@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DobDatePicker(
    state: DatePickerState,
    isOpen: Boolean,
    confirmButtonText: Int = R.string.ok,
    dismissButtonText: Int = R.string.cancel,
    onDismissRequest: () -> Unit,
    onConfirmButtonClicked: () -> Unit
) {
    if (isOpen) {
        DatePickerDialog(
            onDismissRequest = onDismissRequest,
            modifier = Modifier
                .padding(16.dp)
                .wrapContentWidth(),
            confirmButton = {
                Button(onClick = onConfirmButtonClicked) {
                    Text(text = stringResource(id = confirmButtonText))
                }
            },
            dismissButton = {
                TextButton(onClick = onDismissRequest) {
                    Text(text = stringResource(id = dismissButtonText))
                }
            },
            content = {
                DatePicker(
                    state = state,
                )
            })
    }
}

@Composable
fun CustomCurveRoundedButton(btnTxt: Int, onBtnClick: () -> Unit) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally) {
        Button(
            onClick = {
                onBtnClick()
            },
            modifier = Modifier
                .padding(top = 16.dp)
                .clip(MaterialTheme.shapes.medium)
        ) {
            Text(text = stringResource(id = btnTxt))
        }
    }
}



