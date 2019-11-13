package com.prueba.francisco.operators

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception


class MainActivity : AppCompatActivity() {

    private lateinit var myObservable: Observable<Student>
    private lateinit var myObserver: Observer<Student>
    private val TAG: String = "RxDemo"
    private var compositeDisposable: CompositeDisposable? = CompositeDisposable()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myObservable = Observable.create {
            try {
                val studentArrayList = getStudents()

                for (student in studentArrayList) {
                    it.onNext(student)
                }
                it.onComplete()
            }catch (e:Exception){
                Log.i(TAG, "${e.message}")
            }
        }

        compositeDisposable?.add(
            myObservable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map {student ->
                    student.name = student.name.toUpperCase()
                    return@map student
                }
                .subscribeWith(getObserver())
        )

        /*myObservable
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .map {student ->
                student.name = student.name.toUpperCase()
                return@map student
            }
            .subscribe(object : DisposableObserver<Student>(){
                override fun onComplete() {
                    Log.i(TAG, " onComplete invoked")
                }

                override fun onNext(t: Student) {
                    tvName.text = t.name
                }

                override fun onError(e: Throwable) {
                    Log.i(TAG, " onError invoked")
                }

            })*/
    }


    private fun getObserver(): DisposableObserver<Student> {

        myObserver = object : DisposableObserver<Student>() {
            override fun onNext(s: Student) {
                //Log.i(TAG, " onNext invoked with " + s.email)
                tvName.text = s.name
            }

            override fun onError(e: Throwable) {
                Log.i(TAG, " onError invoked")
            }

            override fun onComplete() {
                Log.i(TAG, " onComplete invoked")
            }
        }

        return this.myObserver as DisposableObserver<Student>
    }


    private fun getStudents(): ArrayList<Student> {

        var students: ArrayList<Student>? = ArrayList()

        val student1 = Student()
        student1.name = " student 1"
        student1.email = " student1@gmail.com "
        student1.age = 27
        students?.add(student1)

        val student2 = Student()
        student2.name = " student 2"
        student2.email = " student2@gmail.com "
        student2.age = 20
        students?.add(student2)

        val student3 = Student()
        student3.name = " student 3"
        student3.email = " student3@gmail.com "
        student3.age = 20
        students?.add(student3)

        val student4 = Student()
        student4.name = " student 4"
        student4.email = " student4@gmail.com "
        student4.age = 20
        students?.add(student4)

        val student5 = Student()
        student5.name = " student 5"
        student5.email = " student5@gmail.com "
        student5.age = 20
        students?.add(student5)

        return students!!


    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable?.clear()
    }
}
