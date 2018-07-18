package xyz.radhzyn83.mpplumkm.activity

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import android.view.View

import xyz.radhzyn83.mpplumkm.R
import xyz.radhzyn83.mpplumkm.utils.SessionManager

abstract class BaseActivity : AppCompatActivity() {

    private val loading = false
    private val pastVisiblesItems: Int = 0
    private val visibleItemCount: Int = 0
    private val totalItemCount: Int = 0
    private var mToolbar: Toolbar? = null
    public var sm: SessionManager? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(setLayoutResource())
        setToolBar(setToolBar(), setToolBarTitle())

        sm = SessionManager(this)
        sm!!.checkLogin()

        onViewReady(this, savedInstanceState)
    }

    protected abstract fun setLayoutResource(): Int

    protected abstract fun setToolBar(): Toolbar

    protected abstract fun setToolBarTitle(): String

    private fun setToolBar(toolbar: Toolbar?, title: String) {
        if (toolbar != null) {
            mToolbar = toolbar
            toolbar.setTitleTextColor(Color.WHITE)
            toolbar.title = title
            setSupportActionBar(toolbar)
        }
    }

    internal fun changeToolBarTitle(title: String) {
        mToolbar!!.title = title
    }

    protected abstract fun onViewReady(context: Context, savedInstanceState: Bundle?)

}
