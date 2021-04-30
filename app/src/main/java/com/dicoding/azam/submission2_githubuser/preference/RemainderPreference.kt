package com.dicoding.azam.submission2_githubuser.preference

import android.content.Context
import com.dicoding.azam.submission2_githubuser.models.Remainder

class RemainderPreference(context: Context) {
    companion object {
        const val PREFS_NAME = "reminder_pref"
        private const val REMINDER = "isRemind"
    }

    private val preference= context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setReminder(value: Remainder) {
        val editor = preference.edit()
        editor.putBoolean(REMINDER, value.isRemainder)
        editor.apply()
    }

    fun getReminder() : Remainder {
        val model = Remainder()
        model.isRemainder = preference.getBoolean(REMINDER, false)
        return model
    }
}