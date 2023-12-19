package com.dicoding.parentpal.ui.auth

import androidx.lifecycle.ViewModel
import com.dicoding.parentpal.data.remote.AuthRepository

class SignupViewModel(private val storyRepository: AuthRepository) : ViewModel() {

    fun register(name: String, email: String, password: String, confPassword: String) =
        storyRepository.postSignup(name, email, password, confPassword)

}