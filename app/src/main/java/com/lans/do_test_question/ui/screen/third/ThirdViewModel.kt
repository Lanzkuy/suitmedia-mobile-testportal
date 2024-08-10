package com.lans.do_test_question.ui.screen.third

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.lans.do_test_question.DoTestQuestionApplication
import com.lans.do_test_question.network.Resource
import com.lans.do_test_question.repository.UserRepository
import kotlinx.coroutines.launch

class ThirdViewModel(
    private val userRepository: UserRepository
) : ViewModel() {
    companion object {
        val factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as DoTestQuestionApplication)
                ThirdViewModel(application.container.userRepository)
            }
        }
    }

    private val _state = mutableStateOf(ThirdUIState())
    val state: State<ThirdUIState> get() = _state

    fun getUsers() {
        viewModelScope.launch {
            userRepository.getUsers(
                page = _state.value.page
            ).collect { users ->
                when (users) {
                    is Resource.Success -> {
                        _state.value = _state.value.copy(
                            page = _state.value.page + 1,
                            userResponse = _state.value.userResponse + users.data,
                            hasMorePages = users.data.isNotEmpty(),
                            isLoading = false
                        )
                    }

                    is Resource.Error -> {
                        _state.value = _state.value.copy(
                            error = users.message,
                            isLoading = false
                        )
                    }

                    is Resource.Loading -> {
                        _state.value = _state.value.copy(
                            isLoading = true
                        )
                    }
                }
            }
        }
    }
}