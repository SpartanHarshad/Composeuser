package io.harshad.compuser.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.harshad.compuser.data.local.ApplicationDatabase
import io.harshad.compuser.data.local.UserDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataBaseModule {

    @Singleton
    @Provides
    fun provideRoomDatabase(@ApplicationContext ctx: Context): ApplicationDatabase {
        return Room.databaseBuilder(
            ctx.applicationContext,
            ApplicationDatabase::class.java,
            ApplicationDatabase.DB_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideUserDao(database: ApplicationDatabase): UserDao {
        return database.getUserDao()
    }
}