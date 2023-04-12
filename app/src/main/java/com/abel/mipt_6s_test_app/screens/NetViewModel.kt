package com.abel.mipt_6s_test_app.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abel.mipt_6s_test_app.net.CatalogResponse
import com.abel.mipt_6s_test_app.net.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed class NetEvent {
    data class CommercialClicked(val url: String) : NetEvent()
    data class SwitchSection(val section: NetViewState.Section) : NetEvent()
}


data class NetViewState(
    val nearestRestaurants: List<Restaurant> = emptyList(),
    val popularRestaurants: List<Restaurant> = emptyList(),
    val commercial: Commercial? = null,
    val curSection: Section = Section.NEAREST,
) {
    enum class Section {
        NEAREST,
        POPULAR,
    }

    fun getRestaurants(): List<Restaurant> {
        return when (curSection) {
            Section.NEAREST -> nearestRestaurants
            Section.POPULAR -> popularRestaurants
        }
    }
}


@HiltViewModel
class NetViewModel @Inject constructor(private val repository: RestaurantRepository) : ViewModel() {
    private val _viewState = MutableStateFlow(NetViewState())
    val viewState: StateFlow<NetViewState> = _viewState

    init {
        fetchRestaurants()
    }

    fun obtainEvent(event: NetEvent) {
        when (event) {
            is NetEvent.CommercialClicked -> {
                Log.i("NetViewModel", "Commercial clicked: ${event.url}")
            }
            is NetEvent.SwitchSection -> {
                _viewState.value = _viewState.value.copy(curSection = event.section)
            }
        }
    }

    private fun fetchRestaurants() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.fetchCatalog()
                .collectLatest { catalog ->
                    _viewState.value = _viewState.value.copy(
                        nearestRestaurants = catalog.nearest.map { Restaurant.fromRemote(it) },
                        popularRestaurants = catalog.popular.map { Restaurant.fromRemote(it) },
                        commercial = Commercial.fromRemote(catalog.commercial),
                    )
                }
        }
    }
}