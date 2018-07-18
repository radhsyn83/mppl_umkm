package xyz.radhzyn83.mpplumkm.adapter

import android.content.Context
import android.support.v4.view.PagerAdapter
import android.support.v4.view.ViewPager
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import xyz.radhzyn83.mpplumkm.R
import xyz.radhzyn83.mpplumkm.domain.model.ProdukFotoModelResult

/**
 * Created by FathurRadhy on 2/17/2018.
 */
class DetailFotoAdapter(val context: Context, private val foto: ArrayList<ProdukFotoModelResult>) : PagerAdapter() {
    private var layoutInflater: LayoutInflater? = null

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun getCount(): Int {
        return foto.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater!!.inflate(R.layout.item_detail_foto, null)
        val imageView = view.findViewById<ImageView>(R.id.imageView)
        val link_produk = "https://mppl.radhsyn83.xyz/uploads/produk/"

        Log.d("Penting","link foto "+link_produk+foto.get(position).link!!)

        Glide.with(context)
                .load(link_produk+foto.get(position).link!!)
                .apply(RequestOptions()
                        .placeholder(R.drawable.placeholder))
                .into(imageView)

        val vp = container as ViewPager
        vp.addView(view, 0)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val vp = container as ViewPager
        val view = `object` as View
        vp.removeView(view)
    }
}