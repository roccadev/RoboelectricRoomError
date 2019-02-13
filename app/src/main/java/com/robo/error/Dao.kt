package com.robo.error

import androidx.room.*

@Entity
data class MyEntity @Ignore constructor(
    @PrimaryKey
    var id: Long,
    var title: String,
    var desc: String
) {
    constructor() : this(0, "", "")
}

@Dao
interface MyDao {

    @Query("select * from MyEntity")
    suspend fun selectAll(): List<MyEntity>

    @Insert
    suspend fun insert(vararg entity: MyEntity): LongArray

    @Update
    suspend fun update(vararg entity: MyEntity): Int

    @Delete
    suspend fun delete(vararg entity: MyEntity)
}