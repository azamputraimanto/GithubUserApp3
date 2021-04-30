package com.dicoding.azam.submission2_githubuser.utils

import com.dicoding.azam.submission2_githubuser.models.DetailUser
import com.dicoding.azam.submission2_githubuser.models.User
import com.dicoding.azam.submission2_githubuser.models.UserResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("search/users")
    @Headers("Authorization: token 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")
    fun getSearchUser(
        @Query("q") query: String
    ) : Call<UserResponse>

    @GET("users/{username}")
    @Headers("Authorization: token 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")
    fun getDetailUser(
        @Path("username") username: String
    ) : Call<DetailUser>

    @GET("users/{username}/followers")
    @Headers("Authorization: token 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")
    fun getFollowers(
        @Path("username") username: String
    ) : Call<ArrayList<User>>

    @GET("users/{username}/following")
    @Headers("Authorization: token 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")
    fun getFollowing(
        @Path("username") username: String
    ) : Call<ArrayList<User>>
}