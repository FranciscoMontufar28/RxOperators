package com.prueba.francisco.operators

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class SkipLastOperator : AppCompatActivity() {

    private val TAG: String = "SkipLastOperator"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_skip_last_operator)
        var myObservable: Observable<Int> = Observable.just(1, 2, 3, 4, 5, 6, 7)

        myObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .skipLast(4)
            .subscribe(object : Observer<Int> {
                override fun onComplete() {
                    Log.i(TAG, "Came to onComplete")
                }

                override fun onSubscribe(d: Disposable) {
                    Log.i(TAG, "Came to onSubscribe")
                }

                override fun onNext(t: Int) {
                    Log.i(TAG, "Came to onNext")
                    Log.i(TAG, "valor $t")
                }

                override fun onError(e: Throwable) {
                    Log.i(TAG, "Came to onError")
                }

            })
    }
}
