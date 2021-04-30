package com.dicoding.azam.submission2_githubuser.localdatabase

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "favorite_user")
data class FavoriteUser (
    @PrimaryKey
    val id: Int,
    val login: String,
    val avatar_url: String,
    val html_url: String
) : Serializable