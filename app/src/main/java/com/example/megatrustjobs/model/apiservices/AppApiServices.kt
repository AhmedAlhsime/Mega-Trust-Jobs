package com.example.megatrustjobs.model.apiservices

import com.example.megatrustjobs.model.Jobs
import retrofit2.http.GET


interface AppApiServices {
    @GET("positions.json?description=api")
    suspend fun getJobs(): List<Jobs>
}