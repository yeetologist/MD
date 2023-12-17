package com.dicoding.parentpal.ui.news

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.parentpal.data.local.paging.LoadingStateAdapter
import com.dicoding.parentpal.data.local.paging.NewsListAdapter
import com.dicoding.parentpal.data.remote.response.ArticlesItem
import com.dicoding.parentpal.databinding.FragmentNewsBinding
import com.dicoding.parentpal.ui.ViewModelFactory

class NewsFragment : Fragment() {
    private lateinit var binding: FragmentNewsBinding
    private val newsViewModel: NewsViewModel by viewModels {
        ViewModelFactory(requireContext())
    }
    private val newsAdapter: NewsListAdapter by lazy {
        NewsListAdapter(::onNewsClick)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvListStories.layoutManager = LinearLayoutManager(requireContext())
        getData()
    }

    private fun onNewsClick(articlesItem: ArticlesItem) {
        val intent = Intent(requireContext(), DetailNewsActivity::class.java)
        intent.putExtra(DetailNewsActivity.EXTRA_ARTICLE_NEWS, articlesItem)
        startActivity(intent)
    }

    private fun getData() {
        val adapter = newsAdapter
        binding.rvListStories.adapter = adapter.withLoadStateFooter(
            footer = LoadingStateAdapter {
                adapter.retry()
            }
        )
        newsViewModel.news.observe(viewLifecycleOwner) {
            adapter.submitData(viewLifecycleOwner.lifecycle, it)
        }
    }
}