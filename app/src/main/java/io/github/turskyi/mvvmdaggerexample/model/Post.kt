package io.github.turskyi.mvvmdaggerexample.model

import androidx.room.Entity
import androidx.room.PrimaryKey

//TODO: 2
@Entity
data class Post(
    val userId: Int,
    @field:PrimaryKey val id: Int,
    val title: String,
    val body: String
)