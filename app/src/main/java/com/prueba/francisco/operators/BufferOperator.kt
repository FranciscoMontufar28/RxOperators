package com.prueba.francisco.operators

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class BufferOperator : AppCompatActivity() {

    private val TAG: String = "BufferOperator"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_buffer_operator)

        var myObservable : Observable<Int> = Observable.range(1, 20)

        myObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .buffer(4)
            .subscribe(object : Observer<List<Int>>{
                override fun onNext(t: List<Int>) {
                    Log.i(TAG, "Came to OnNext")
                    for( i in t){
                        Log.i(TAG, "int value is $i")
                    }
                }

                override fun onComplete() {
                    Log.i(TAG, "Came to onComplete")
                }

                override fun onSubscribe(d: Disposable) {
                    Log.i(TAG, "Came to onSubscribe")
                }

                override fun onError(e: Throwable) {
                    Log.i(TAG, "Came to onError")
                }

            })

    }
}
