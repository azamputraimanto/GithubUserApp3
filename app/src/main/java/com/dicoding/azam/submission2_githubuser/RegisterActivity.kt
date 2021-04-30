package com.dicoding.azam.submission2_githubuser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        supportActionBar!!.hide()

        btn_save.setOnClickListener {
            if (register_username.text.trim().isNotEmpty() ||
                    register_email.text.trim().isNotEmpty() ||
                    register_password2.text.trim().isNotEmpty() ||
                    register_password1.text.trim().isNotEmpty()) {
                Toast.makeText(this, R.string.berhasil , Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, R.string.pesan_register , Toast.LENGTH_LONG).show()
            }
        }

        btn_login.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java).also {
                startActivity(it)
            }
        }

    }
}




