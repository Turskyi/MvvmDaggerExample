package io.github.turskyi.mvvmdaggerexample.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface PostDao {
    @Insert
    fun insertAll(vararg post: Post)

    @get:Query("SELECT * FROM post")
    val all: List<Post>
}