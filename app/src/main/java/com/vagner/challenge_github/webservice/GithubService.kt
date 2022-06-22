package com.vagner.challenge_github.webservice

import android.graphics.pdf.PdfDocument
import com.vagner.challenge_github.model.Owner
import com.vagner.challenge_github.model.PullRequest
import com.vagner.challenge_github.model.RepositoryResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubService {

    @GET("search/repositories?q=language:Java&sort=stars")
    fun getItemRepository(@Query("page") page: Int): Call<RepositoryResponse>

    @GET("repos/{owner}/{repo}/pulls")
    fun getItemPullRequest(
        @Path("owner") owner: String,
        @Path("repo") repo: String
    ): Call<List<PullRequest>>

}