package com.eight_centimeter.android.mvvm_coroutines_hilt.data.room;

import androidx.room.ColumnInfo
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.eight_centimeter.android.mvvm_coroutines_hilt.data.room.UserDB.Companion.UserDB_TABLE_NAME

@Entity(tableName = UserDB_TABLE_NAME)
data class UserDB(
    @PrimaryKey() @ColumnInfo(name = UserDB_TABLE_COLUMN_ID) var id: Long,
    @ColumnInfo(name = "name") var name: String,
){
    companion object {
        const val UserDB_TABLE_NAME = "User"
        const val UserDB_TABLE_COLUMN_ID = "id"
    }
}



