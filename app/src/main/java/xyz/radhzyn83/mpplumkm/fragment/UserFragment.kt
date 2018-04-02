package xyz.radhzyn83.mpplumkm.fragment

import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.jcodecraeer.xrecyclerview.XRecyclerView
import xyz.radhzyn83.mpplumkm.R

class UserFragment : RecyclerFragment() {

    private var xRecyclerView: XRecyclerView? = null
    private var start: Int = 0
    private var search_null: LinearLayout? = null
    private var error_title: TextView? = null

    companion object {
        fun newInstance(): UserFragment {
            return UserFragment()
        }
    }

    override fun setLayoutResources(): Int {
        return R.layout.fragment_user
    }

    override fun setRecyclerView(view: View): XRecyclerView? {
        xRecyclerView =  view.findViewById(R.id.user_rv)
        return xRecyclerView
    }

    override fun onViewReady(view: View) {

        search_null =  view.findViewById(R.id.search_null)
        error_title =  view.findViewById(R.id.error_title)
        getData(start)
    }

    override fun onPullRefresh() {
        isLoading = false
        xRecyclerView!!.setLoadingMoreEnabled(true)
        getData(start)
    }

    private fun getData(start: Int) {
    }

    override fun onScrollLoadMore() {
        isLoading = true
        getData(xRecyclerView!!.adapter.itemCount)
    }

    override fun onReload() {
        isLoading = false
        getData(start)
    }
}
