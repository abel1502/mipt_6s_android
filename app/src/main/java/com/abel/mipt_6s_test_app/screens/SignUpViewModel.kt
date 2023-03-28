package com.abel.mipt_6s_test_app.screens

import android.util.Log
import androidx.lifecycle.ViewModel
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
}


data class SignUpViewState(
    val login: String = "",
    val email: String = "",
    val password: String = "",
    val keepSignedIn: Boolean = true,
    val emailMe: Boolean = true,
    val passwordShown: Boolean = false,
)


class SignUpViewModel : ViewModel() {
    private val _viewState = MutableStateFlow(SignUpViewState())
    val viewState: StateFlow<SignUpViewState> = _viewState

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
                Log.i("SignUpViewModel", "Already have account")
            }
        }
    }

    private fun doSubmit() {
        Log.i("SignUpViewModel", "form submitted: ${_viewState.value}")
    }
}