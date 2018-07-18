package com.kotlin.utils

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import xyz.radhzyn83.mpplumkm.domain.model.DetailProdukModel
import xyz.radhzyn83.mpplumkm.domain.model.LoginResponse
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

    fun postLogin(email: String, password: String): Call<LoginResponse> {
        return retrofitApi.loginResponse(email, password)
    }

    fun getProduk(start: String): Call<ProdukModel> {
        return retrofitApi.produkResponse(start)
    }

    fun getDetailProduk(id_produk: String): Call<DetailProdukModel> {
        return retrofitApi.produkDetailResponse(id_produk)
    }

    fun getSearch(search: String): Call<ProdukModel> {
        return retrofitApi.searchResponse(search)
    }
}