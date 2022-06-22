package com.vagner.challenge_github.webservice

import com.vagner.challenge_github.utils.Constants.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitService {

    companion object {


        private fun getRetrofitInstance(): Retrofit {

            val http = OkHttpClient.Builder()

            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(http.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        fun <S> createService(service: Class<S>): S {
            return getRetrofitInstance().create(service)
        }
    }
}