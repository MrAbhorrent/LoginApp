package ru.gb.popularlibrary.mvp_login.ui.registration

import ru.gb.popularlibrary.mvp_login.domain.RegistrationUsecase

class RegistrationViewModel(
    private val registratioUsecase: RegistrationUsecase
): RegistrationContract.ViewModel {

    private val errorString = "Ошибка регистрации!!!"
    private var view: RegistrationContract.View? = null
    private var isSuccess = false
    private var isError = false

    override fun onAttach(registrationView: RegistrationContract.View) {
        this.view = registrationView
        if (isSuccess) {
            registrationView.setSuccess()
        } else if (isError) {
            registrationView.setError(errorString)
        }
    }

    override fun onRegistration(
        newUserLogin: String,
        newUserEmail: String,
        newUserPassword: String,
        newUserPasswordRepeat: String
    ) {
        view?.showProgress()
        registratioUsecase.registration(
            newUserLogin,
            newUserEmail,
            newUserPassword,
            newUserPasswordRepeat) { result ->
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
}