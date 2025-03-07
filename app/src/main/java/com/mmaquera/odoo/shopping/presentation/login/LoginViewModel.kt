@file:Suppress("OPT_IN_USAGE")

package com.mmaquera.odoo.shopping.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmaquera.odoo.shopping.domain.usecase.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor (
    private val signInUseCase: SignInUseCase
) : ViewModel() {

    private val intentFlow = MutableSharedFlow<LoginIntent>()

    private val _viewState = MutableStateFlow(LoginViewState())
    val viewState = _viewState.asStateFlow()

    init {
        intentFlow
            .map { intent -> intent.mapToAction() }
            .flatMapMerge { action -> processAction(action = action) }
            .map { result -> reduceToViewState(result = result) }
            .onEach { newState -> _viewState.update { newState } }
            .launchIn(viewModelScope)
    }

    private fun processAction(action: LoginAction): Flow<Result<LoginResult>> {
        return when (action) {
            is LoginAction.SignIn -> signInUseCase(user = action.user, password = action.password)
                .map { it.mapCatching { id -> LoginResult(user = id.id.toString()) } }
        }
    }

    private fun reduceToViewState(result: Result<LoginResult>): LoginViewState {
        return result.fold({
            _viewState.value.copy(loginSuccess = true)
        }, {
            _viewState.value.copy(isLoading = false)
        })
    }

    fun processIntent(intent: LoginIntent) {
        viewModelScope.launch {
            intentFlow.emit(intent)
        }
    }
}