package io.github.turskyi.mvvmdaggerexample.ui

import android.util.Log
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.lifecycle.MutableLiveData
import io.github.turskyi.mvvmdaggerexample.R
import io.github.turskyi.mvvmdaggerexample.base.BaseViewModel
import io.github.turskyi.mvvmdaggerexample.model.Post
import io.github.turskyi.mvvmdaggerexample.model.PostDao
import io.github.turskyi.mvvmdaggerexample.network.PostApi
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

//TODO: 8
class PostListViewModel(private val dao: PostDao) : BaseViewModel() {

    @Inject
    lateinit var postApi: PostApi

    private lateinit var subscription: Disposable
    val adapter: PostListAdapter = PostListAdapter()
    val loadingVisibility: MutableLiveData<Int> = MutableLiveData()
    val errorClickListener = View.OnClickListener { loadPosts() }
    val errorMessage: MutableLiveData<Int> = MutableLiveData()

    init {
        loadPosts()
    }

    private fun loadPosts() {

        subscription = Observable.fromCallable {
            dao.all
        }
                /* we made a thread for a task above */
            .subscribeOn(Schedulers.io())
            .observeOn(Schedulers.io())
                /*start*/
            .concatMap { postDbList ->
                if (postDbList.isEmpty()){
                    /*download from api*/
                    postApi.getPosts().concatMap {postApiList ->
                        /*add to db*/
                        dao.insertAll(*postApiList.toTypedArray())
                        /*add to observable to show in main activity*/
                        Observable.just(postApiList)
                    }
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                } else {
                    /*show from db*/
                    Observable.just(postDbList)
                }
            }
                /*stop*/
            .doOnSubscribe { onRetrievePostListStart() }
            .doOnTerminate { onRetrievePostListFinish() }
            .subscribe(
                {posts-> onRetrievePostListSuccess(posts) },
                {
                    onRetrievePostListError(it)
                }
            )
    }

    private fun onRetrievePostListStart() {
        Log.d("PostListViewModel", "onRetrievePostListStart")
        loadingVisibility.postValue(VISIBLE)
    }

    private fun onRetrievePostListFinish() {
        Log.d("PostListViewModel", "onRetrievePostListFinish")
        loadingVisibility.postValue(GONE)
    }

    private fun onRetrievePostListSuccess(posts: List<Post>) {
        Log.d("PostListViewModel", "onRetrievePostListSuccess")
        adapter.updatePostList(posts)
    }

    private fun onRetrievePostListError(throwable: Throwable) {
        Log.d("PostListViewModel", "onRetrievePostListError: ${throwable.message}")
        errorMessage.postValue(R.string.post_error)
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }
}