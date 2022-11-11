package com.eight_centimeter.android.mvvm_coroutines_hilt.data.room;

import androidx.room.*

@Dao
interface UserDao : BaseDaos<UserDB>{

    @Query("""
        SELECT * FROM ${UserDB.UserDB_TABLE_NAME} WHERE ${UserDB.UserDB_TABLE_COLUMN_ID} = :id
    """)
    override fun getItem(id: Long): UserDB

    @Query("""
        SELECT * FROM ${UserDB.UserDB_TABLE_NAME}
    """)
    override fun getItems(): List<UserDB>

    @Query("""
        DELETE FROM ${UserDB.UserDB_TABLE_NAME}
    """)
    override fun deleteAll()
}
