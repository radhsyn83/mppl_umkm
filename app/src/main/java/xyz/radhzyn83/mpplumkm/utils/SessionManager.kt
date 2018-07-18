package xyz.radhzyn83.mpplumkm.utils

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import xyz.radhzyn83.mpplumkm.activity.LoginActivity
import java.util.*

/**
 * Created by FathurRadhy on 1/30/2018.
 */
class SessionManager {
    private var sp: SharedPreferences
    private var editor: SharedPreferences.Editor

    val KEY_ID = "id"
    val KEY_EMAIL = "email"
    val KEY_NAMA = "nama"
    private val IS_LOGIN = "loginstatus"
    private val SHARE_NAME = "loginsession"
    private val MODE_PRIVATE = 0
    private var _contex: Context

    constructor(context: Context)
    {
        this._contex = context
        sp = _contex.getSharedPreferences(SHARE_NAME, MODE_PRIVATE)
        editor = sp.edit()
    }

    fun storeLogin(id: String, email: String, nama: String)
    {
        editor.putBoolean(IS_LOGIN, true)
        editor.putString(KEY_ID, id)
        editor.putString(KEY_EMAIL, email)
        editor.putString(KEY_NAMA, nama)
        editor.commit()
    }

    fun getDetailLogin(): HashMap<*, *> {
        val map = HashMap<String, String>()
        map.put(KEY_ID, sp.getString(KEY_ID, null))
        map.put(KEY_EMAIL, sp.getString(KEY_EMAIL, null))
        map.put(KEY_NAMA, sp.getString(KEY_NAMA, null))
        return map
    }

    fun checkLogin()
    {
        if (!this.login()!!) {
            val i = Intent(_contex, LoginActivity::class.java)
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            _contex.startActivity(i)
        }
    }

    fun login(): Boolean?
    {
        return sp.getBoolean(IS_LOGIN, false)
    }

    fun logout()
    {
        editor.clear()
        editor.commit()
    }
}