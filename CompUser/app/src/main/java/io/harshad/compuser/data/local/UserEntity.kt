package io.harshad.compuser.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "userTable")
data class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "uId")
    val uId: Int = 0,
    @ColumnInfo(name = "name")
    val uName: String,
    @ColumnInfo(name = "age")
    val uAge: Int,
    @ColumnInfo(name = "dob")
    val uDob: String,
    @ColumnInfo(name = "adr")
    val uAdr: String,
)
