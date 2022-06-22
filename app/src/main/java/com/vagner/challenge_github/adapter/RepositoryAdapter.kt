package com.vagner.challenge_github.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vagner.challenge_github.databinding.ItemRepositoryBinding
import com.vagner.challenge_github.model.Repository

class RepositoryAdapter(private val onItemClick: (Repository) -> Unit) :
    RecyclerView.Adapter<RepositoryAdapter.ViewHolder>() {

    var listRepository = mutableListOf<Repository>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun getItemCount(): Int = listRepository.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemRepositoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listRepository[position], onItemClick)
    }


    class ViewHolder(private val binding: ItemRepositoryBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(repo: Repository, onItemClick: (Repository) -> Unit) {
            binding.textRepositoryName.text = repo.name
            binding.textRepositoryDescription.text = repo.description
            binding.textviewRepositoryOwnerUsername.text = repo.owner.login
            binding.textRepositoryStarCounter.text = repo.stargazers_count.toString()
            binding.textRepositoryForkCounter.text = repo.forks_count.toString()

            Glide.with(binding.imageRepositoryAvatar).load(repo.owner.avatar_url).circleCrop()
                .into(binding.imageRepositoryAvatar)


            itemView.setOnClickListener {
                onItemClick(repo)
            }

        }
    }


}