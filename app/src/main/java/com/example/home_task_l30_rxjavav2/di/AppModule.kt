package com.example.home_task_l30_rxjavav2.di

import android.content.Context
import com.example.home_task_l30_rxjavav2.datasource.api.PostReposApi
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
class AppModule(val context: Context) {

    @Singleton
    @Provides
    fun context(): Context {
        return context
    }

    @Provides
    @Singleton
    fun provideRetrofit(gsonConverterFactory: GsonConverterFactory): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .addConverterFactory(gsonConverterFactory)
            .build()
    }

    @Provides
    @Singleton
    fun provideGsonConverterFactory(): GsonConverterFactory {
        return GsonConverterFactory.create(
            GsonBuilder().setLenient().create()
        )
    }

    @Provides
    @Singleton
    fun providePostsReposApi(retrofit: Retrofit): PostReposApi {
        return retrofit.create(PostReposApi::class.java)
    }

}