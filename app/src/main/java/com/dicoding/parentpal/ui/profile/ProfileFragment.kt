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
import com.dicoding.parentpal.ui.auth.LoginActivity
import com.dicoding.parentpal.ui.bookmark.BookmarkActivity
import com.dicoding.parentpal.ui.bookmark.HistoryActivity
import com.dicoding.parentpal.ui.setting.SettingsActivity
import com.dicoding.parentpal.util.PreferenceManager

class ProfileFragment : Fragment(), View.OnClickListener {

    private lateinit var binding: FragmentProfileBinding

    private val newsViewModel: ProfileViewModel by viewModels {
        ViewModelFactory(requireContext())
    }

    private lateinit var preferenceManager: PreferenceManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        preferenceManager = PreferenceManager(requireContext())

        binding.apply {
            btnSetting.setOnClickListener(this@ProfileFragment)
            btnLogout.setOnClickListener(this@ProfileFragment)
            btnBookmark.setOnClickListener(this@ProfileFragment)
            btnHistory.setOnClickListener(this@ProfileFragment)
            tvBookmark.setOnClickListener(this@ProfileFragment)
            tvHistory.setOnClickListener(this@ProfileFragment)
        }

        newsViewModel.getAllBookmarks().observe(viewLifecycleOwner) {
            binding.tvBookmark.text = it.size.toString()
        }

        newsViewModel.getHistory().observe(viewLifecycleOwner) {
            binding.tvHistory.text = it.size.toString()
        }

        binding.tvUsername.text = preferenceManager.getPreferences()?.name ?: ""
        binding.tvEmail.text = preferenceManager.getPreferences()?.email ?: ""

    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn_setting -> {
                startActivity(Intent(requireActivity(), SettingsActivity::class.java))
            }

            R.id.btn_bookmark, R.id.tv_bookmark -> {
                startActivity(Intent(requireContext(), BookmarkActivity::class.java))
            }

            R.id.btn_history, R.id.tv_history -> {
                startActivity(Intent(requireContext(), HistoryActivity::class.java))
            }

            R.id.btn_logout -> {
                startActivity(Intent(requireActivity(), LoginActivity::class.java))
                preferenceManager.clearPreferences()
                requireActivity().finish()
            }
        }
    }
}