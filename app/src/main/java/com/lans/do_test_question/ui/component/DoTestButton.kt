package com.lans.do_test_question.ui.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.lans.do_test_question.R

@Composable
fun DoTestButton(
    modifier: Modifier,
    text: String,
    textSize: TextUnit,
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        onClick = onClick
    ) {
        Text(
            text = text,
            fontSize = textSize
        )
    }
}