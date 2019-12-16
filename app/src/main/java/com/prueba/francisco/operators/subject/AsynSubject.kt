package com.prueba.francisco.operators.subject

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.prueba.francisco.operators.R
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.AsyncSubject

class AsynSubject : AppCompatActivity() {

    private var Tag : String = "Asyn"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asyn_subject)
        asynSubjectDemo1()
    }

    private fun getFirtsObserver() : Observer<String>{
        return object : Observer<String>{
            override fun onComplete() {
                Log.i(Tag, "First observer onComplete")
            }

            override fun onSubscribe(d: Disposable) {
                Log.i(Tag, "First observer onSubscribe")
            }

            override fun onNext(t: String) {
                Log.i(Tag, "First observer onNext")
            }

            override fun onError(e: Throwable) {
                Log.i(Tag, "First observer onError")
            }
        }
    }

    private fun getSecondObserver() : Observer<String>{
        return object : Observer<String>{
            override fun onComplete() {
                Log.i(Tag, "Second observer onComplete")
            }

            override fun onSubscribe(d: Disposable) {
                Log.i(Tag, "Second observer onSubscribe")
            }

            override fun onNext(t: String) {
                Log.i(Tag, "Second observer onNext")
            }

            override fun onError(e: Throwable) {
                Log.i(Tag, "Second observer onError")
            }
        }
    }

    private fun getThirdObserver() : Observer<String>{
        return object : Observer<String>{
            override fun onComplete() {
                Log.i(Tag, "Third observer onComplete")
            }

            override fun onSubscribe(d: Disposable) {
                Log.i(Tag, "Third observer onSubscribe")
            }

            override fun onNext(t: String) {
                Log.i(Tag, "Third observer onNext")
            }

            override fun onError(e: Throwable) {
                Log.i(Tag, "Third observer onError")
            }
        }
    }

    fun asynSubjectDemo1(){
        var observable  : Observable<String> = Observable.just("JAVA", "KOTLIN", "XML", "JSON")
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

        var asyncSubject : AsyncSubject<String> = AsyncSubject.create()
        observable.subscribe(asyncSubject)

        asyncSubject.subscribe(getFirtsObserver())
        asyncSubject.subscribe(getSecondObserver())
        asyncSubject.subscribe(getThirdObserver())
    }

    fun asynSubjectDemo2(){
        var asyncSubject : AsyncSubject<String> = AsyncSubject.create()
        asyncSubject.subscribe(getFirtsObserver())

        asyncSubject.onNext("JAVA")
        asyncSubject.onNext("KOTLIN")
        asyncSubject.onNext("XML")

        asyncSubject.subscribe(getSecondObserver())

        asyncSubject.onNext("JSON")
        asyncSubject.onComplete()

        asyncSubject.subscribe(getThirdObserver())
    }
}
