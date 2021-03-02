package com.example.home_task_l30_rxjavav2

import android.app.Application
import com.example.home_task_l30_rxjavav2.di.AppComponent
import com.example.home_task_l30_rxjavav2.di.AppModule
import com.example.home_task_l30_rxjavav2.di.DaggerAppComponent
import com.example.home_task_l30_rxjavav2.di.RoomModule

class App : Application() {

    private lateinit var daggerComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        daggerComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .roomModule(RoomModule(this))
            .build()
    }

    fun getComponent(): AppComponent {
        return daggerComponent
    }
}