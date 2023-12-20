package com.dicoding.parentpal.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.parentpal.data.remote.Result
import com.dicoding.parentpal.data.remote.response.LoginResponse
import com.dicoding.parentpal.databinding.ActivityLoginBinding
import com.dicoding.parentpal.ui.MainActivity
import com.dicoding.parentpal.ui.ViewModelFactory
import com.dicoding.parentpal.util.PreferenceManager
import com.dicoding.parentpal.util.showSnackbarShort


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val loginViewModel: LoginViewModel by viewModels {
        ViewModelFactory(this)
    }

    private lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupAction()
        preferenceManager = PreferenceManager(this)
        binding.btnSignup.setOnClickListener {
            startActivity(Intent(this@LoginActivity, SignupActivity::class.java))
        }
        binding.btnLoginBack.setOnClickListener {
            finish()
        }
    }


    private fun setupAction() {
        binding.btnLogin.setOnClickListener {
            val email = binding.etLoginEmail.text.toString()
            val password = binding.etLoginPassword.text.toString()

            loginViewModel.postLogin(email, password).observe(this) {
                if (it != null) {
                    when (it) {
                        is Result.Loading -> {
                            showLoading(true)
                        }

                        is Result.Success -> {
                            showLoading(false)
                            processLogin(it.data)
                        }

                        is Result.Error -> {
                            showLoading(false)
                            showSnackbarShort(it.error, binding.root)
                        }
                    }
                }
            }
        }
    }

    private fun processLogin(data: LoginResponse) {
        val email = binding.etLoginEmail.text.toString()
        loginViewModel.getUsers(data.accessToken).observe(this) {
            if (it != null) {
                when (it) {
                    is Result.Loading -> {
                        showLoading(true)
                    }

                    is Result.Success -> {
                        showLoading(false)
                        it.data.forEach { item ->
                            if (item.email == email) {
                                preferenceManager.savePreferences(item)
                                startActivity(Intent(this, MainActivity::class.java))
                                finish()
                            }
                        }
                    }

                    is Result.Error -> {
                        showLoading(false)
                        showSnackbarShort(it.error, binding.root)
                    }
                }
            }
        }
    }

    private fun showLoading(bool: Boolean) {
        binding.vLayer.visibility = if (bool) View.VISIBLE else View.GONE
        binding.progressBar.visibility = if (bool) View.VISIBLE else View.GONE
    }

}