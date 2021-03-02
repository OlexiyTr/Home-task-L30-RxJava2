package com.example.home_task_l30_rxjavav2.datasource.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.home_task_l30_rxjavav2.PostData

@Database(entities = [PostData::class], version = PostDataBase.VERSION)
abstract class PostDataBase : RoomDatabase() {
    companion object {
        const val VERSION = 2
    }

    abstract fun getPostsDao(): PostsDao
}