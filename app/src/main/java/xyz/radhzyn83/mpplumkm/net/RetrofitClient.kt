package com.kotlin.utils

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import xyz.radhzyn83.mpplumkm.domain.model.ProdukModel

/**
 * Created by FathurRadhy on 1/30/2018.
 */
class RetrofitClient {
    private val retrofitApi: RetrofitUrl
    val API_URL:String = "http://mppl.radhsyn83.xyz/";

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        retrofitApi = retrofit.create(RetrofitUrl::class.java)
    }

    fun getProduk(start: String): Call<ProdukModel> {
        return retrofitApi.produkResponse(start)
    }
}