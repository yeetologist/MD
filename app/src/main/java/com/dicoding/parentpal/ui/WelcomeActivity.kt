package com.dicoding.parentpal.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.parentpal.databinding.ActivityWelcomeBinding
import com.dicoding.parentpal.ui.auth.LoginActivity
import com.dicoding.parentpal.ui.auth.SignupActivity
import com.dicoding.parentpal.util.PreferenceManager

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding
    private lateinit var preferenceManager: PreferenceManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        preferenceManager = PreferenceManager(this)

        if (preferenceManager.getPreferences() != null) {
            startActivity(Intent(this, MainActivity::class.java))
        }

        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnWelcomeLogin.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
        }

        binding.btnWelcomeSignup.setOnClickListener {
            startActivity(Intent(this, SignupActivity::class.java))
        }
    }
}

