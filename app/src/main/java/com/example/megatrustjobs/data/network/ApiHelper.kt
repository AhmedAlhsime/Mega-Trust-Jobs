package com.example.megatrustjobs.data.network

import com.example.megatrustjobs.model.apiservices.AppApiServices

class ApiHelper(private val apiService: AppApiServices ) {
    suspend fun getJobs() = apiService.getJobs()

}