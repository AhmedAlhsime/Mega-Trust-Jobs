package com.example.megatrustjobs.view.viewModel.appviewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.megatrustjobs.data.network.repo.MainRepository
import com.example.megatrustjobs.model.apiservices.AppApiServices

//class AppViewModelFactory(private val appApiServices: MainRepository) : ViewModelProvider.Factory {
//    @Suppress("UNCHECKED_CAST")
//    override fun <T : ViewModel> create(modelClass: Class<T>): T {
////        if (modelClass.isAssignableFrom(HomeViewModel::class.java)) {
////            return HomeViewModel(appApiServices) as T
//        }
////        throw IllegalArgumentException("Unknown ViewModel class")
//    }
//}