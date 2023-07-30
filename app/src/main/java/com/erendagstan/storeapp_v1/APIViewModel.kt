package com.erendagstan.storeapp_v1

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class APIViewModel : ViewModel() {

    private var storeAPIService = StoreAPIService()
    private val disposable = CompositeDisposable()
    private val context = Application().applicationContext
    private fun getProductsFromAPI(){
        disposable.add(
            storeAPIService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableSingleObserver<List<Model>>(){
                    override fun onSuccess(t: List<Model>) {
                        showProducts(t)
                        //Toast.makeText(,"Countries From API",Toast.LENGTH_LONG).show()
                    }

                    override fun onError(e: Throwable) {
                        Toast.makeText(context,"ALO",Toast.LENGTH_LONG).show()
                    }
                })
        )
    }

    private fun showProducts(products: List<Model>){
        Log.i("PRODUCTS -> ",products[1].title)
        Log.i("PRODUCTS -> ",products[1].price)
        Log.i("PRODUCTS -> ",products[1].description)
        Log.i("PRODUCTS -> ",products[1].image)
    }



}

