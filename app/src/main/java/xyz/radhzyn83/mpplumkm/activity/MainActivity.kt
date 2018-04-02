package xyz.radhzyn83.mpplumkm.activity

import android.content.Context
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import kotlinx.android.synthetic.main.activity_main.*
import xyz.radhzyn83.mpplumkm.R
import xyz.radhzyn83.mpplumkm.adapter.ViewPagerAdapter
import xyz.radhzyn83.mpplumkm.fragment.DashboardFragment
import xyz.radhzyn83.mpplumkm.fragment.HomeFragment
import xyz.radhzyn83.mpplumkm.fragment.UserFragment

class MainActivity : BaseActivity() {

    private var f_home: Fragment = HomeFragment()
    private var f_dashboard: Fragment = DashboardFragment()
    private var f_user: Fragment = UserFragment()

    private var mViewPagerAdapter: ViewPagerAdapter? = null
    private var prevMenuItem: MenuItem? = null
    private var mItemCart: MenuItem? = null
    private var mItemNotificatiom: MenuItem? = null

    override fun setLayoutResource(): Int {
        return R.layout.activity_main
    }

    override fun setToolBar(): Toolbar {
        return toolbar
    }

    override fun setToolBarTitle(): String {
        return resources.getString(R.string.home)
    }

    override fun onViewReady(context: Context, savedInstanceState: Bundle?) {
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        setupViewPager(viewpager)

        val limit = if (mViewPagerAdapter!!.getCount() > 1) mViewPagerAdapter!!.getCount() - 1 else 1
        viewpager.setOffscreenPageLimit(limit)
    }

    private fun setupViewPager(viewPager: ViewPager) {
        mViewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        val limit = if (mViewPagerAdapter!!.getCount() > 1) mViewPagerAdapter!!.getCount() - 1 else 1
        mViewPagerAdapter!!.addFragment(f_home)
        mViewPagerAdapter!!.addFragment(f_dashboard)
        mViewPagerAdapter!!.addFragment(f_user)
        viewPager.adapter = mViewPagerAdapter
        viewPager.offscreenPageLimit = limit
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                viewpager.setCurrentItem(0)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                viewpager.setCurrentItem(1)
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_user -> {
                viewpager.setCurrentItem(2)
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

}
