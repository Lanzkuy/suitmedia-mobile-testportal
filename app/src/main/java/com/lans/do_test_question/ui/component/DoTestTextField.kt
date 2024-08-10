package com.lans.do_test_question.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.lans.do_test_question.R

@Composable
fun DoTestTextField(
    modifier: Modifier,
    input: String,
    placeholder: String,
    onValueChange: (value: String) -> Unit
){
    TextField(
        modifier = modifier,
        value = input,
        placeholder = {
            Text(
                text = placeholder,
                color = Color.Gray
            )
        },
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.colors(
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),
        onValueChange = onValueChange
    )
}