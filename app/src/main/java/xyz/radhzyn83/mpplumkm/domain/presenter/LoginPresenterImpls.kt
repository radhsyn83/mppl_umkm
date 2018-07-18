package com.kotlin.model

import android.util.Log
import com.kotlin.utils.RetrofitClient
import retrofit2.Call
import retrofit2.Response
import xyz.radhzyn83.mpplumkm.domain.model.LoginResponse
import xyz.radhzyn83.mpplumkm.domain.view.LoginView
import xyz.radhzyn83.mpplumkm.utils.SessionManager
import java.util.regex.Pattern

/**
 * Created by FathurRadhy on 1/29/2018.
 */
class LoginPresenterImpls : LoginPresenter {
    var mLoginView: LoginView
    private var sm: SessionManager? = null

    constructor(loginView: LoginView) {
        this.mLoginView = loginView
    }

    override fun doLogin(email: String, password: String) {
        val p = Pattern.compile("\\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\\b")
        val m = p.matcher(email)

        if (email == "" || email.length == 0
                || password == "" || password.length == 0){
            mLoginView.LoginValidation()
        }
        else
        {
            if ( !m.find() ) {
                mLoginView.LoginInvalidEmail()
            }
            else
            {
                val call = RetrofitClient().postLogin(email, password)
                call.enqueue(object : retrofit2.Callback<LoginResponse> {
                    override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                        Log.d("Retrofit", "response : " + response.message())
                        val res = response.body()
                        if (res!!.success == true)
                        {
                            res!!.result.map {
                                val id_user = it.id
                                val nama_user = it.nama
                                val email_user = it.email
                                mLoginView.LoginSuccess(id_user,nama_user,email_user)
                            }
                        }
                        else
                        {
                            mLoginView.LoginFailed()
                            Log.e("ERROR RETROFIT", response.message())
                        }
                    }

                    override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                        mLoginView.LoginError()
                        Log.e("ERROR RETROFIT", t.toString())
                    }
                })
            }
        }
    }

}