package com.mmaquera.odoo.shopping.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mmaquera.odoo.shopping.domain.usecase.GetClientsUseCase
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
class HomeViewModel @Inject constructor(
    private val getClientsUseCase: GetClientsUseCase
) : ViewModel() {

    private val intentFlow = MutableSharedFlow<HomeIntent>()

    private val _viewState = MutableStateFlow(HomeViewState())
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

    private fun processAction(action: HomeAction): Flow<Result<HomeResult>> {
        return when (action) {
            is HomeAction.LoadClients -> getClientsUseCase().map {
                it.mapCatching { clients -> HomeResult(clients = clients) }
            }
        }
    }

    private fun reduceToViewState(result: Result<HomeResult>): HomeViewState {
        return result.fold({
            _viewState.value.copy(clients = it.clients.toPresentation())
        }, {
            _viewState.value.copy(clients = emptyList())
        })
    }

    fun processIntent(intent: HomeIntent) {
        viewModelScope.launch {
            intentFlow.emit(intent)
        }
    }
}