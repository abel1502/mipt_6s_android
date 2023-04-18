package com.abel.mipt_6s_test_app.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow


sealed class SignUpEvent {
    class LoginChanged(val login: String) : SignUpEvent()
    class EmailChanged(val email: String) : SignUpEvent()
    class PasswordChanged(val password: String) : SignUpEvent()
    class KeepSignedInChanged(val keepSignedIn: Boolean) : SignUpEvent()
    class EmailMeChanged(val emailMe: Boolean) : SignUpEvent()
    object PasswordShownChanged : SignUpEvent()
    object Submit : SignUpEvent()
    object AlreadyHaveAccount : SignUpEvent()
    object PopupShown : SignUpEvent()
}


data class SignUpViewState(
    val login: String = "",
    val email: String = "",
    val password: String = "",
    val keepSignedIn: Boolean = true,
    val emailMe: Boolean = true,
    val passwordShown: Boolean = false,
    val popupMessage: String? = null,
)


class SignUpViewModel : ViewModel() {
    private val _viewState = MutableStateFlow(SignUpViewState())
    val viewState: StateFlow<SignUpViewState> = _viewState

    private var _navController: NavController? = null

    fun setNavController(navController: NavController?) {
        _navController = navController
    }

    fun obtainEvent(event: SignUpEvent) {
        when (event) {
            is SignUpEvent.LoginChanged -> {
                _viewState.value = _viewState.value.copy(login = event.login)
            }
            is SignUpEvent.EmailChanged -> {
                _viewState.value = _viewState.value.copy(email = event.email)
            }
            is SignUpEvent.PasswordChanged -> {
                _viewState.value = _viewState.value.copy(password = event.password)
            }
            is SignUpEvent.KeepSignedInChanged -> {
                _viewState.value = _viewState.value.copy(keepSignedIn = event.keepSignedIn)
            }
            is SignUpEvent.EmailMeChanged -> {
                _viewState.value = _viewState.value.copy(emailMe = event.emailMe)
            }
            SignUpEvent.PasswordShownChanged -> {
                _viewState.value = _viewState.value.copy(passwordShown = !_viewState.value.passwordShown)
            }
            SignUpEvent.Submit -> {
                doSubmit()
            }
            SignUpEvent.AlreadyHaveAccount -> {
                doAlreadyHaveAccount()
            }
            SignUpEvent.PopupShown -> {
                _viewState.value = _viewState.value.copy(popupMessage = null)
            }
        }
    }

    private fun doSubmit() {
        Log.i("SignUpViewModel", "form submitted: ${_viewState.value}")

        _navController?.navigate("main")
    }

    private fun doAlreadyHaveAccount() {
        Log.i("SignUpViewModel", "Already have account")

        showPopup("I'll trust you :)")
        _navController?.navigate("main")
    }

    private fun showPopup(message: String) {
        _viewState.value = _viewState.value.copy(popupMessage = message)
    }
}