package com.abhang.skosystem_demo.data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.abhang.skosystem_demo.data.local.dao.UserDao
import com.abhang.skosystem_demo.domain.models.UserData
import com.abhang.skosystem_demo.utils.Constants


@Database(entities = [UserData::class], version = 1)
abstract class UserDatabase : RoomDatabase(){
    abstract fun getUserDao(): UserDao

    companion object {
        private const val DB_NAME = Constants.DB_NAME

        @Volatile
        private var instance: UserDatabase?= null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also {
                instance = it
            }
        }


        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            UserDatabase::class.java,
            DB_NAME
        ).fallbackToDestructiveMigration().build()
    }

}