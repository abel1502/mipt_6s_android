package com.abel.mipt_6s_test_app.screens

import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.abel.mipt_6s_test_app.data.NavParamsCache
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


sealed class DetailsEvent {
    data class ProvideRestaurant(val paramId: Int) : DetailsEvent()
}


data class DetailsViewState(
    val restaurant: Restaurant? = null,
)


class DetailsViewModel : ViewModel() {
    private val _viewState = MutableStateFlow(DetailsViewState())
    val viewState: StateFlow<DetailsViewState> = _viewState

    private var _navController: NavController? = null

    fun setNavController(navController: NavController?) {
        _navController = navController
    }

    fun obtainEvent(event: DetailsEvent) {
        when (event) {
            is DetailsEvent.ProvideRestaurant -> {
                // The weird handling is to certainly avoid taking from the cache
                // twice (since the value is cleared after taking)
                val restaurant: Restaurant = NavParamsCache.take(event.paramId)
                    ?: return

                _viewState.value = _viewState.value.copy(restaurant = restaurant)
            }
        }
    }
}