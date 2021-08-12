package com.android.olxapplication.data.room

import androidx.room.Database
import androidx.room.DatabaseConfiguration
import androidx.room.InvalidationTracker
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteOpenHelper
import com.android.olxapplication.data.UserModel
import com.android.olxapplication.data.room.dao.UserDao

@Database(entities = [UserModel::class], version = 1,exportSchema = false)
abstract class AppDatabase  :RoomDatabase(){

    abstract fun userDao(): UserDao

    companion object{
        const val  DATABASE_NAME:String = "VisionaryDatabase"
    }

    override fun createOpenHelper(config: DatabaseConfiguration?): SupportSQLiteOpenHelper {
        TODO("Not yet implemented")
    }

    override fun createInvalidationTracker(): InvalidationTracker {
        TODO("Not yet implemented")
    }

    override fun clearAllTables() {
        TODO("Not yet implemented")
    }
}