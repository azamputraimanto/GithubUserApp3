package com.dicoding.azam.submission2_githubuser

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.SearchView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.azam.submission2_githubuser.adapter.UserAdapter
import com.dicoding.azam.submission2_githubuser.databinding.ActivityMainBinding
import com.dicoding.azam.submission2_githubuser.models.User
import com.dicoding.azam.submission2_githubuser.viewmodels.UserViewModel
import kotlinx.android.synthetic.main.activity_main.*
import java.util.ArrayList

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: UserViewModel
    private lateinit var adapter: UserAdapter
    private var list = ArrayList<User>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: User) {
                Toast.makeText(this@MainActivity, data.login, Toast.LENGTH_SHORT).show()
                Intent(this@MainActivity, DetailActivity::class.java).also {
                    it.putExtra(DetailActivity.EXTRA_USERNAME, data.login)
                    it.putExtra(DetailActivity.EXTRA_ID, data.id)
                    it.putExtra(DetailActivity.EXTRA_AVATAR, data.avatar_url)
                    it.putExtra(DetailActivity.EXTRA_HTML_URL, data.html_url)
                    startActivity(it)
                }
            }
        })

        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(UserViewModel::class.java)

        binding.apply {
            rvDataGithub.layoutManager = LinearLayoutManager( this@MainActivity)
            rvDataGithub.setHasFixedSize(true)
            rvDataGithub.adapter = adapter

            supportActionBar?.title = resources.getString(R.string.github_user)

            btnIconPencarian.setOnClickListener {
                searchUser()
            }

            searchQuery.setOnKeyListener { v, keyCode, event ->
                if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
                    searchUser()
                    return@setOnKeyListener true
                }
                return@setOnKeyListener false
            }
        }

        viewModel.getSearchUsers().observe(this, {
            if(it != null){
                if (it.isEmpty()){
                    binding.noData.visibility = View.VISIBLE
                    binding.rvDataGithub.visibility = View.GONE
                    showProgressBar(false)
                    Toast.makeText(this, resources.getString(R.string.message), Toast.LENGTH_LONG).show()
                }else{
                    adapter.setList(it)
                    binding.rvDataGithub.visibility = View.VISIBLE
                    binding.noData.visibility = View.GONE
                    showProgressBar(false)
                }
            }
        })
    }

    private fun searchUser() {
        binding.apply {
            val query = searchQuery.text.toString()
            if (query.isEmpty()) return
            showProgressBar(true)
            viewModel.setSearchUser(query)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu_home, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.favorite -> {
                Intent(this, FavoriteActivity::class.java).also {
                    startActivity(it)
                }
            }
            R.id.setting -> {
                Intent(this, SettingActivity::class.java).also {
                    startActivity(it)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.rvDataGithub.adapter = null
    }

    private fun showProgressBar(state : Boolean){
        if (state){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }
}