package com.dicoding.azam.consumerapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.azam.consumerapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: UserAdapter
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        binding.apply {
            rvDataFavorite.setHasFixedSize(true)
            rvDataFavorite.layoutManager = LinearLayoutManager(this@MainActivity)
            rvDataFavorite.adapter = adapter

            supportActionBar?.title = resources.getString(R.string.favorite)
        }

        viewModel.setFavoriteUser(this)

        viewModel.getFavoriteUser()?.observe(this, {
            if(it != null){
                if (it.isEmpty()){
                    binding.noData.visibility = View.VISIBLE
                    binding.rvDataFavorite.visibility = View.GONE
                    showProgressBar(false)
                    Toast.makeText(this, resources.getString(R.string.message), Toast.LENGTH_LONG).show()
                }else{
                    adapter.setList(it)
                    binding.rvDataFavorite.visibility = View.VISIBLE
                    binding.noData.visibility = View.GONE
                    showProgressBar(false)
                }
            }
        })

//        viewModel.getFavoriteUser()?.observe(this, {
//            if (it != null) {
//                adapter.setList(it)
//            }
//        })
    }

    private fun showProgressBar(state : Boolean){
        if (state){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }
}