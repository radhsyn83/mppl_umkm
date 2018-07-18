package xyz.radhzyn83.mpplumkm.domain.view

/**
 * Created by FathurRadhy on 1/29/2018.
 */
interface LoginView {
    fun LoginValidation();
    fun LoginSuccess(id_user: String, nama_user: String, email_user: String);
    fun LoginInvalidEmail();
    fun LoginFailed();
    fun LoginError();
    fun initView();
}