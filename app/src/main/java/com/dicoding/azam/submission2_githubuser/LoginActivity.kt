package com.dicoding.azam.submission2_githubuser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        supportActionBar!!.hide()

        val login_username = findViewById<EditText>(R.id.login_username)
        val login_password = findViewById<EditText>(R.id.login_password)
        val btn_login = findViewById<Button>(R.id.btn_login)
        val btn_reset = findViewById<Button>(R.id.btn_reset)
        val btn_register = findViewById<Button>(R.id.btn_register)

        btn_reset.setOnClickListener {
            login_username.setText("")
            login_password.setText("")
        }

        btn_login.setOnClickListener {
            if (login_username.text.trim().isNotEmpty() || login_password.text.trim().isNotEmpty()) {
                Toast.makeText(this, login_username.text, Toast.LENGTH_LONG).show()
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else{
                Toast.makeText(this, R.string.pesan , Toast.LENGTH_LONG).show()
            }
        }

        btn_register.setOnClickListener {
            Intent(this, RegisterActivity::class.java).also {
                startActivity(it)
            }
        }
    }
}