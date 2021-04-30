package com.dicoding.azam.submission2_githubuser.models

data class DetailUser(
    val login: String,
    val id: Int,
    val avatar_url: String,
    val html_url: String,
    val name: String,
    val company : String,
    val location : String,
    val public_repos : Int,
    val followers: Int,
    val following: Int
)
