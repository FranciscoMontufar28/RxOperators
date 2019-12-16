package com.prueba.francisco.operators.subject

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import com.prueba.francisco.operators.R
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject

class BehaviorSubject : AppCompatActivity() {

    private var Tag : String = "Behavior"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_behaviour_subject)

        behaviorSubjectDemo1()
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

    fun behaviorSubjectDemo1(){
        var observable  : Observable<String> = Observable.just("JAVA", "KOTLIN", "XML", "JSON")
        observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

        var behaviorSubject : BehaviorSubject<String> = BehaviorSubject.create()
        observable.subscribe(behaviorSubject)

        behaviorSubject.subscribe(getFirtsObserver())
        behaviorSubject.subscribe(getSecondObserver())
        behaviorSubject.subscribe(getThirdObserver())
    }

    fun behaviorSubjectDemo2(){
        var behaviorSubject : BehaviorSubject<String> = BehaviorSubject.create()
        behaviorSubject.subscribe(getFirtsObserver())

        behaviorSubject.onNext("JAVA")
        behaviorSubject.onNext("KOTLIN")
        behaviorSubject.onNext("XML")

        behaviorSubject.subscribe(getSecondObserver())

        behaviorSubject.onNext("JSON")
        behaviorSubject.onComplete()

        behaviorSubject.subscribe(getThirdObserver())
    }
}
