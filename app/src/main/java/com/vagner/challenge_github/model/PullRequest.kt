package com.vagner.challenge_github.model

import com.google.gson.annotations.SerializedName


data class PullRequest(

    val html_url: String,
    val title: String,
    val created_at: String,
    val body: String,
    @SerializedName("user") val owner: Owner
)
