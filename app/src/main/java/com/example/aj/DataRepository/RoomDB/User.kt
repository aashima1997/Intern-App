package com.example.aj.DataRepository.RoomDB

import android.graphics.Bitmap
import androidx.room.*
import java.util.*

@Entity(tableName="likeTable")
data class User(

        @PrimaryKey(autoGenerate = true)
        val id:Int,
@ColumnInfo (name="Username") val Username:String,
        @ColumnInfo (name="Password") val Password:String,

        )
