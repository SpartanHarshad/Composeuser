package io.harshad.compuser.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [UserEntity::class], version = 1, exportSchema = false)
abstract class ApplicationDatabase:RoomDatabase() {

    abstract fun getUserDao(): UserDao

    companion object {
        const val DB_NAME = "User_DB"
    }
}