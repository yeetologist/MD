package com.dicoding.parentpal.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dicoding.parentpal.R
import com.dicoding.parentpal.databinding.FragmentCalculatorBinding

class CalculatorFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentCalculatorBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCalculatorBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            btnImt.setOnClickListener(this@CalculatorFragment)
            btnEnergi.setOnClickListener(this@CalculatorFragment)
            btnBbi.setOnClickListener(this@CalculatorFragment)
            btnKesimpulan.setOnClickListener(this@CalculatorFragment)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_imt -> {
            }
            R.id.btn_energi -> {
            }
            R.id.btn_bbi -> {
            }
            R.id.btn_kesimpulan -> {
            }
        }
    }
}