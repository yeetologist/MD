package com.dicoding.parentpal.data.remote.retrofit

import com.dicoding.parentpal.data.remote.response.LoginResponse
import com.dicoding.parentpal.data.remote.response.NewsResponse
import com.dicoding.parentpal.data.remote.response.ResponseItem
import com.dicoding.parentpal.data.remote.response.SignupPostResponse
import com.dicoding.parentpal.util.getOneMonthBeforeCurrentTime
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {

    @GET("everything")
    suspend fun getListNews(
        @Query("q") query: String = "Health",
        @Query("from") fromDate: String = getOneMonthBeforeCurrentTime(),
        @Query("sortBy") sortBy: String = "popularity",
        @Query("apiKey") apiKey: String = "c3775a471c674664b618c6a70ae128e8",
        @Query("page") page: Int,
        @Query("size") size: Int
    ): NewsResponse

    @POST("login")
    suspend fun postLogin(@Body loginRequest: LoginRequest): LoginResponse

    @POST("users")
    suspend fun postSignup(@Body signupRequest: SignupPostRequest): SignupPostResponse

    @GET("users")
    suspend fun getUsers(@Header("Authorization") token: String): List<ResponseItem>
}

data class LoginRequest(
    val email: String,
    val password: String
)

data class SignupPostRequest(
    val name: String,
    val email: String,
    val password: String,
    val confPassword: String
)