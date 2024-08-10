package com.lans.do_test_question.ui.screen.third

import com.lans.do_test_question.model.User

data class ThirdUIState(
    var page: Int = 1,
    val hasMorePages: Boolean = false,
    var userResponse: List<User> = emptyList(),
    val error: String? = null,
    val isLoading: Boolean = false
)
