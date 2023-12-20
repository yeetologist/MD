package com.dicoding.parentpal.ui.auth

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.parentpal.data.remote.Result
import com.dicoding.parentpal.data.remote.response.SignupPostResponse
import com.dicoding.parentpal.databinding.ActivitySignupBinding
import com.dicoding.parentpal.ui.ViewModelFactory
import com.dicoding.parentpal.util.showSnackbarShort

class SignupActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private val signupViewModel: SignupViewModel by viewModels {
        ViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupAction()
        binding.btnSignupLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }
        binding.btnSignupBack.setOnClickListener {
            finish()
        }
    }

    private fun setupAction() {
        binding.btnSignup.setOnClickListener {
            val name = binding.etSignupUsername.text.toString()
            val email = binding.etSignupEmail.text.toString()
            val password = binding.etSignupPassword.text.toString()
            val passwordConf = binding.etSignupPasswordConfirm.text.toString()

            signupViewModel.register(name, email, password, passwordConf).observe(this) {
                if (it != null) {
                    when (it) {
                        is Result.Loading -> {
                            showLoading(true)
                        }

                        is Result.Success -> {
                            showLoading(false)
                            processSignup(it.data)
                        }

                        is Result.Error -> {
                            showLoading(true)
                            showSnackbarShort(it.error, binding.root)
                        }
                    }
                }
            }
        }
    }

    private fun processSignup(data: SignupPostResponse) {
        showSnackbarShort(data.msg, binding.root)
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    private fun showLoading(bool: Boolean) {
        binding.vLayer.visibility = if (bool) View.VISIBLE else View.GONE
        binding.progressBar.visibility = if (bool) View.VISIBLE else View.GONE
    }
}