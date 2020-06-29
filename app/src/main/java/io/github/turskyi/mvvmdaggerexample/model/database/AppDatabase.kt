package io.github.turskyi.mvvmdaggerexample.model.database

import androidx.room.Database
import androidx.room.RoomDatabase
import io.github.turskyi.mvvmdaggerexample.model.Post
import io.github.turskyi.mvvmdaggerexample.model.PostDao

@Database(entities = [Post::class],version = 1)
abstract class AppDatabase : RoomDatabase(){
    abstract fun postDao(): PostDao
}