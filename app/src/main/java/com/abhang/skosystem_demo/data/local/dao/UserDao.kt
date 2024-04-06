package com.abhang.skosystem_demo.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.abhang.skosystem_demo.domain.models.UserData
import com.abhang.skosystem_demo.utils.Constants

@Dao
interface UserDao {

    @Insert
    suspend fun addUser(userData: UserData)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUsers(userData: List<UserData>)

    @Query("SELECT * FROM "+ Constants.DB_TABLE_NAME + " ORDER BY userId ASC")
    suspend fun getAllData(): List<UserData>

    @Query("SELECT * FROM "+ Constants.DB_TABLE_NAME + " ORDER BY userId ASC LIMIT :limit OFFSET :offset")
    suspend fun getData(limit: Int, offset:Int): List<UserData>

    @Query("SELECT COUNT(*) FROM "+ Constants.DB_TABLE_NAME + " WHERE userId = :id")
    suspend fun getUserCount(id: Int): Int

    @Query("SELECT userId FROM "+ Constants.DB_TABLE_NAME + " WHERE userId IN (:id)")
    suspend fun checkIfUserExist(id: List<Int>): List<Int>
}