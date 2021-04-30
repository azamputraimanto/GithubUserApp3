package com.dicoding.azam.submission2_githubuser.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.azam.submission2_githubuser.DetailActivity
import com.dicoding.azam.submission2_githubuser.R
import com.dicoding.azam.submission2_githubuser.adapter.UserAdapter
import com.dicoding.azam.submission2_githubuser.databinding.FragmentFollowersBinding
import com.dicoding.azam.submission2_githubuser.models.User
import com.dicoding.azam.submission2_githubuser.viewmodels.FollowersViewModel

class FollowersFragment : Fragment() {

    private var _binding : FragmentFollowersBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: FollowersViewModel
    private lateinit var adapter: UserAdapter
    private lateinit var username: String

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val args = arguments
        username = args?.getString(DetailActivity.EXTRA_USERNAME).toString()
        _binding = FragmentFollowersBinding.inflate(inflater, container, false)
        val view = binding.root

        adapter = UserAdapter()
        adapter.notifyDataSetChanged()

        adapter.setOnItemClickCallback(object : UserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: User) {
                startActivity(Intent(activity, DetailActivity::class.java).also {
                    it.putExtra(DetailActivity.EXTRA_USERNAME, data.login)
                    it.putExtra(DetailActivity.EXTRA_ID, data.id)
                    it.putExtra(DetailActivity.EXTRA_AVATAR, data.avatar_url)
                    it.putExtra(DetailActivity.EXTRA_HTML_URL, data.html_url)
                })
            }
        })

        binding.apply {
            rvDataGithub.setHasFixedSize(true)
            rvDataGithub.layoutManager = LinearLayoutManager(activity)
            rvDataGithub.adapter = adapter
        }

        showProgressBar(true)
        viewModel = ViewModelProvider(this, ViewModelProvider.NewInstanceFactory()).get(FollowersViewModel::class.java)
        viewModel.setFollowers(username)
        viewModel.getFollowers().observe(viewLifecycleOwner, {
            if(it != null){
                if (it.isEmpty()){
                    binding.noData.visibility = View.VISIBLE
                    binding.rvDataGithub.visibility = View.GONE
                    showProgressBar(false)
                }else{
                    adapter.setList(it)
                    binding.rvDataGithub.visibility = View.VISIBLE
                    binding.noData.visibility = View.GONE
                    showProgressBar(false)
                }
            }
        })

        // Inflate the layout for this fragment
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showProgressBar(state : Boolean){
        if (state){
            binding.progressBar.visibility = View.VISIBLE
        }else{
            binding.progressBar.visibility = View.GONE
        }
    }
}