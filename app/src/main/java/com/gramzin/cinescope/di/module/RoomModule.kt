package com.gramzin.cinescope.di.module

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.gramzin.cinescope.data.room.DBUtils
import com.gramzin.cinescope.data.room.db.AppDatabase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [RoomBindsModule::class])
object RoomModule {

    @Singleton
    @Provides
    fun provideDatabase(context: Context): AppDatabase{
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java, DBUtils.dbName
        ).build()
    }
}