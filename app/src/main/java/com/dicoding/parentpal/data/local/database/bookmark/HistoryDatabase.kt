package com.dicoding.parentpal.data.local.database.bookmark

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BookmarkEntity::class], version = 1, exportSchema = false)
abstract class HistoryDatabase : RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao

    companion object {
        @Volatile
        private var INSTANCE: HistoryDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): HistoryDatabase {
            if (INSTANCE == null) {
                synchronized(HistoryDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        HistoryDatabase::class.java, "history_database"
                    )
                        .build()
                }
            }
            return INSTANCE as HistoryDatabase
        }
    }
}