package com.prueba.francisco.operators.subject

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.prueba.francisco.operators.R
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.PublishSubject

class PublishSubject : AppCompatActivity() {

    private var Tag : String = "Publish"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_public_subject)
    }

    private fun getFirtsObserver() : Observer<String> {
        return object : Observer<String> {
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

    private fun getSecondObserver() : Observer<String> {
        return object : Observer<String> {
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

    private fun getThirdObserver() : Observer<String> {
        return object : Observer<String> {
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

    fun publishSubjectDemo1(){
        var observable  : Observable<String> = Observable.just("JAVA", "KOTLIN", "XML", "JSON")
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

        var publishSubject : PublishSubject<String> = PublishSubject.create()
        observable.subscribe(publishSubject)

        publishSubject.subscribe(getFirtsObserver())
        publishSubject.subscribe(getSecondObserver())
        publishSubject.subscribe(getThirdObserver())
    }

    fun publishSubjectDemo2(){
        var publishSubject : PublishSubject<String> = PublishSubject.create()
        publishSubject.subscribe(getFirtsObserver())

        publishSubject.onNext("JAVA")
        publishSubject.onNext("KOTLIN")
        publishSubject.onNext("XML")

        publishSubject.subscribe(getSecondObserver())

        publishSubject.onNext("JSON")
        publishSubject.onComplete()

        publishSubject.subscribe(getThirdObserver())
    }
}
