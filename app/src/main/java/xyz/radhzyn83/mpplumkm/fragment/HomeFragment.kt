package xyz.radhzyn83.mpplumkm.fragment

import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import com.jcodecraeer.xrecyclerview.XRecyclerView
import org.jetbrains.anko.support.v4.toast
import xyz.radhzyn83.mpplumkm.R
import xyz.radhzyn83.mpplumkm.adapter.ProdukAdapter
import xyz.radhzyn83.mpplumkm.domain.model.ProdukModel
import xyz.radhzyn83.mpplumkm.domain.presenter.ProdukImplement
import xyz.radhzyn83.mpplumkm.domain.presenter.ProdukPresenter
import xyz.radhzyn83.mpplumkm.domain.view.ProdukView
import xyz.radhzyn83.mpplumkm.utils.Const

class HomeFragment : RecyclerFragment(), ProdukView {

    private var xRecyclerView: XRecyclerView? = null
    private var start: Int = 0
    private var search_null: LinearLayout? = null
    private var error_title: TextView? = null
    lateinit var mPresenter: ProdukPresenter

    companion object {
        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun setLayoutResources(): Int {
        return R.layout.fragment_home
    }

    override fun setRecyclerView(view: View): XRecyclerView? {
        xRecyclerView =  view.findViewById(R.id.home_rv)
        return xRecyclerView
    }

    override fun onViewReady(view: View) {
        search_null =  view.findViewById(R.id.search_null)
        error_title =  view.findViewById(R.id.error_title)

        mPresenter = ProdukImplement(this)

        getData(Const().START_DEFAULT())
    }

    override fun onPullRefresh() {
        isLoading = false
        xRecyclerView!!.setLoadingMoreEnabled(true)
        getData(Const().START_DEFAULT())
    }

    private fun getData(start: Int) {
        mPresenter.doLoadProduk(""+start)
    }

    override fun onScrollLoadMore() {
        isLoading = true
        getData(xRecyclerView!!.adapter.itemCount)
    }

    override fun onReload() {
        isLoading = false
        getData(start)
    }

    override fun onGetSuccess(res: ProdukModel) {
        xRecyclerView!!.visibility = View.VISIBLE
        search_null!!.visibility = View.GONE
        if (xRecyclerView != null) {
            if(res.produk.size > 0) {
                if (isLoading) {
                    xRecyclerView!!.loadMoreComplete()
                    isLoading = true
                    (xRecyclerView!!.getAdapter() as ProdukAdapter).addItem(res.produk)
                } else {
                    val adapter = ProdukAdapter(activity!!,res.produk)
                    xRecyclerView!!.refreshComplete();
                    xRecyclerView!!.layoutManager = LinearLayoutManager(activity!!.applicationContext, LinearLayout.VERTICAL, false) as RecyclerView.LayoutManager?
                    xRecyclerView!!.adapter = adapter
                }
            } else {
                if (isLoading) {
                    xRecyclerView!!.loadMoreComplete()
                    xRecyclerView!!.setLoadingMoreEnabled(false)
                } else {
                    xRecyclerView!!.refreshComplete()
                    xRecyclerView!!.setVisibility(View.GONE)
                    xRecyclerView!!.visibility = View.GONE
                    search_null!!.visibility = View.VISIBLE
                    error_title!!.setText("Belum ada produk yang di inginkan")
                }
            }
        }
    }

    override fun onError(msg: String) {
        toast(msg)
        xRecyclerView!!.loadMoreComplete()
        xRecyclerView!!.setLoadingMoreEnabled(false)
    }
}
