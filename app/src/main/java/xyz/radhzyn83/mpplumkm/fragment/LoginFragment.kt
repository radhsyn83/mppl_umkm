package xyz.radhzyn83.mpplumkm.fragment

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.text.InputType
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.*
import com.kotlin.model.LoginPresenter
import com.kotlin.model.LoginPresenterImpls
import kotlinx.android.synthetic.main.fragment_login.*
import xyz.radhzyn83.mpplumkm.R
import xyz.radhzyn83.mpplumkm.activity.MainActivity
import xyz.radhzyn83.mpplumkm.domain.view.LoginView
import xyz.radhzyn83.mpplumkm.utils.Const
import xyz.radhzyn83.mpplumkm.utils.SessionManager

class LoginFragment : Fragment(), LoginView, View.OnClickListener {
    private var mLoginPresenter: LoginPresenter? = null
    private var loginBtn: Button? = null
    private var registerBtn: TextView? = null
    private var forgotBtn: TextView? = null
    private var showHide: CheckBox? = null
    private var passwd: EditText? = null
    private var sm: SessionManager? = null
    private var pDialog: ProgressDialog? = null


    companion object {
        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater!!.inflate(R.layout.fragment_login, container, false)
        mLoginPresenter = LoginPresenterImpls(this)
        sm = SessionManager(activity!!.applicationContext)

        loginBtn = root.findViewById(R.id.loginBtn)
        forgotBtn = root.findViewById(R.id.forgot_password)
        registerBtn = root.findViewById(R.id.createAccount)
        showHide = root.findViewById(R.id.show_hide_password)
        passwd = root.findViewById(R.id.login_password)

        pDialog = ProgressDialog(activity!!)



        initView()

        return root
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.loginBtn -> {
                pDialog!!.setMessage("Mohon menunggu..")
                pDialog!!.setCancelable(false)
                pDialog!!.show()

                var email: String = login_emailid.text.toString()
                var password: String = login_password.text.toString()
                mLoginPresenter!!.doLogin(email,password)
            }
            R.id.createAccount -> {
//                fragmentManager!!
//                        .beginTransaction()
//                        .setCustomAnimations(R.anim.right_enter, R.anim.left_out)
//                        .replace(R.id.frameContainer, RegisterFragment(),
//                                Const().SignUp_Fragment()).commit()
                Toast.makeText(activity, "Register", Toast.LENGTH_SHORT).show()

            }
            R.id.forgot_password -> {
                Toast.makeText(activity, "Forgot", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun initView() {
        loginBtn!!.setOnClickListener(this)
        registerBtn!!.setOnClickListener(this)
        forgotBtn!!.setOnClickListener(this)
        showHide!!.setOnCheckedChangeListener{
            buttonView, isChecked ->
            if(isChecked) {
                show_hide_password.setText(R.string.hide_pwd)
                login_password.setInputType(InputType.TYPE_CLASS_TEXT)
                login_password.setTransformationMethod(HideReturnsTransformationMethod
                        .getInstance())
            } else {
                show_hide_password.setText(R.string.show_pwd)
                login_password.setInputType(InputType.TYPE_CLASS_TEXT or InputType.TYPE_TEXT_VARIATION_PASSWORD)
                login_password.setTransformationMethod(PasswordTransformationMethod
                        .getInstance())// hide password
            }
        }
        passwd!!.setOnEditorActionListener{
            v: TextView?, actionId: Int, event: KeyEvent? ->
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                loginBtn!!.performClick();
                return@setOnEditorActionListener true;
            }
            return@setOnEditorActionListener false;
        }
    }

    override fun LoginValidation() {
        Toast.makeText(activity, "Email dan Password tidak boleh kosong", Toast.LENGTH_SHORT).show()
    }

    override fun LoginInvalidEmail() {
        if (pDialog != null) {
            pDialog!!.dismiss()
        }
        Toast.makeText(activity, "Email tidak valid", Toast.LENGTH_SHORT).show()
    }

    override fun LoginSuccess(id_user: String, nama_user: String, email_user: String) {
        Toast.makeText(activity, "Selamat datang "+nama_user, Toast.LENGTH_SHORT).show()
        val handler = Handler()
        handler.postDelayed(Runnable {
            sm!!.storeLogin(id_user,email_user,nama_user)
            val i = Intent(activity, MainActivity::class.java)
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK)
            startActivity(i)
            activity!!.finish()
        }, 2000)
    }

    override fun LoginFailed() {
        if (pDialog != null) {
            pDialog!!.dismiss()
        }
        Toast.makeText(activity, "Email dan Password tidak cocok", Toast.LENGTH_SHORT).show()
    }

    override fun LoginError() {
        if (pDialog != null) {
            pDialog!!.dismiss()
        }
        Toast.makeText(activity, "Terjadi masalah pada koneksi", Toast.LENGTH_SHORT).show()
    }
}

