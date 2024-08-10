package com.lans.do_test_question.ui.component

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.lans.do_test_question.R

@Composable
fun AlertDialog(
    message: String,
    isVisible: MutableState<Boolean>
) {
    if (isVisible.value) {
        AlertDialog(
            title = {
                Text(
                    text = stringResource(R.string.alert)
                )
            },
            text = {
                Text(
                    text = message
                )
            },
            onDismissRequest = {
                isVisible.value = false
            },
            confirmButton = {
                Button(
                    onClick = {
                        isVisible.value = false
                    }
                ) {
                    Text(
                        text = stringResource(R.string.ok),
                        color = Color.White
                    )
                }
            }
        )
    }
}