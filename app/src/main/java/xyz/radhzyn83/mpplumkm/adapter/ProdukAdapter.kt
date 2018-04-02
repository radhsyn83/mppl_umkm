package xyz.radhzyn83.mpplumkm.adapter

import android.app.Activity
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.item_produk.view.*
import org.jetbrains.anko.backgroundResource
import org.jetbrains.anko.imageResource
import xyz.radhzyn83.mpplumkm.R
import xyz.radhzyn83.mpplumkm.domain.model.ProdukModelResult
import xyz.radhzyn83.mpplumkm.utils.Const

/**
 * Created by FathurRadhy on 3/2/2018.
 */
class ProdukAdapter(val context: Activity, private val list: ArrayList<ProdukModelResult>) : RecyclerView.Adapter<ProdukAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_produk, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = list.count()

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(this.context,list[position])
    }

    inner class ViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bind(c: Activity, data: ProdukModelResult) {
            val link_produk = "https://mppl.radhsyn83.xyz/uploads/produk/"
            val link_user = "https://mppl.radhsyn83.xyz/uploads/user/"
            if(data.profile_foto == null || data.profile_foto.equals("")) {
                itemView.iv_profil_foto.imageResource = R.drawable.placeholder_user
            } else {
                Glide.with(c).load(link_user+data.profile_foto).apply(RequestOptions()
                        .placeholder(R.drawable.placeholder_user)).into(itemView.iv_profil_foto)
            }
            Glide.with(c).load(link_produk+data.link).apply(RequestOptions()
                    .placeholder(R.drawable.placeholder_shirt)).into(itemView.iv_produk_foto)

            itemView.tv_user.text = data.nama_user
            itemView.tv_harga.text = Const().toRupiah(data.harga)
            itemView.tv_date.text = data.date_add
            itemView.tv_judul.text = data.nama
        }
    }

    fun addItem(chatLists: ArrayList<ProdukModelResult>) {
        list.addAll(chatLists)
        notifyDataSetChanged()
    }
}
