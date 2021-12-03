package com.lobovindustries.rxtest.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import io.reactivex.rxjava3.observables.ConnectableObservable

class MainViewModel : ViewModel() {
    private val disposable = CompositeDisposable()
    val liveData = MutableLiveData<List<String>>()
    private val users = listOf("Ilya", "Ilya`s colleague")


    fun subscribeWithoutSubscribers() {
       val conObs = ConnectableObservable.just(liveData.setValue(users)).publish()
        Log.e("asdasd", Thread.currentThread().name)
        val disp = (conObs as ConnectableObservable).connect()
        Log.e("vm  ppp", disp.isDisposed().toString())

        // кажется just() емитит данные и завершается, что вызывает освобождение ресурсов и
        // disposable очищается.
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}