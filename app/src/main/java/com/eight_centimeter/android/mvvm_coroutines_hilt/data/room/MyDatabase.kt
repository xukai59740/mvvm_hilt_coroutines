package com.eight_centimeter.android.mvvm_coroutines_hilt.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * Database used for Search History feature.
 */
@Database(
    entities = [UserDB::class],
    version = 1
)
abstract class MyDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao
}
