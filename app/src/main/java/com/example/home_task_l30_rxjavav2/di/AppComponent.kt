package com.example.home_task_l30_rxjavav2.di

import android.content.Context
import com.example.home_task_l30_rxjavav2.ui.inputNewPostScreen.InputNewPostFragment
import com.example.home_task_l30_rxjavav2.ui.mainActivity.MainActivity
import com.example.home_task_l30_rxjavav2.ui.showPostsScreen.PostsScreenFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(dependencies = [], modules = [AppModule::class, RoomModule::class])
interface AppComponent {
    val context : Context
    fun inject(screenFragment: PostsScreenFragment)
    fun inject(fragment: InputNewPostFragment)
    fun inject(activity: MainActivity)
}