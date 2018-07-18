package com.kotlin.model

import android.util.Log
import com.kotlin.utils.RetrofitClient
import retrofit2.Call
import retrofit2.Response
import xyz.radhzyn83.mpplumkm.domain.model.DetailProdukModel
import xyz.radhzyn83.mpplumkm.domain.view.DetailView

/**
 * Created by FathurRadhy on 3/7/2018.
 */
class DetailPresenterImpls : DetailPresenter {
    var mView: DetailView

    constructor(mView: DetailView) {
        this.mView = mView
    }

    override fun doLoad(id_produk: String) {
        val call = RetrofitClient().getDetailProduk(id_produk)
        call.enqueue(object : retrofit2.Callback<DetailProdukModel> {
            override fun onResponse(call: Call<DetailProdukModel>, response: Response<DetailProdukModel>) {
                Log.d("Retrofit", "response : " + response.toString())
                val res = response.body()
                if(res!!.status == "1") {
                    mView.onGetSuccess(res!!)
                } else {
                    mView.onGetError("Tidak ada Foto")
                }
            }

            override fun onFailure(call: Call<DetailProdukModel>, t: Throwable) {
                Log.d("Retrofit", "response : " + t)
                mView.onGetError(t.toString())
            }
        })
    }
}