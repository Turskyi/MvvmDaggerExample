package io.github.turskyi.mvvmdaggerexample.ui

import androidx.lifecycle.MutableLiveData
import io.github.turskyi.mvvmdaggerexample.base.BaseViewModel
import io.github.turskyi.mvvmdaggerexample.model.Post

class PostViewModel : BaseViewModel() {
    val postTitle = MutableLiveData<String>()
    val postBody = MutableLiveData<String>()

    fun bind(post: Post) {
        postTitle.postValue(post.title)
        postBody.postValue(post.body )
    }
}