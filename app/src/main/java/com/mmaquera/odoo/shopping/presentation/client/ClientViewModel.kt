package com.mmaquera.odoo.shopping.presentation.client

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmaquera.odoo.shopping.domain.usecase.GetClientUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ClientViewModel @Inject constructor(
    private val getClientUseCase: GetClientUseCase
) : ViewModel() {

    private val intentFlow = MutableSharedFlow<ClientIntent>()

    private val _viewState = MutableStateFlow(ClientViewState())
    val viewState = _viewState.asStateFlow()

    init {
        intentFlow
            .map { intent -> intent.mapToAction() }
            .flatMapMerge { action -> processAction(action = action) }
            .map { result -> reduceToViewState(result = result) }
            .distinctUntilChanged()
            .onEach { newState -> _viewState.value = newState }
            .launchIn(viewModelScope)
    }


    private fun processAction(action: ClientAction): Flow<Result<ClientResult>> {
        return when (action) {
            is ClientAction.GetClient -> getClientUseCase(clientCode = action.clientCode).map {
                it.mapCatching { client -> ClientResult(client = client) }
            }
        }
    }

    private fun reduceToViewState(result: Result<ClientResult>): ClientViewState {
        return result.fold({
            _viewState.value.copy(client = it.client.toPresentation())
        }, {
            _viewState.value.copy(client = ClientModel())
        })
    }

    fun processIntent(intent: ClientIntent) {
        viewModelScope.launch {
            intentFlow.emit(intent)
        }
    }
}