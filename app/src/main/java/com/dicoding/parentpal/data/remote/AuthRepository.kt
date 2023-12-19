package com.dicoding.parentpal.data.remote

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.liveData
import com.dicoding.parentpal.data.remote.response.LoginResponse
import com.dicoding.parentpal.data.remote.response.ResponseItem
import com.dicoding.parentpal.data.remote.response.SignupPostResponse
import com.dicoding.parentpal.data.remote.retrofit.ApiService
import com.dicoding.parentpal.data.remote.retrofit.LoginRequest
import com.dicoding.parentpal.data.remote.retrofit.SignupPostRequest

class AuthRepository(private val apiService: ApiService) {
    fun postLogin(email: String, password: String): LiveData<Result<LoginResponse>> = liveData {
        emit(Result.Loading)
        try {
            val body = LoginRequest(email, password)
            val response = apiService.postLogin(body)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.e("LoginViewModel", "postLogin: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun postSignup(
        name: String,
        email: String,
        password: String,
        confPassword: String
    ): LiveData<Result<SignupPostResponse>> = liveData {
        emit(Result.Loading)
        try {
            val body = SignupPostRequest(name, email, password, confPassword)
            val response = apiService.postSignup(body)
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.e("SignupViewModel", "postLogin: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }

    fun getUsers(token: String): LiveData<Result<List<ResponseItem>>> = liveData {
        emit(Result.Loading)
        try {
            val response = apiService.getUsers("Bearer $token")
            emit(Result.Success(response))
        } catch (e: Exception) {
            Log.e("LoginViewModel", "getUsers: ${e.message.toString()}")
            emit(Result.Error(e.message.toString()))
        }
    }
}