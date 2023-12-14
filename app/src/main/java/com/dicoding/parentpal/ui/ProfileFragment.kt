package com.dicoding.parentpal.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.dicoding.parentpal.R
import com.dicoding.parentpal.databinding.FragmentProfileBinding
import com.dicoding.parentpal.ui.setting.SettingsActivity

class ProfileFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentProfileBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater,container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnSetting.setOnClickListener(this@ProfileFragment)
            btnTerms.setOnClickListener(this@ProfileFragment)
            btnLogout.setOnClickListener(this@ProfileFragment)
        }
    }

    override fun onClick(v: View?) {
        when(v?.id){
            R.id.btn_setting -> {
                startActivity(Intent(requireActivity(), SettingsActivity::class.java))
            }
//            R.id.btn_terms -> {
//                startActivity(Intent(requireActivity(),TermsActivity::class.java))
//            }
//            R.id.btn_logout -> {
//                viewModel.logout()
//                startActivity(Intent(requireActivity(),LoginActivity::class.java))
//                requireActivity().finish()
//            }
        }
    }
}