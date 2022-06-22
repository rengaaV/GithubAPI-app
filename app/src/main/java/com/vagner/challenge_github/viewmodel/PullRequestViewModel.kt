package com.vagner.challenge_github.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vagner.challenge_github.model.PullRequest
import com.vagner.challenge_github.utils.Constants
import com.vagner.challenge_github.utils.Constants.ERROR
import com.vagner.challenge_github.webservice.GithubService
import com.vagner.challenge_github.webservice.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PullRequestViewModel : ViewModel() {

    private var repository = RetrofitService.createService(GithubService::class.java)
    val successRepository = MutableLiveData<List<PullRequest>>()
    val errorRepository = MutableLiveData<Any>()


    fun getItemPull(owner: String, repo: String) {

        repository.getItemPullRequest(owner, repo).enqueue(object : Callback<List<PullRequest>> {
            override fun onResponse(
                call: Call<List<PullRequest>>,
                response: Response<List<PullRequest>>
            ) {
                successRepository.postValue(response.body())
            }

            override fun onFailure(call: Call<List<PullRequest>>, t: Throwable) {
                Log.d(ERROR, t.message.toString())
            }
        })

    }

}
