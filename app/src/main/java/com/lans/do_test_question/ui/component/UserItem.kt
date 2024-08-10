package com.lans.do_test_question.ui.component

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.lans.do_test_question.R

@Composable
fun UserItem(
    avatar: String,
    name: String,
    email: String,
    onClick: () -> Unit
) {
    Column(
        modifier = Modifier
            .clickable {
                onClick.invoke()
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    vertical = 16.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .clip(CircleShape)
                    .size(60.dp),
                model = avatar,
                error = painterResource(id = R.drawable.background),
                contentScale = ContentScale.Crop,
                contentDescription = stringResource(R.string.avatar),
            )
            Spacer(modifier = Modifier.padding(10.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
            ) {
                Text(
                    text = name,
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Medium
                )
                Spacer(modifier = Modifier.padding(2.dp))
                Text(
                    text = email,
                    color = Color.Black,
                    fontSize = 12.sp
                )
            }
        }
        HorizontalDivider(thickness = 1.dp)
    }
}