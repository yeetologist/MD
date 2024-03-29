package com.dicoding.parentpal.data.local.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.parentpal.data.remote.response.ArticlesItem
import com.dicoding.parentpal.databinding.ItemNewsBinding
import com.dicoding.parentpal.util.loadImage

class NewsListAdapter(private val onItemClickListener: (ArticlesItem) -> Unit) :
    PagingDataAdapter<ArticlesItem, NewsListAdapter.MyViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = ItemNewsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val data = getItem(position)
        if (data != null) {
            holder.bind(data, onItemClickListener)
        }
    }

    class MyViewHolder(private val binding: ItemNewsBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var news: ArticlesItem
        fun bind(data: ArticlesItem, clickListener: (ArticlesItem) -> Unit) {
            this.news = data
            binding.tvItemQuote.text = data.title
            binding.tvItemAuthor.text = data.description
            itemView.setOnClickListener {
                clickListener(data)
            }
            data.urlToImage?.let { binding.ivItemNews.loadImage(it) }
            binding.ivItemNews.contentDescription = data.title
        }

        fun getArticles(): ArticlesItem = news
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<ArticlesItem>() {
            override fun areItemsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ArticlesItem, newItem: ArticlesItem): Boolean {
                return oldItem.url == newItem.url
            }
        }
    }
}