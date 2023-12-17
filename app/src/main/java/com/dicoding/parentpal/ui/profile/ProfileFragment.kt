package com.dicoding.parentpal.ui.profile

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.dicoding.parentpal.R
import com.dicoding.parentpal.databinding.FragmentProfileBinding
import com.dicoding.parentpal.ui.ViewModelFactory
import com.dicoding.parentpal.ui.bookmark.BookmarkActivity
import com.dicoding.parentpal.ui.bookmark.HistoryActivity
import com.dicoding.parentpal.ui.setting.SettingsActivity

class ProfileFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentProfileBinding

    private val newsViewModel: ProfileViewModel by viewModels {
        ViewModelFactory(requireContext())
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            btnSetting.setOnClickListener(this@ProfileFragment)
            btnTerms.setOnClickListener(this@ProfileFragment)
            btnLogout.setOnClickListener(this@ProfileFragment)
        }

        newsViewModel.getAllBookmarks().observe(viewLifecycleOwner) {
            binding.tvBookmark.text = it.size.toString()
        }

        newsViewModel.getHistory().observe(viewLifecycleOwner) {
            binding.tvHistory.text = it.size.toString()
        }

        binding.tvBookmark.setOnClickListener {
            startActivity(Intent(requireContext(), BookmarkActivity::class.java))
        }

        binding.tvHistory.setOnClickListener {
            startActivity(Intent(requireContext(), HistoryActivity::class.java))
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
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