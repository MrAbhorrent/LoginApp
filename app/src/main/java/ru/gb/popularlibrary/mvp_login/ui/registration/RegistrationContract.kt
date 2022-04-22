package ru.gb.popularlibrary.mvp_login.ui.registration

import android.os.Handler
import androidx.annotation.MainThread

class RegistrationContract {

    interface View {
        @MainThread
        fun setSuccess()

        @MainThread
        fun setError(error: String)

        @MainThread
        fun showProgress()

        @MainThread
        fun hideProgress()

        @MainThread
        fun getHandler(): Handler
    }

    interface Presenter {

        @MainThread
        fun onAttach(view: View)

        @MainThread
        fun onRegistration(
            newUserLogin: String,
            newUserEmail: String,
            newUserPassword: String,
            newUserPasswordRepeat: String
        )
    }


}