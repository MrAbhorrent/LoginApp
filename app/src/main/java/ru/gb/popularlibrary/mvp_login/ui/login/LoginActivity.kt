package ru.gb.popularlibrary.mvp_login.ui.login

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import ru.gb.popularlibrary.mvp_login.R
import ru.gb.popularlibrary.mvp_login.app
import ru.gb.popularlibrary.mvp_login.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private lateinit var binding: ActivityLoginBinding
    private var presenter: LoginContract.Presenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = getInstancePresenter()
        presenter?.onAttach(this)

        binding.loginButton.setOnClickListener {
            presenter?.onLogin(
                binding.loginEditText.text.toString(),
                binding.passwordEditText.text.toString(),
            )
        }

        binding.rememberPasswordTextView.setOnClickListener {
            showNotification()
        }
    }

    private fun showNotification() {
        Toast.makeText(this@LoginActivity, resources.getString(R.string.strRememberPassword), Toast.LENGTH_SHORT).show()
    }

    private fun getInstancePresenter(): LoginPresenter {
        val presenter = lastCustomNonConfigurationInstance as? LoginPresenter

        return presenter ?: LoginPresenter(app.loginUsecase)
    }

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return presenter
    }

    @MainThread
    override fun setSuccess() {
        // Скрываем поля на экране после успешного логина
        with(binding) {
            loginButton.isVisible = false
            loginTitleTextView.isVisible = false
            loginEditText.isVisible = false
            passwordTitleTextView.isVisible = false
            passwordEditText.isVisible = false
            rememberPasswordTextView.isVisible = false
            root.setBackgroundColor(resources.getColor(R.color.successLogin))
        }
    }

    @MainThread
    override fun setError(error: String) {
        Toast.makeText(this, "ERROR: $error", Toast.LENGTH_SHORT).show()
    }

    @MainThread
    override fun showProgress() {
        // Процесс авторизации. Начало
        // Надо отображать прогрессбар и делать кнопку неактивной
        binding.loginButton.isEnabled = false
        binding.authorizatonProgressProgressBar.isVisible = true
    }

    @MainThread
    override fun hideProgress() {
        // Процесс авторизации. Конец
        // Скрываем прогрессбар и даем доступ к кнопке
        binding.loginButton.isEnabled = true
        binding.authorizatonProgressProgressBar.isVisible = false
    }

    override fun getHandler(): Handler {
        return Handler(Looper.getMainLooper())
    }
}