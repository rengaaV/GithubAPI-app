package com.vagner.challenge_github.model

data class Repository(

    val name: String,
    val fullName: String,
    val owner: Owner,
    val description: String,
    val stargazers_count: Int,
    val forks_count: Int

)