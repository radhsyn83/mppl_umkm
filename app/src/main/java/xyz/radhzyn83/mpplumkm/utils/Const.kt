package xyz.radhzyn83.mpplumkm.utils

import android.content.Context
import android.support.v7.app.AppCompatActivity
import org.jetbrains.anko.toast
import java.text.NumberFormat
import java.util.*

/**
 * Created by FathurRadhy on 1/30/2018.
 */
class Const{


//
//    private var sm: SessionManager? = null
//
//    //id_user
//    fun getId_user(): String {
//        sm = SessionManager(context)
//        val map = sm!!.getDetailLogin()
//        return map.get(sm!!.KEY_ID).toString()
//    }

    fun START_DEFAULT() = 0

    //Email Validation pattern
    fun regEx() = "\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b"

    //Url API
    fun API_URL() = "http://api.radhsyn83.xyz/"
    fun UPLOAD_URL() = "http://api.radhsyn83.xyz/upload_bukti/"

    fun toRupiah(angka: String?): CharSequence? {
        var res = ""
        var angka = angka
        val Rupiah = "Rp"

        if (angka == "") {
            angka = "0"
        }

        val rupiahFormat = NumberFormat.getInstance(Locale.GERMANY)
        try {
            val rupiah = rupiahFormat.format(java.lang.Double.parseDouble(angka))
            res = Rupiah + rupiah
        } catch (e: KotlinNullPointerException) {
        }
        return res
    }

    //Fragments Tags
    fun Login_Fragment() = "LoginFragment"
    fun SignUp_Fragment() = "RegisterFragment"
    fun ForgotPassword_Fragment() = "ForgotPasswordFragment"
    fun Home_Fragment() = "HomeFragment"
    fun Favorit_Fragment() = "FavoritFragment"
    fun Transaksi_Fragment() = "TransaksiFragment"
    fun Notif_Fragment() = "NotifFragment"
    fun Akun_Fragment() = "AkunFragment"
    fun Pengiriman_Fragment() = "PengirimanFragment"
    fun Pembelian_Fragment() = "PembelianFragment"
    fun Finish_Fragment() = "FinishFragment"
    fun Search_recent_fragment() = "SearchRecentFragment"
    fun Search_result_fragment() = "SearchResultFragment"
}
