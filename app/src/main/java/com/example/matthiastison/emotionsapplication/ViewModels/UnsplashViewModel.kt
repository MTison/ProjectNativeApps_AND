package com.example.matthiastison.emotionsapplication.ViewModels

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.example.matthiastison.emotionsapplication.App
import com.example.matthiastison.emotionsapplication.Network.SearchResult
import com.example.matthiastison.emotionsapplication.Network.Unsplash
import com.example.matthiastison.emotionsapplication.Network.UnsplashApi
import com.orhanobut.logger.Logger
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class UnsplashViewModel : ViewModel() {

    private val unsplashData = MutableLiveData<ArrayList<Unsplash>>()
    // Represents a disposable resource
    private lateinit var subscription: Disposable

    @Inject
    // The instance of the UnsplashApi class to get back the results of the API
    lateinit var unsplashApi: UnsplashApi

    init {
        App.vmComponent.inject(this)
    }

    fun requestPhotosOnQuery(searchQuery: String): MutableLiveData<ArrayList<Unsplash>> {
        subscription = unsplashApi.getPhotos(searchQuery)
                // we tell it to fetch the data on background by
                .subscribeOn(Schedulers.io())
                // we like the fetched data to be displayed on the main UI Thread
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { onRetrieveUnsplashStart() }
                .doOnTerminate { onRetrieveUnsplashFinish() }
                .subscribe(
                        { result -> onRetrieveUnsplashSuccess(result) },
                        { error -> onRetrieveUnsplashError(error) }
                )

        return unsplashData
    }

    private fun onRetrieveUnsplashError(error: Throwable) {
        Logger.e(error.message!!)
    }

    private fun onRetrieveUnsplashSuccess(result: SearchResult) {
        unsplashData.value = result.results as ArrayList<Unsplash>
        Logger.i("The first result description: ${result.results!![0].description}")
    }

    private fun onRetrieveUnsplashFinish() {
        Logger.i("Finished retrieving UNSPLASH info")
    }

    private fun onRetrieveUnsplashStart() {
        Logger.i("Started retrieving UNSPLASH info")
    }

    override fun onCleared() {
        super.onCleared()
        subscription.dispose()
    }

}