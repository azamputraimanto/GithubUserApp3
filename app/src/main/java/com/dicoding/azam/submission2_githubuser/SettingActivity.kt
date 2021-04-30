package com.dicoding.azam.submission2_githubuser

import android.app.AlarmManager
import android.content.BroadcastReceiver
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.CalendarContract
import android.provider.Settings
import android.view.View
import com.dicoding.azam.submission2_githubuser.databinding.ActivitySettingBinding
import com.dicoding.azam.submission2_githubuser.models.Remainder
import com.dicoding.azam.submission2_githubuser.preference.RemainderPreference
import com.dicoding.azam.submission2_githubuser.receiver.AlarmReceiver
import kotlinx.android.synthetic.main.activity_setting.*

class SettingActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var binding: ActivitySettingBinding
    private lateinit var remainder: Remainder
    private lateinit var alarmReceiver: AlarmReceiver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySettingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBahasa.setOnClickListener(this)
        binding.btnLogout.setOnClickListener(this)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = getString(R.string.setting)

        val remainderPreference = RemainderPreference(this)
        binding.switch1.isChecked = remainderPreference.getReminder().isRemainder

        alarmReceiver = AlarmReceiver()

        binding.switch1.setOnCheckedChangeListener { buttonView, isCheked ->
            if (isCheked) {
                saveRemainder(true)
                alarmReceiver.setRepeatingAlarm(this,
                    resources.getString(R.string.repeating_alarm),
                    resources.getString(R.string.time),
                    resources.getString(R.string.github_remainder))
            } else {
                saveRemainder(false)
                alarmReceiver.cancelAlarm(this)
            }
        }
    }

    private fun saveRemainder(state: Boolean) {
        val remainderPreference = RemainderPreference(this)
        remainder = Remainder()
        remainder.isRemainder = state
        remainderPreference.setReminder(remainder)
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.btn_bahasa -> {
                val intent = Intent(Settings.ACTION_LOCALE_SETTINGS)
                startActivity(intent)
            }

            R.id.btn_logout -> {
                Intent(this, LoginActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}