package com.example.home_task_l30_rxjavav2.data.repository

import com.example.home_task_l30_rxjavav2.PostData
import com.example.home_task_l30_rxjavav2.data.mapper.DataToDomainMapper
import com.example.home_task_l30_rxjavav2.data.mapper.PostResponseToPostDbEntityMapper
import com.example.home_task_l30_rxjavav2.datasource.api.PostReposApi
import com.example.home_task_l30_rxjavav2.datasource.db.PostsDao
import com.example.home_task_l30_rxjavav2.datasource.model.PostResponse
import com.example.home_task_l30_rxjavav2.domain.model.PostDomainModel
import io.reactivex.rxjava3.core.Completable
import java.io.IOException
import java.util.*
import java.util.concurrent.locks.ReentrantLock
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class PostsInfoRepository @Inject constructor(
    private val api: PostReposApi,
    private val dao: PostsDao,
    private val mapperToDB: PostResponseToPostDbEntityMapper,
    private val dataToDomainMapper: DataToDomainMapper
) : Observable() {

    private val reentrantLock = ReentrantLock()

    fun getPostsFromLocalStorage(): List<PostDomainModel>? {
        while (true) {
            if (!reentrantLock.isLocked) {
                return dataToDomainMapper.map(dao.getAllUsersFromDB())
            }
        }
    }

    fun updateLocalStorage(): Completable {
        reentrantLock.lock()
        val emitter = Completable.create { emitter ->
            val postFromApi = getPostsFromApi()
            if (postFromApi != null) {
                dao.insertAll(*mapperToDB.map(postFromApi).toTypedArray())
                setChanged()
                notifyObservers()
                emitter.onComplete()
            } else {
                emitter.onError(IOException("Error get posts from API"))
            }
        }
        reentrantLock.unlock()
        return emitter
    }

    fun addObserverFun(obj: Observer) {
        this.addObserver(obj)
    }

    fun putNewPost(post: PostData) {
        reentrantLock.lock()
        dao.insertPost(post)
        reentrantLock.unlock()
        setChanged()
        notifyObservers()
    }

    private fun getPostsFromApi(): List<PostResponse>? {
        return api.getPostsList().execute().body()
    }

    fun getNewPostId() = dao.getIdForNewPost() + 1
}

