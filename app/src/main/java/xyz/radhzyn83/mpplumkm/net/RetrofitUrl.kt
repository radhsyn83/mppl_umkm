package com.kotlin.utils

import retrofit2.Call
import retrofit2.http.*
import xyz.radhzyn83.mpplumkm.domain.model.ProdukModel

/**
 * Created by FathurRadhy on 1/30/2018.
 */
interface RetrofitUrl {
    //HOME
    @GET("produk/{start}")
    fun produkResponse(@Path("start") start: String): Call<ProdukModel>
}