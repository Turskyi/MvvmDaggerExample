package io.github.turskyi.mvvmdaggerexample.network

import io.github.turskyi.mvvmdaggerexample.model.Post
import io.reactivex.Observable
import retrofit2.http.GET

//TODO: 3
interface PostApi {

    @GET("/posts")
    fun getPosts(): Observable<List<Post>>

}