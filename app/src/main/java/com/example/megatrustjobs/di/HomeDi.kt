package com.example.megatrustjobs

import android.content.Context
import androidx.room.Room
import com.example.megatrustjobs.data.local.room.database.JobsDatabase
import com.example.megatrustjobs.data.local.room.deo.JobsDeo
import com.example.megatrustjobs.model.apiservices.AppApiServices
import com.example.megatrustjobs.view.viewModel.appviewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val homeDi = module {
    single { getRetrofit() }
    single { getServise(get()) }
    viewModel { HomeViewModel(get(), get()) }
    single { addListRoom(get()) }
    single { appDatabase(get()) }
}


private const val BASE_URL = "https://jobs.github.com/"

private fun getRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build() //Doesn't require the adapter
}

fun getServise(retrofit: Retrofit): AppApiServices {
    return retrofit.create(AppApiServices::class.java)
}

fun addListRoom(db: JobsDatabase): JobsDeo {
    return db.jobDeo()
}

fun appDatabase(context: Context): JobsDatabase {
    return Room.databaseBuilder(
        context.applicationContext,
        JobsDatabase::class.java, "jobs.db"
    ).fallbackToDestructiveMigration()
        .build()
}
