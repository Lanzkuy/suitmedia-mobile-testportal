package com.lans.do_test_question.ui.screen.second

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.lans.do_test_question.R
import com.lans.do_test_question.model.User
import com.lans.do_test_question.ui.component.DoTestButton
import com.lans.do_test_question.ui.theme.Blue

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SecondScreen(
    username: String,
    user: User,
    navigateToThirdScreen: () -> Unit,
    navigateBack: () -> Unit
) {
    Column(
        modifier = Modifier
            .statusBarsPadding()
            .navigationBarsPadding()
    ) {
        CenterAlignedTopAppBar(
            navigationIcon = {
                IconButton(
                    modifier = Modifier
                        .size(48.dp),
                    onClick = {
                        navigateBack.invoke()
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_back),
                        tint = Blue,
                        contentDescription = stringResource(R.string.back)
                    )
                }
            },
            title = {
                Text(text = stringResource(R.string.second_screen))
            }
        )
        HorizontalDivider(thickness = 1.dp)
        Column(
            modifier = Modifier
                .padding(20.dp)
                .weight(1f),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(
                    text = stringResource(R.string.welcome),
                    fontSize = 14.sp,
                    color = Color.Black
                )
                Text(
                    text = username,
                    color = Color.Black,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = if (user.firstName.isEmpty()) stringResource(R.string.selected_user_name) else "${user.firstName} ${user.lastName}",
                textAlign = TextAlign.Center,
                color = Color.Black,
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold
            )
            DoTestButton(
                modifier = Modifier
                    .fillMaxWidth(),
                text = stringResource(R.string.choose_a_user),
                textSize = 18.sp,
                onClick = {
                    navigateToThirdScreen.invoke()
                }
            )
        }
    }
}