package xyz.radhzyn83.mpplumkm.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.design.widget.AppBarLayout
import android.support.v4.content.ContextCompat
import android.support.v4.view.ViewPager
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import com.kotlin.model.DetailPresenterImpls
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.content_detail.*
import xyz.radhzyn83.mpplumkm.R
import xyz.radhzyn83.mpplumkm.adapter.DetailFotoAdapter
import xyz.radhzyn83.mpplumkm.domain.model.DetailProdukModel
import xyz.radhzyn83.mpplumkm.domain.model.DetailProdukModelResult
import xyz.radhzyn83.mpplumkm.domain.model.ProdukFotoModelResult
import xyz.radhzyn83.mpplumkm.domain.view.DetailView
import xyz.radhzyn83.mpplumkm.utils.Const

class DetailActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var id_produk: String
    private var dotscount: Int = 0
    private var dots: Array<ImageView?>? = null
    private var no_hp: String? = null
    private var text_order: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        setSupportActionBar(toolbar)
        toolbar_title.animate().alpha(0.0f)
        back_btn.animate().alpha(0.0f)
        app_bar.addOnOffsetChangedListener(object : AppBarLayout.OnOffsetChangedListener {
            internal var isVisible = true
            internal var scrollRange = -1
            override fun onOffsetChanged(appBarLayout: AppBarLayout, verticalOffset: Int) {
                var of = scrollRange + verticalOffset
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.totalScrollRange
                }
                if (of < 300) {
                    toolbar_title.animate().alpha(1.0f)
                    back_btn.animate().alpha(1.0f)
                    isVisible = true
                } else if (isVisible) {
                    toolbar_title.animate().alpha(0.0f)
                    back_btn.animate().alpha(0.0f)
                    isVisible = false
                }
                Log.d("SCROL",""+scrollRange+" | "+verticalOffset+" | "+of)
            }
        })

        id_produk = intent.getStringExtra("id_produk")

        Log.d("Penting","idProduk "+id_produk)

        back_btn.setOnClickListener { view -> finish() }
        back_btn2.setOnClickListener { view -> finish() }
        back_btn2.bringToFront()
        whatsapp.setOnClickListener(this)

        loadDataProduk()
    }

    fun loadDataProduk() {
        DetailPresenterImpls(object : DetailView{
            override fun onGetSuccess(res: DetailProdukModel) {
                loadProdukFoto(res.produk_foto)
                loadProdukDetail(res.produk)
            }

            override fun onGetError(e: String) {
                Log.d("Penting","Error : "+e)
            }
        }).doLoad(id_produk)
    }

    fun loadProdukFoto(res: ArrayList<ProdukFotoModelResult>) {
        val sliderAdapter = DetailFotoAdapter(this, res)
        viewPager.setAdapter(sliderAdapter)

        dotscount = sliderAdapter.getCount()
        dots = arrayOfNulls<ImageView>(dotscount)

        for (i in 0 until dotscount) {
            dots!![i] = ImageView(this@DetailActivity)
            dots!![i]!!.setImageDrawable(ContextCompat.getDrawable(this@DetailActivity, R.drawable.dots_nonactive))
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
            params.setMargins(5, 0, 5, 0)
            viewPager_dots.addView(dots!![i], params)
        }
        dots!![0]!!.setImageDrawable(ContextCompat.getDrawable(this@DetailActivity, R.drawable.dots_active))

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) {}
            override fun onPageSelected(position: Int) {
                for (i in 0 until dotscount) {
                    dots!![i]!!.setImageDrawable(ContextCompat.getDrawable(this@DetailActivity, R.drawable.dots_nonactive))
                }
                dots!![position]!!.setImageDrawable(ContextCompat.getDrawable(this@DetailActivity, R.drawable.dots_active))

                if (position == dotscount) {
                    viewPager.setCurrentItem(0)
                }

            }
            override fun onPageScrollStateChanged(state: Int) {}
        })
    }

    fun loadProdukDetail(res: DetailProdukModelResult) {
        toolbar_title.text = res.nama
        kode_produk.text = res.kode
        harga_produk.text = Const().toRupiah(res.harga)
        merek_produk.text = res.merek
        berat_produk.text = res.berat + " gram"
        produk_deskripsi.text = res.deskripsi
        no_hp = res.kontak
        text_order = res.text
    }

    override fun onClick(v: View?) {
        val url = "https://api.whatsapp.com/send?phone=6282272605775&text=$text_order"
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(url)
        startActivity(i)
    }
}
