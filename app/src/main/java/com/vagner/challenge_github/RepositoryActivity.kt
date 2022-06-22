package com.vagner.challenge_github

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.vagner.challenge_github.adapter.RepositoryAdapter
import com.vagner.challenge_github.databinding.ActivityRepositoryBinding
import com.vagner.challenge_github.utils.Constants.OWNER
import com.vagner.challenge_github.utils.Constants.REPO
import com.vagner.challenge_github.viewmodel.RepositoryViewModel


class RepositoryActivity : AppCompatActivity()   {

    private lateinit var binding: ActivityRepositoryBinding

    private lateinit var viewModel: RepositoryViewModel

    private val layoutManager = LinearLayoutManager(this)

    private var adapter = RepositoryAdapter {
        val intent = Intent(applicationContext, PullRequestActivity::class.java)
        intent.putExtra(OWNER, it.owner.login)
        intent.putExtra(REPO, it.name)
        startActivity(intent)

    }

    var page = 1

    private var loading = false
    private var lastPosition = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRepositoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(RepositoryViewModel::class.java)

        setSupportActionBar(binding.toolbarRepository)

        binding.recyclerRepository.layoutManager = layoutManager
        binding.recyclerRepository.adapter = adapter
        binding.recyclerRepository.setHasFixedSize(true)


        launcher()
    }


    private fun launcher() {
        getRepositories()
        onScrollListener()

    }

    private fun onScrollListener() {
        binding.recyclerRepository.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                val lastCompleteItem = layoutManager.findLastVisibleItemPosition()


                if (!loading) {
                    if (lastCompleteItem == adapter.listRepository.size - 1) {
                        lastPosition = adapter.listRepository.size + 1
                        page += 1
                        getRepositories()
                    }
                    binding.progressBarRepository.visibility = View.VISIBLE
                }

            }
        })
    }


    private fun getRepositories() {
        successRepositories()
        errorRepositories()
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun successRepositories() {


        viewModel.successRepository.observe(this) {

            for (i in it) {
                if (i !in adapter.listRepository) {

                    adapter.listRepository.addAll(it)
                    adapter.notifyDataSetChanged()
                    binding.progressBarRepository.visibility = View.GONE
                }
            }
        }

        viewModel.getItemRepository(page)


    }

    private fun errorRepositories() {
        viewModel.errorRepository.observe(this) {
            Toast.makeText(this, R.string.unknown_error, Toast.LENGTH_SHORT).show()
        }
    }

}