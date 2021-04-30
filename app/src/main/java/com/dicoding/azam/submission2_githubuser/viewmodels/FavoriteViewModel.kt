package com.dicoding.azam.submission2_githubuser.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.dicoding.azam.submission2_githubuser.localdatabase.FavoriteUser
import com.dicoding.azam.submission2_githubuser.localdatabase.FavoriteUserDao
import com.dicoding.azam.submission2_githubuser.localdatabase.UserDatabase

class FavoriteViewModel(application: Application): AndroidViewModel(application) {

    private var userDao: FavoriteUserDao?
    private var userDb: UserDatabase?

    init {
        userDb = UserDatabase.getDatabase(application)
        userDao = userDb?.favoriteUserDao()
    }

    fun getFavoriteUser(): LiveData<List<FavoriteUser>>? {
        return userDao?.getFavoriteUser()
    }
}