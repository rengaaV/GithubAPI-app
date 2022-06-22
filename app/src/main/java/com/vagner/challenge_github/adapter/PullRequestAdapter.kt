package com.vagner.challenge_github.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.vagner.challenge_github.adapter.PullRequestAdapter.ViewHolderPull
import com.vagner.challenge_github.databinding.ItemPullRequestBinding
import com.vagner.challenge_github.model.PullRequest

class PullRequestAdapter(private val onItemClick: (PullRequest) -> Unit) :
    RecyclerView.Adapter<ViewHolderPull>() {

    var listPull  = mutableListOf<PullRequest>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun getItemCount(): Int = listPull.size


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolderPull {
        return ViewHolderPull(
            ItemPullRequestBinding.inflate(
                LayoutInflater.from(
                    parent.context
                ), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PullRequestAdapter.ViewHolderPull, position: Int) {
        holder.bind(listPull[position], onItemClick)
    }

    inner class ViewHolderPull(private val binding: ItemPullRequestBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(pull: PullRequest, onItemClick: (PullRequest) -> Unit) {

            binding.textPullRequestTitle.text = pull.title
            binding.textPullRequestBody.text = pull.body
            binding.textPullRequestDate.text = pull.created_at
            binding.textPullRequestUsername.text = pull.owner.login

            Glide.with(binding.imagePullRequestAvatar).load(pull.owner.avatar_url).circleCrop()
                .into(binding.imagePullRequestAvatar)


            itemView.setOnClickListener {
                onItemClick(pull)
            }
        }

    }


}