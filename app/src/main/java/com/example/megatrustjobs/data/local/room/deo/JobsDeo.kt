package com.example.megatrustjobs.data.local.room.deo

import androidx.room.*
import com.example.megatrustjobs.data.local.room.entities.JobsEntityes
import kotlinx.coroutines.flow.Flow


@Dao
interface JobsDeo {

    @Query("SELECT * FROM jobsentityes")
    fun getList(): Flow<List<JobsEntityes>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun  insertJobs(job: List<JobsEntityes>)

    @Update(onConflict =  OnConflictStrategy.REPLACE)
    fun updateEvent( job: List<JobsEntityes>)

}