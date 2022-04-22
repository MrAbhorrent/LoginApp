package ru.gb.popularlibrary.mvp_login.presenter

import java.lang.Thread.sleep
import ru.gb.popularlibrary.mvp_login.model.LoginModel

class LoginPresenter: LoginContract.Presenter {

    private val errorString = "Неверный пароль!!!"
    private var view: LoginContract.View? = null
    private var isSuccess = false
    private var isError = false

    override fun onAttach(view: LoginContract.View) {
        this.view = view
        if (isSuccess) {
            view.setSuccess()
        } else if (isError) {
            view.setError(errorString)
        }

    }

    override fun onLogin(login: String, password: String) {
        view?.showProgress()
        // Поток, по хорошему надо вынести в LoginModel
        Thread {
            sleep(3_000)
            view?.getHandler()?.post {
                view?.hideProgress()
                if (checkCredentials(login, password)) {
                    isSuccess = true
                    view?.setSuccess()
                } else {
                    isError = true
                    view?.setError(errorString)
                }
            }
        }.start()
    }

    private fun checkCredentials(login: String, password: String): Boolean {
        var checkResult = false
        if (login.isNotEmpty() && password.isNotEmpty()) {
            // Здесь надо данные отправить в модель. Типа на сетевой запрос
            checkResult = LoginModel().getDataAuth(login, password)
        }
        return  checkResult
    }

    override fun onCredentialsChange() {
        // Nothing to do
    }
}