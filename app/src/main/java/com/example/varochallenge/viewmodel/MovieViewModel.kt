package com.example.varochallenge.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.varochallenge.api.MovieService
import com.example.varochallenge.api.MovieServiceInstance
import com.example.varochallenge.model.MovieInfo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory



class MovieViewModel : ViewModel() {
    companion object {
        private const val TAG = "MovieViewModel"
    }

    private val disposables = CompositeDisposable()
    private var _movieList = MutableLiveData<List<MovieInfo>>()

    override fun onCleared() {
        super.onCleared()
        disposables.clear()
    }

    fun getMoviesList():MutableLiveData<List<MovieInfo>> {
        MovieServiceInstance.moviesNowPlaying
            .getNowPlaying()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { response ->
                    _movieList.postValue(response.results)
                },
                onError = { error ->
                    Log.e(TAG, error.toString())
                }
            )
            .addTo(disposables)

        return _movieList
    }

}