package xyz.radhzyn83.mpplumkm.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jcodecraeer.xrecyclerview.ProgressStyle

import com.jcodecraeer.xrecyclerview.XRecyclerView

/**
 * Created by Andi Insanudin on 14/12/2016.
 */

abstract class RecyclerFragment : Fragment() {

    private var mContext: Context? = null
    private var mXRecyclerView: XRecyclerView? = null
    var isLoading = false
    var id_user: String? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(setLayoutResources(), container, false)
        setRetainInstance(true)

        mContext = activity

//        id_user = Const(activity!!.applicationContext).getId_user()

        if (setRecyclerView(view) != null) {
            mXRecyclerView = setRecyclerView(view)
        }

        onViewReady(view)

        if (mXRecyclerView != null) {
            mXRecyclerView!!.setRefreshProgressStyle(ProgressStyle.BallSpinFadeLoader);
            mXRecyclerView!!.setLoadingMoreProgressStyle(ProgressStyle.BallSpinFadeLoader);
            mXRecyclerView!!.setLoadingListener(object : XRecyclerView.LoadingListener {
                override fun onRefresh() {
                    onPullRefresh()
                }
                override fun onLoadMore() {
                    onScrollLoadMore()
                }
            })
        }

        return view
    }

    protected abstract fun setLayoutResources(): Int
    protected abstract fun setRecyclerView(view: View): XRecyclerView?
    protected abstract fun onViewReady(view: View)
    protected abstract fun onPullRefresh()
    protected abstract fun onScrollLoadMore()
    protected abstract fun onReload()

    override fun getContext(): Context? {
        return mContext
    }
}
