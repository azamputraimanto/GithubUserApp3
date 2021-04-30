package com.dicoding.azam.submission2_githubuser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Toast
import com.dicoding.azam.submission2_githubuser.databinding.ActivityMainBinding
import com.dicoding.azam.submission2_githubuser.databinding.ActivitySplashScreenBinding
import kotlinx.android.synthetic.main.activity_main.*

class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Handler().postDelayed({
                binding.progressBar2.visibility = View.GONE
                showProgressBar(false)
            startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }, 5000)){
        } else {
            binding.noConnection.visibility = View.GONE
            showProgressBar(false)
        }

    }

    private fun showProgressBar(state : Boolean){
        if (state){
            binding.progressBar2.visibility = View.VISIBLE
        }else{
            binding.progressBar2.visibility = View.GONE
        }
    }
}
