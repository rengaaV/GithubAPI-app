package com.vagner.challenge_github.viewmodel


import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.vagner.challenge_github.model.Repository
import com.vagner.challenge_github.model.RepositoryResponse
import com.vagner.challenge_github.utils.Constants.ERROR
import com.vagner.challenge_github.webservice.GithubService
import com.vagner.challenge_github.webservice.RetrofitService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RepositoryViewModel : ViewModel() {

    private val repository = RetrofitService.createService(GithubService::class.java)
    val successRepository = MutableLiveData<List<Repository>>()
    val errorRepository = MutableLiveData<Any>()


    fun getItemRepository(page: Int) {
        repository.getItemRepository(page).enqueue(object : Callback<RepositoryResponse> {
            override fun onResponse(
                call: Call<RepositoryResponse>,
                response: Response<RepositoryResponse>
            ) {

                response.body()?.let {
                    successRepository.postValue(it.items)

                }
            }

            override fun onFailure(call: Call<RepositoryResponse>, t: Throwable) {
                Log.d(ERROR, t.message.toString())
                errorRepository.postValue(t)
            }

        })
    }

}