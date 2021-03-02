package com.example.home_task_l30_rxjavav2.di

import android.app.Application
import androidx.room.Room
import com.example.home_task_l30_rxjavav2.datasource.db.PostDataBase
import com.example.home_task_l30_rxjavav2.datasource.db.PostsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule(mApplication: Application) {

    @Singleton
    @Provides
    fun providesRoomDatabase(): PostDataBase {
        return postsDatabase
    }

    @Singleton
    @Provides
    fun providesProductDao(demoDatabase: PostDataBase): PostsDao {
        return demoDatabase.getPostsDao()
    }

    @Singleton
    private val postsDatabase: PostDataBase =
        Room.databaseBuilder(mApplication, PostDataBase::class.java, "posts-db").fallbackToDestructiveMigration().build()

}