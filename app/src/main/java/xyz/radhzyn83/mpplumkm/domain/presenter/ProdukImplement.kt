package xyz.radhzyn83.mpplumkm.domain.presenter

import android.util.Log
import com.kotlin.utils.RetrofitClient
import retrofit2.Call
import retrofit2.Response
import xyz.radhzyn83.mpplumkm.domain.model.ProdukModel
import xyz.radhzyn83.mpplumkm.domain.view.ProdukView

/**
 * Created by FathurRadhy on 3/15/2018.
 */
class ProdukImplement : ProdukPresenter {
    var mView : ProdukView

    constructor(mView: ProdukView) {
        this.mView = mView
    }

    override fun doLoadProduk(start: String) {
        val call = RetrofitClient().getProduk(start)
        call.enqueue(object : retrofit2.Callback<ProdukModel> {
            override fun onResponse(call: Call<ProdukModel>, response: Response<ProdukModel>) {
                Log.d("Retrofit", "response : " + response.toString())
                val res = response.body()
                if(res!!.status == "1") {
                    mView.onGetSuccess(res!!)
                } else {
                    mView.onError(response.toString())
                }
            }

            override fun onFailure(call: Call<ProdukModel>, t: Throwable) {
                Log.d("Retrofit", "response : " + t)
                mView.onError(t.toString())
            }
        })
    }

}