package com.eight_centimeter.android.mvvm_coroutines_hilt.data.room;

import androidx.room.*

interface BaseDaos<T> {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(item: T): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(items: List<T>): List<Long>

    @Update
    fun update(items: T)

    @Update
    fun update(items: List<T>)

    @Delete
    fun delete(item: T)

    @Delete
    fun delete(items: List<T>)

    fun deleteAll()

    fun getItem(id: Long): T

    fun getItems(): List<T>
}
