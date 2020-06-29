package io.github.turskyi.mvvmdaggerexample.injection

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import io.github.turskyi.mvvmdaggerexample.model.database.AppDatabase
import io.github.turskyi.mvvmdaggerexample.ui.PostListViewModel

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val activity: AppCompatActivity) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PostListViewModel::class.java)) {
            val db = Room.databaseBuilder(
                activity.applicationContext,
                AppDatabase::class.java,
                "post.db"
            ).build()
            return PostListViewModel(db.postDao()) as T
        }
        throw IllegalArgumentException("Unknown viewModel class")
    }
}