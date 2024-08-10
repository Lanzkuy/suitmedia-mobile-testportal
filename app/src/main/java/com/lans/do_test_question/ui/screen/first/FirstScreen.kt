package com.lans.do_test_question.ui.screen.first

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lans.do_test_question.R
import com.lans.do_test_question.ui.component.AlertDialog
import com.lans.do_test_question.ui.component.DoTestButton
import com.lans.do_test_question.ui.component.DoTestTextField

@Composable
fun FirstScreen(
    navigateToSecondScreen: (username: String) -> Unit
) {
    val alertState = remember { mutableStateOf(false) }
    val alertMessage = remember { mutableStateOf("") }
    val name = remember { mutableStateOf("") }
    val palindrome = remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .fillMaxSize()
    ) {
        if (alertState.value) {
            AlertDialog(
                message = alertMessage.value,
                isVisible = alertState
            )
        }

        Image(
            modifier = Modifier
                .matchParentSize(),
            painter = painterResource(id = R.drawable.background),
            contentScale = ContentScale.FillBounds,
            contentDescription = stringResource(R.string.background),
        )

        Column(
            modifier = Modifier
                .matchParentSize()
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_photo),
                contentDescription = stringResource(R.string.profile_picture),
            )
            Spacer(modifier = Modifier.padding(24.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                DoTestTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    input = name.value,
                    placeholder = stringResource(id = R.string.name),
                    onValueChange = {
                        name.value = it
                    }
                )
                DoTestTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    input = palindrome.value,
                    placeholder = stringResource(R.string.palindrome),
                    onValueChange = {
                        palindrome.value = it
                    }
                )
            }
            Spacer(modifier = Modifier.padding(24.dp))
            Column(
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                DoTestButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = stringResource(R.string.check),
                    textSize = 18.sp,
                    onClick = {
                        if (palindrome.value.isEmpty()) {
                            alertState.value = true
                            alertMessage.value = "Palindrome field must be filled"
                            return@DoTestButton
                        }

                        if (isPalindrome(palindrome.value)) {
                            alertState.value = true
                            alertMessage.value = "isPalindrome"
                        } else {
                            alertState.value = true
                            alertMessage.value = "not palindrome"
                        }
                    }
                )
                DoTestButton(
                    modifier = Modifier
                        .fillMaxWidth(),
                    text = stringResource(R.string.next),
                    textSize = 18.sp,
                    onClick = {
                        if (name.value.isEmpty()) {
                            alertState.value = true
                            alertMessage.value = "Name field must be filled"
                            return@DoTestButton
                        }

                        navigateToSecondScreen.invoke(name.value)
                    }
                )
            }
        }
    }
}

fun isPalindrome(text: String): Boolean {
    val cleanText = text.replace(" ", "").lowercase()
    return cleanText.reversed() == cleanText
}