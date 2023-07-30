package com.erendagstan.storeapp_v1

import android.view.Display.Mode
import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class StoreAPIService {

    private val BASE_URL ="https://fakestoreapi.com/"

    private val api = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()
        .create(StoreAPI::class.java)

    fun getData() : Single<List<Model>>{
        return api.getProducts()
    }

    fun getJewelryData() : Single<List<Model>>{
        return api.getJewelryProducts()
    }
    fun getElectronicsData() : Single<List<Model>>{
        return api.getElectronicsProducts()
    }
    fun getMenClothingData() : Single<List<Model>>{
        return api.getMenClothingProducts()
    }
    fun getWomenClothingData() : Single<List<Model>>{
        return api.getWomenClotingProducts()
    }

    fun getCategoriesData() : Single<List<String>>{
        return api.getCategories()
    }

}