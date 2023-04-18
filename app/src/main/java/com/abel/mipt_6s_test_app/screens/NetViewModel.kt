package com.abel.mipt_6s_test_app.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.abel.mipt_6s_test_app.data.NavParamsCache
import com.abel.mipt_6s_test_app.data.RestaurantRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject


sealed class NetEvent {
    data class CommercialClicked(val url: String) : NetEvent()
    data class SwitchSection(val section: NetViewState.Section) : NetEvent()
    data class RestaurantClicked(val restaurant: Restaurant) : NetEvent()
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

    private var _navController: NavController? = null

    fun setNavController(navController: NavController?) {
        _navController = navController
    }

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
            is NetEvent.RestaurantClicked -> {
                expandDetails(event.restaurant)
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

    private fun expandDetails(restaurant: Restaurant) {
        val id = NavParamsCache.put(restaurant)

        _navController?.navigate(
            "details/${id}"
        )
    }
}