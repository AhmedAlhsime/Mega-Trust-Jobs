package com.example.megatrustjobs.view.viewModel.appviewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.megatrustjobs.data.local.room.database.JobsDatabase
import com.example.megatrustjobs.data.local.room.deo.JobsDeo
import com.example.megatrustjobs.data.local.room.entities.JobsEntityes
import com.example.megatrustjobs.model.apiservices.AppApiServices
import com.example.megatrustjobs.utils.Resource1
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

//
class HomeViewModel(
    apiServices: AppApiServices,
    jobsDeo: JobsDeo
) : ViewModel() {
    val data: MutableLiveData<Resource1<List<JobsEntityes>>> = MutableLiveData()


    init {
        viewModelScope.launch {

            data.value = Resource1.loading(null)

            try {
                val getListFromApi = apiServices.getJobs()
                jobsDeo.insertJobs(getListFromApi.map {
                    with(it) {
                        JobsEntityes(
                            id = id,
                            type = type,
                            url = url,
                            created_at = created_at,
                            company = company,
                            company_url = company_url,
                            location = location,
                            title = title,
                            description = description,
                            how_to_apply = how_to_apply,
                            company_logo = company_logo
                        )
                    }
                })
            } catch (e: Exception) {
                data.value =
                    Resource1.error(null, e.message ?: "Something went wrong, please try again")
            }

            val list = jobsDeo.getList()
            list.collect {
//                jobsDeo.updateEvent(it)
                Log.d(
                    "viewModel", "list :$it "
                )
                data.value = Resource1.success(it)
            }






        }
    }

}