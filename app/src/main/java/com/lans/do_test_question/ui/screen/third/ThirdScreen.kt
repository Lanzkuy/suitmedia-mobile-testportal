package com.lans.do_test_question.ui.screen.third

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.lans.do_test_question.R
import com.lans.do_test_question.model.User
import com.lans.do_test_question.ui.component.UserItem
import com.lans.do_test_question.ui.theme.Blue
import kotlinx.coroutines.flow.map

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ThirdScreen(
    viewModel: ThirdViewModel = viewModel(factory = ThirdViewModel.factory),
    navigateBack: (user: User?) -> Unit
) {
    val state by viewModel.state
    val userListState = rememberLazyListState()
    val userList = state.userResponse

    LaunchedEffect(Unit) {
        viewModel.getUsers()
    }

    LaunchedEffect(userListState) {
        snapshotFlow { userListState.layoutInfo.visibleItemsInfo.lastOrNull() }
            .map { it?.index }
            .collect { index ->
                if (index == state.userResponse.size && !state.isLoading && state.hasMorePages) {
                    viewModel.getUsers()
                }
            }
    }

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
                        navigateBack.invoke(null)
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
                Text(text = stringResource(R.string.third_screen))
            }
        )
        HorizontalDivider(thickness = 1.dp)
        if (userList.isNotEmpty()) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp),
                state = userListState,
                verticalArrangement = Arrangement.spacedBy(2.dp)
            ) {
                items(userList) { user ->
                    UserItem(
                        avatar = user.avatar,
                        name = "${user.firstName} ${user.lastName}",
                        email = user.email,
                        onClick = {
                            navigateBack.invoke(user)
                        }
                    )
                }

                item {
                    if (state.isLoading) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(16.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            CircularProgressIndicator()
                        }
                    }
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "Empty",
                    color = Color.Black,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}