package com.dicoding.parentpal.ui.result

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dicoding.parentpal.R
import com.dicoding.parentpal.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {

    lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val predResult = intent.getIntExtra(EXTRA_RESULT, -1)
        setupView(predResult)
    }

    private fun setupView(predResult: Int) {
        when (predResult) {
            0 -> {
                binding.tvResultHeader.text = getString(R.string.header_normal)
                binding.tvDetailResultLabel.text = getString(R.string.label_normal)
                binding.tvDetailResult.text = getString(R.string.detail_normal)
                binding.tvRecommendation.text = getString(R.string.konsultasi_gizibaik)
                binding.ivResult.setImageResource(R.drawable.gizibaik)
            }

            1 -> {
                binding.tvResultHeader.text = getString(R.string.header_overweight)
                binding.tvDetailResultLabel.text = getString(R.string.label_overweight)
                binding.tvDetailResult.text = getString(R.string.detail_overweight)
                binding.tvRecommendation.text = getString(R.string.konsultasi_overweight)
                binding.ivResult.setImageResource(R.drawable.overweight)
            }

            2 -> {
                binding.tvResultHeader.text = getString(R.string.header_underweight)
                binding.tvDetailResultLabel.text = getString(R.string.label_underweight)
                binding.tvDetailResult.text = getString(R.string.detail_underweight)
                binding.tvRecommendation.text = getString(R.string.konsultasi_underweight)
                binding.ivResult.setImageResource(R.drawable.underweight)
            }

            else -> {
                binding.tvResultHeader.text = getString(R.string.header_stunting)
                binding.tvDetailResultLabel.text = getString(R.string.label_underweight)
                binding.tvDetailResult.text = getString(R.string.detail_stunting)
                binding.tvRecommendation.text = getString(R.string.konsultasi_stunting)
                binding.ivResult.setImageResource(R.drawable.stunting)
            }
        }
    }

    companion object {
        const val EXTRA_RESULT = "EXTRA_RESULT"
    }
}