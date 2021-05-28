package com.example.aj.DataRepository.RoomDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.aj.DataRepository.RoomDB.Attendees
import com.example.aj.DataRepository.RoomDB.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser(user: User)

    @Query("SELECT * FROM likeTable")
    fun readAllData():LiveData<List<User>>

    //Attendees class
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addUser1(user1: Attendees)

    @Query("SELECT * FROM hi_table")
    fun readAllData1():LiveData<List<Attendees>>




    @Query("SELECT * FROM likeTable WHERE Username LIKE :userName")
     fun getUsername(userName: String): User



}