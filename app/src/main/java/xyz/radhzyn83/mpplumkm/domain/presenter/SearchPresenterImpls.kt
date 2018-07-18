package com.kotlin.model

import android.util.Log
import com.kotlin.utils.RetrofitClient
import retrofit2.Call
import retrofit2.Response
import xyz.radhzyn83.mpplumkm.domain.model.DetailProdukModel
import xyz.radhzyn83.mpplumkm.domain.model.ProdukModel
import xyz.radhzyn83.mpplumkm.domain.view.SearchView

/**
 * Created by FathurRadhy on 3/7/2018.
 */
class SearchPresenterImpls : SearchPresenter {
    var mView: SearchView

    constructor(mView: SearchView) {
        this.mView = mView
    }

    override fun doLoad(search: String) {
        val call = RetrofitClient().getSearch(search)
        call.enqueue(object : retrofit2.Callback<ProdukModel> {
            override fun onResponse(call: Call<ProdukModel>, response: Response<ProdukModel>) {
                Log.d("Retrofit", "response : " + response.toString())
                val res = response.body()
                if(res!!.status == "1") {
                    mView.onGetSuccess(res!!)
                } else {
                    mView.onGetError("Terjadi gangguan, silahkan coba lagi.")
                }
            }

            override fun onFailure(call: Call<ProdukModel>, t: Throwable) {
                Log.d("Retrofit", "response : " + t)
                mView.onGetError(t.toString())
            }
        })
    }
}