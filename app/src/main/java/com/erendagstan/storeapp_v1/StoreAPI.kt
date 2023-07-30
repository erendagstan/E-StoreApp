package com.erendagstan.storeapp_v1

import io.reactivex.Single
import retrofit2.http.GET

interface StoreAPI {

    @GET("products")
    fun getProducts() :Single<List<Model>>

    @GET("products/category/jewelery")
    fun getJewelryProducts() : Single<List<Model>>
    @GET("products/category/electronics")
    fun getElectronicsProducts() : Single<List<Model>>
    @GET("products/category/men's clothing")
    fun getMenClothingProducts() : Single<List<Model>>
    @GET("products/category/jewelery")
    fun getWomenClotingProducts() : Single<List<Model>>

    @GET("products/categories")
    fun getCategories() : Single<List<String>>
}