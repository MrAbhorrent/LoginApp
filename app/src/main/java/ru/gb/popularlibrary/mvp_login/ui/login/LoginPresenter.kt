package ru.gb.popularlibrary.mvp_login.ui.login

import ru.gb.popularlibrary.mvp_login.domain.LoginUsecase

class LoginPresenter(
    private val loginUsecase: LoginUsecase
) : LoginContract.Presenter {

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
        loginUsecase.login(login, password) { result ->
            view?.hideProgress()
            if (result) {
                isSuccess = true
                view?.setSuccess()
            } else {
                isError = true
                view?.setError(errorString)
            }

        }
    }

    private fun checkCredentials(login: String, password: String): Boolean {
        var checkResult = false
        return checkResult
    }

    override fun onCredentialsChange() {
        // Nothing to do
    }
}