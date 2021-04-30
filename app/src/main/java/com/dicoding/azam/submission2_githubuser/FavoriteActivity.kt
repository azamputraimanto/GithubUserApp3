package com.dicoding.azam.submission2_githubuser

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.azam.submission2_githubuser.adapter.UserAdapter
import com.dicoding.azam.submission2_githubuser.databinding.ActivityFavoriteBinding
import com.dicoding.azam.submission2_githubuser.localdatabase.FavoriteUser
import com.dicoding.azam.submission2_githubuser.models.User
import com.dicoding.azam.submission2_githubuser.viewmodels.FavoriteViewModel
import kotlinx.android.synthetic.main.activity_favorite.*

class FavoriteActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFavoriteBinding
    private lateinit var adapter: UserAdapter
    private lateinit var viewModel: FavoriteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        viewModel = ViewModelProvider(this).get(FavoriteViewModel::class.java)

        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: User) {
                Toast.makeText(this@FavoriteActivity, data.login, Toast.LENGTH_SHORT).show()
                Intent(this@FavoriteActivity, DetailActivity::class.java).also {
                    it.putExtra(DetailActivity.EXTRA_USERNAME, data.login)
                    it.putExtra(DetailActivity.EXTRA_ID, data.id)
                    it.putExtra(DetailActivity.EXTRA_AVATAR, data.avatar_url)
                    it.putExtra(DetailActivity.EXTRA_HTML_URL, data.html_url)
                    startActivity(it)
                }
            }
        })

        binding.apply {
            rvDataFavorite.setHasFixedSize(true)
            rvDataFavorite.layoutManager = LinearLayoutManager(this@FavoriteActivity)
            rvDataFavorite.adapter = adapter

            supportActionBar?.setDisplayHomeAsUpEnabled(true)
            supportActionBar?.title = resources.getString(R.string.favorite)
        }

        viewModel.getFavoriteUser()?.observe(this, {
            if(it != null){
                if (it.isEmpty()){
                    binding.noData.visibility = View.VISIBLE
                    binding.rvDataFavorite.visibility = View.GONE
                    showProgressBar(false)
                    Toast.makeText(this, resources.getString(R.string.message), Toast.LENGTH_LONG).show()
                }else{
                    val list = mapList(it)
                    adapter.setList(list)
                    binding.rvDataFavorite.visibility = View.VISIBLE
                    binding.noData.visibility = View.GONE
                    showProgressBar(false)
                }
            }
        })

//        viewModel.getFavoriteUser()?.observe(this, {
//            if (it != null) {
//                val list = mapList(it)
//                adapter.setList(list)
//            }
//        })
    }

    private fun mapList(users: List<FavoriteUser>): ArrayList<User> {
        val listUsers = ArrayList<User>()
        for (user in users) {
            val userMapped = User(
                user.login,
                user.id,
                user.avatar_url,
                user.html_url
            )
            listUsers.add(userMapped)
        }
        return listUsers
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onDestroy() {
        super.onDestroy()
        binding.rvDataFavorite.adapter = null
    }

    private fun showProgressBar(state : Boolean){
        if (state){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }
}