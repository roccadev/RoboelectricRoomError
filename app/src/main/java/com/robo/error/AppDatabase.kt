package com.robo.error

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [MyEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {

    abstract val myDao: MyDao
}