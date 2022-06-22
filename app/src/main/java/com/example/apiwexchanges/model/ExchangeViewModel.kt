package com.example.apiwexchanges.model

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.apiwexchanges.data.ListStateExchange
import com.example.apiwexchanges.data.remote.repository.ExchangeRepository
import com.example.apiwexchanges.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class ExchangeViewModel @Inject constructor(
     private val exchangeRepository: ExchangeRepository
) : ViewModel() {

    private var _state = mutableStateOf(ListStateExchange())
    val state: State<ListStateExchange> = _state

    init {
        exchangeRepository.getExchanges().onEach { result ->
            when (result) {
                is Resource.Loading -> {
                    _state.value = ListStateExchange(isLoading = true)
                }
                is Resource.Success -> {
                    _state.value = ListStateExchange(exchange = result.data ?: emptyList())
                }
                is Resource.Error -> {
                    _state.value = ListStateExchange(error = result.message ?: "Error!")
                }
            }
        }.launchIn(viewModelScope)
    }
}