package com.example.aj.DataRepository.RoomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.aj.DataRepository.dateConverter

@Database(entities = [User::class, Attendees::class],version = 1,exportSchema = false)
@TypeConverters(dateConverter::class)

abstract class UserDatabase:RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase? {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(
                    context, UserDatabase::class.java, "hey_Table"
                )
                    .allowMainThreadQueries()
                    .build()
            }
            return INSTANCE
        }
    }


}