package com.example.megatrustjobs.data.local.room.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.megatrustjobs.data.local.room.deo.JobsDeo
import com.example.megatrustjobs.data.local.room.entities.JobsEntityes
import com.example.noteapp.database.UriConverter

@Database(entities = [JobsEntityes::class], version = 1, exportSchema = false)
@TypeConverters(UriConverter::class)
abstract class JobsDatabase : RoomDatabase() {

    abstract fun jobDeo(): JobsDeo

    companion object {

        @Volatile
        private var INSTANCE: JobsDatabase? = null

        fun getInstance(context: Context): JobsDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                JobsDatabase::class.java, "jobs.db"
            ).fallbackToDestructiveMigration()
                .build()
    }

}