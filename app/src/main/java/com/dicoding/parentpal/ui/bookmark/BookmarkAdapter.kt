package com.dicoding.parentpal.ui.bookmark

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.parentpal.data.local.database.bookmark.BookmarkEntity
import com.dicoding.parentpal.databinding.ItemNewsBinding
import com.dicoding.parentpal.util.loadImage

class BookmarkAdapter(private val onItemClickListener: (BookmarkEntity) -> Unit) :
    ListAdapter<BookmarkEntity, BookmarkAdapter.MyViewHolder>(DIFF_CALLBACK) {

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
        private lateinit var news: BookmarkEntity
        fun bind(data: BookmarkEntity, clickListener: (BookmarkEntity) -> Unit) {
            this.news = data
            binding.tvItemQuote.text = data.title
            binding.tvItemAuthor.text = data.description
            itemView.setOnClickListener {
                clickListener(data)
            }
            data.urlToImage?.let { binding.ivItemNews.loadImage(it) }
            binding.ivItemNews.contentDescription = data.title
        }

        fun getArticles(): BookmarkEntity = news
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<BookmarkEntity>() {
            override fun areItemsTheSame(
                oldItem: BookmarkEntity,
                newItem: BookmarkEntity
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: BookmarkEntity,
                newItem: BookmarkEntity
            ): Boolean {
                return oldItem.url == newItem.url
            }
        }
    }
}