package com.erendagstan.storeapp_v1

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.integration.compose.GlideImage
import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SharedViewModel :ViewModel(){

    var product by mutableStateOf<Products?>(null)
        private set

    var products by mutableStateOf<List<Products?>>(listOf())
        private set

    var products_api by mutableStateOf<List<Model?>>(listOf())
        private set

    var categoryFromAPI by mutableStateOf<List<String>>(listOf())

    var products_jewe by mutableStateOf<List<Model>>(listOf())

    fun addCategoryFromAPI(newCategories : List<String>){
        categoryFromAPI=newCategories
    }

    var productsTshirt by mutableStateOf<List<Products?>>(listOf())
        private set

    var productsHoodies by mutableStateOf<List<Products?>>(listOf())
        private set

    var productsJumpers by mutableStateOf<List<Products?>>(listOf())
        private set

    var category by mutableStateOf("")

    fun addProduct(newProduct: Products) {
        product = newProduct
    }

    fun addTshirtProducts(newProducts: List<Products>) {
        productsTshirt = newProducts
    }

    fun addNewProducts(newProducts: List<Model>) {
        products_api= newProducts
        println("EKO: ${products_api} ")
    }


    fun addHoodiesProducts(newProducts: List<Products>) {
        productsHoodies = newProducts
    }

    fun addJumpersProducts(newProducts: List<Products>) {
        productsJumpers = newProducts
    }

    @JvmName("setProduct1")
    fun setProduct(newProduct: Products) {
        product = newProduct
    }

    @JvmName("setCategory1")
    fun setCategory(newCategory: String) {
        category = newCategory
    }

    var storeAPIService = StoreAPIService()
    val disposable = CompositeDisposable()

    //val context = Application().applicationContext
    fun getProductsFromAPI() {
        disposable.add(
            storeAPIService.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Model>>() {
                    override fun onSuccess(t: List<Model>) {
                        showProducts(t)
                        //Toast.makeText(,"Countries From API",Toast.LENGTH_LONG).show()
                    }

                    override fun onError(e: Throwable) {
                        println("ERROR")
                    }
                })
        )


    }
    fun handleResponse(categoryList : List<String>){

    }


    fun getCategoriesFromAPI() {
        disposable.add(
            storeAPIService.getCategoriesData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<String>>() {
                    override fun onError(e: Throwable) {
                        println("ERROR")
                    }

                    override fun onSuccess(t: List<String>) {
                        shareCategories(t)
                    }
                })
        )

    }

    fun getJewelryDataFromAPI(key:String?){
        disposable.add(
            storeAPIService.getJewelryData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Model>>() {
                    override fun onSuccess(t: List<Model>) {
                        if(key=="jewelery") {
                            shareModel(t)
                        }
                        //KEY GONDERİLEBİLİR

                        //Toast.makeText(,"Countries From API",Toast.LENGTH_LONG).show()
                    }

                    override fun onError(e: Throwable) {
                        println("ERROR JEWELRY")
                    }
                })
        )
    }

    fun getElectronicsDataFromAPI(key:String?){
        disposable.add(
            storeAPIService.getElectronicsData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Model>>() {
                    override fun onSuccess(t: List<Model>) {
                        if(key=="electronics") {
                            shareModel(t)
                        }

                        //Toast.makeText(,"Countries From API",Toast.LENGTH_LONG).show()
                    }

                    override fun onError(e: Throwable) {
                        println("ERROR")
                    }
                })
        )
    }
    fun getMenClothingDataFromAPI(){
        disposable.add(
            storeAPIService.getMenClothingData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Model>>() {
                    override fun onSuccess(t: List<Model>) {
                        shareModel(t)
                        //Toast.makeText(,"Countries From API",Toast.LENGTH_LONG).show()
                    }

                    override fun onError(e: Throwable) {
                        println("ERROR")
                    }
                })
        )
    }
    fun getWomenClothingDataFromAPI(){
        disposable.add(
            storeAPIService.getWomenClothingData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<Model>>() {
                    override fun onSuccess(t: List<Model>) {
                        shareModel(t)
                        //Toast.makeText(,"Countries From API",Toast.LENGTH_LONG).show()
                    }

                    override fun onError(e: Throwable) {
                        println("ERROR")
                    }
                })
        )
    }


    fun shareCategories(categories : List<String>){
        addCategoryFromAPI(categories)

        Log.i("Categories from API -> ",categories[0])
        Log.i("Categories from API ->->",categories[1])
        Log.i("Categories from API -> ",categories[2])
        Log.i("Categories from API ->  ",categories[3])
    }


    fun shareModel(models : List<Model>){
        addNewProducts(models)
    }

    private fun showProducts(products: List<Model>){
        Log.i("PRODUCTS -> ",products[1].title)
        Log.i("PRODUCTS -> ",products[1].price)
        Log.i("PRODUCTS -> ",products[1].description)
        Log.i("PRODUCTS -> ",products[1].image)
    }

    override fun onCleared() {
        super.onCleared()
       // disposable.clear()
    }
}



