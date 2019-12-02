package com.prueba.francisco.operators

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class DistinctOperator : AppCompatActivity() {

    private val TAG: String = "DistinctOperator"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_distinct_operator)

        var observable : Observable<Int> = Observable
            .just(1,2,3,7,5,3,5,4,5)

        observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .distinct()
            .subscribe(object : Observer<Int>{
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
