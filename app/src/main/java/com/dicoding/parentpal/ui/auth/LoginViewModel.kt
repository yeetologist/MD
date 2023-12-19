package com.dicoding.parentpal.ui.auth

import androidx.lifecycle.ViewModel
import com.dicoding.parentpal.data.remote.AuthRepository

class LoginViewModel(private val storyRepository: AuthRepository) : ViewModel() {
    fun postLogin(email: String, password: String) =
        storyRepository.postLogin(email, password)

    fun getUsers(token: String) = storyRepository.getUsers(token)
}