package xyz.radhzyn83.mpplumkm.fragment

import android.view.View
import android.widget.Button
import android.widget.TextView
import com.jcodecraeer.xrecyclerview.XRecyclerView
import xyz.radhzyn83.mpplumkm.R
import xyz.radhzyn83.mpplumkm.utils.SessionManager
import xyz.radhzyn83.mpplumkm.utils.SupportVariabel

class UserFragment : RecyclerFragment() {

    private var tv_nama: TextView? = null
    private var tv_email: TextView? = null
    private var xRecyclerView: XRecyclerView? = null
    private var start: Int = 0
    private var btn_logout: Button? = null
    private var sm: SessionManager? = null

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
        sm = SessionManager(activity!!)
        tv_email = view.findViewById(R.id.tv_email)
        tv_nama = view.findViewById(R.id.tv_nama)

        tv_email!!.setText(SupportVariabel(activity!!).getEmail_user())
        tv_nama!!.setText(SupportVariabel(activity!!).getName_user())

        btn_logout =  view.findViewById(R.id.btn_logout)
        btn_logout!!.setOnClickListener{view->
            sm!!.logout()
            sm!!.checkLogin()
        }

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
