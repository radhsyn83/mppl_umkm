package com.kotlin.utils

import retrofit2.Call
import retrofit2.http.*
import xyz.radhzyn83.mpplumkm.domain.model.DetailProdukModel
import xyz.radhzyn83.mpplumkm.domain.model.LoginResponse
import xyz.radhzyn83.mpplumkm.domain.model.ProdukModel

/**
 * Created by FathurRadhy on 1/30/2018.
 */
interface RetrofitUrl {
    //LOGIN
    @FormUrlEncoded
    @POST("login")
    fun loginResponse(@Field("email") email: String, @Field("password") password: String): Call<LoginResponse>
    //HOME
    @GET("produk/{start}")
    fun produkResponse(@Path("start") start: String): Call<ProdukModel>
    //Detail produk
    @GET("detail/{id}")
    fun produkDetailResponse(@Path("id") start: String): Call<DetailProdukModel>
    //Search produk
    @GET("search/{search}")
    fun searchResponse(@Path("search") search: String): Call<ProdukModel>

}