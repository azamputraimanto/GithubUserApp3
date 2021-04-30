package com.dicoding.azam.submission2_githubuser.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dicoding.azam.submission2_githubuser.models.User
import com.dicoding.azam.submission2_githubuser.utils.UserRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FollowingViewModel : ViewModel() {

    val listDataFollowing = MutableLiveData<ArrayList<User>>()

    fun setFollowing(username: String) {
        UserRetrofit.apiInstance
            .getFollowing(username)
            .enqueue(object : Callback<ArrayList<User>> {
                override fun onResponse(
                    call: Call<ArrayList<User>>,
                    response: Response<ArrayList<User>>
                ) {
                    if (response.isSuccessful) {
                        listDataFollowing.postValue(response.body())
                    }
                }

                override fun onFailure(call: Call<ArrayList<User>>, t: Throwable) {
                    t.message?.let { Log.d("Failure", it) }
                }
            })
    }

    fun getFollowing(): LiveData<ArrayList<User>> {
        return listDataFollowing
    }
}