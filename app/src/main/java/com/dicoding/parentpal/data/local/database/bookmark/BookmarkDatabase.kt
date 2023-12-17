package com.dicoding.parentpal.data.local.database.bookmark

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [BookmarkEntity::class], version = 1, exportSchema = false)
abstract class BookmarkDatabase : RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao

    companion object {
        @Volatile
        private var INSTANCE: BookmarkDatabase? = null

        @JvmStatic
        fun getDatabase(context: Context): BookmarkDatabase {
            if (INSTANCE == null) {
                synchronized(BookmarkDatabase::class.java) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        BookmarkDatabase::class.java, "bookmark_database"
                    )
                        .build()
                }
            }
            return INSTANCE as BookmarkDatabase
        }
    }
}