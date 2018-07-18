package xyz.radhzyn83.mpplumkm.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.view.View
import com.kotlin.model.SearchPresenterImpls
import kotlinx.android.synthetic.main.activity_search.*
import xyz.radhzyn83.mpplumkm.R
import xyz.radhzyn83.mpplumkm.adapter.SearchAdapter
import xyz.radhzyn83.mpplumkm.domain.model.ProdukModel
import xyz.radhzyn83.mpplumkm.domain.view.SearchView

class SearchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)

        search_rv.setPullRefreshEnabled(false)

        search_btn.setOnClickListener {
            searchProduk()
        }
    }

    private fun searchProduk() {
//        SimpleDialog().showDialog(this)
        SearchPresenterImpls(object : SearchView {
            override fun onGetSuccess(res: ProdukModel) {
                if(res.produk.size > 0) {
                    val adapter = SearchAdapter(this@SearchActivity, res.produk)
                    search_rv.layoutManager = GridLayoutManager(this@SearchActivity.applicationContext, 2)
                    search_rv.adapter = adapter
                    search_rv.refreshComplete()
                    search_rv.visibility = View.VISIBLE
                    search_null.visibility = View.GONE
//                    SimpleDialog().stopDialog()
                } else {
//                    SimpleDialog().textDialog(this@SearchActivity, "Tidak ada produk yang di temukan",false)
                }

            }

            override fun onGetError(e: String) {
//                SimpleDialog().stopDialog()
//                SimpleDialog().textDialog(this@SearchActivity, e,false)
                search_rv.visibility = View.GONE
                search_null.visibility = View.VISIBLE
            }
        }).doLoad(et_search.text.toString())
    }
}
