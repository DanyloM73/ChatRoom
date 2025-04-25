package com.danylom73.chatroom.view_model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.danylom73.chatroom.data.Result
import com.danylom73.chatroom.data.UserRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.launch

class AuthViewModel : ViewModel() {
    private val userRepository: UserRepository = UserRepository(
        FirebaseAuth.getInstance(),
        Injection.instance()
    )

    private val _authResult = MutableLiveData<Result<Boolean>>()
    val authResult: LiveData<Result<Boolean>> get() = _authResult

    fun signUp(
        email: String,
        password: String,
        firstName: String,
        lastName: String
    ) {
        viewModelScope.launch {
            _authResult.value = userRepository
                .signUp(email, password, firstName, lastName)
        }
    }

    fun signIn(
        email: String,
        password: String
    ) {
        viewModelScope.launch {
            _authResult.value = userRepository.signIn(email, password)
        }
    }
}