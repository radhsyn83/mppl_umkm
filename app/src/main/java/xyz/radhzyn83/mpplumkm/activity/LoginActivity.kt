package xyz.radhzyn83.mpplumkm.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import xyz.radhzyn83.mpplumkm.R
import xyz.radhzyn83.mpplumkm.fragment.LoginFragment
import xyz.radhzyn83.mpplumkm.utils.Const

class LoginActivity : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        if (savedInstanceState == null) {
            supportFragmentManager
                    .beginTransaction()
                    .add(R.id.frameContainer, LoginFragment.newInstance(), Const().Login_Fragment())
                    .commit()
        }
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(R.anim.slide_left_in,R.anim.slide_right_out)
    }

}