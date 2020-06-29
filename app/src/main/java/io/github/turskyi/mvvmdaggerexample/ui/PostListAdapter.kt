package io.github.turskyi.mvvmdaggerexample.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import io.github.turskyi.mvvmdaggerexample.R
import io.github.turskyi.mvvmdaggerexample.databinding.ItemPostBinding
import io.github.turskyi.mvvmdaggerexample.model.Post

class PostListAdapter : RecyclerView.Adapter<PostListAdapter.ViewHolder>() {

    private lateinit var listPost: List<Post>
    fun updatePostList(updatedListPost: List<Post>) {
        this.listPost = updatedListPost
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = DataBindingUtil.inflate<ItemPostBinding>(
            LayoutInflater.from(parent.context),
            R.layout.item_post,
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return if (::listPost.isInitialized) {
            listPost.size
        } else 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listPost[position])
    }

    class ViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        private val viewModel: PostViewModel = PostViewModel()

        fun bind(post: Post) {
            viewModel.bind(post)
            binding.viewModel = viewModel
        }
    }
}