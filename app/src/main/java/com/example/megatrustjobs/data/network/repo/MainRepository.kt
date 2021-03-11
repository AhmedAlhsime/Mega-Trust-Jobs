package com.example.megatrustjobs.data.network.repo

import com.example.megatrustjobs.data.network.ApiHelper


class MainRepository(private val apiHelper: ApiHelper) {

    suspend fun getJobs() = apiHelper.getJobs()
}