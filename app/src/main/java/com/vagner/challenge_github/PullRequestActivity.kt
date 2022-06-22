package com.vagner.challenge_github

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.vagner.challenge_github.adapter.PullRequestAdapter
import com.vagner.challenge_github.databinding.ActivityPullRequestBinding
import com.vagner.challenge_github.viewmodel.PullRequestViewModel

class PullRequestActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPullRequestBinding

    private lateinit var viewModel: PullRequestViewModel

    private val adapter = PullRequestAdapter {
        val url = it.html_url
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private var owner = ""

    private var repo = ""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPullRequestBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(PullRequestViewModel::class.java)

        binding.recyclerPullRequest.adapter = adapter
        binding.recyclerPullRequest.layoutManager = LinearLayoutManager(this)
        binding.recyclerPullRequest.setHasFixedSize(true)

        owner = intent.getStringExtra("owner").toString()
        repo = intent.getStringExtra("repo").toString()

        setSupportActionBar(binding.toolbarPullRequest)
        binding.toolbarPullRequest.title = repo

        launcher()
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
          android.R.id.home -> finish()
        }
        return true

    }

    private fun launcher() {
        getItemPull()

    }

    private fun getItemPull() {
        successPull()
        errorPull()
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun successPull() {
        viewModel.successRepository.observe(this) {
            adapter.listPull.addAll(it)
            adapter.notifyDataSetChanged()
            binding.progressBarPullRequest.visibility = View.GONE

        }

        viewModel.getItemPull(owner, repo)
    }

    private fun errorPull() {
        viewModel.errorRepository.observe(this) {
            Toast.makeText(this, R.string.unknown_error, Toast.LENGTH_SHORT).show()
        }
    }


}


