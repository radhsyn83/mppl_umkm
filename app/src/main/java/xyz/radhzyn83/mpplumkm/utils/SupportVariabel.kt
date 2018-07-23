package xyz.radhzyn83.mpplumkm.utils

import android.content.Context

class SupportVariabel(val context: Context) {
    private var sm: SessionManager? = null

    //id_user
    fun getId_user(): String {
        sm = SessionManager(context)
        val map = sm!!.getDetailLogin()
        return map.get(sm!!.KEY_ID).toString()
    }

    //email
    fun getEmail_user(): String {
        sm = SessionManager(context)
        val map = sm!!.getDetailLogin()
        return map.get(sm!!.KEY_EMAIL).toString()
    }

    //nama
    fun getName_user(): String {
        sm = SessionManager(context)
        val map = sm!!.getDetailLogin()
        return map.get(sm!!.KEY_NAMA).toString()
    }
}