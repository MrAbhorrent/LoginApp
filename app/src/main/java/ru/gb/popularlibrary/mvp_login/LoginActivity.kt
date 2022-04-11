package ru.gb.popularlibrary.mvp_login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.core.view.isVisible
import ru.gb.popularlibrary.mvp_login.databinding.ActivityMainBinding
import ru.gb.popularlibrary.mvp_login.presenter.LoginContract
import ru.gb.popularlibrary.mvp_login.presenter.LoginPresenter

class LoginActivity : AppCompatActivity(), LoginContract.View {

    private lateinit var binding: ActivityMainBinding
    private var presenter: LoginContract.Presenter? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = getInstancePresenter()
        presenter?.onAttach(this)

        binding.btnLogin.setOnClickListener {
            presenter?.onLogin(
                binding.edtLogin.text.toString(),
                binding.edtPassword.text.toString(),
            )
        }

        binding.tvRememberPassword.setOnClickListener {
            showNotification()
        }
    }

    private fun showNotification() {
        Toast.makeText(this@LoginActivity, resources.getString(R.string.strRememberPassword), Toast.LENGTH_SHORT).show()
    }

    private fun getInstancePresenter(): LoginPresenter {
        val presenter = lastCustomNonConfigurationInstance as? LoginPresenter
        return presenter ?: LoginPresenter()
    }

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return presenter
    }

    @MainThread
    override fun setSuccess() {
        // Скрываем поля на экране после успешного логина
        with(binding) {
            btnLogin.isVisible = false
            tvLoginTitle.isVisible = false
            edtLogin.isVisible = false
            tvPasswordTitle.isVisible = false
            edtPassword.isVisible = false
            tvRememberPassword.isVisible = false
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
        binding.btnLogin.isEnabled = false
        binding.pbAuthorizatonProgress.isVisible = true
    }

    @MainThread
    override fun hideProgress() {
        // Процесс авторизации. Конец
        // Скрываем прогрессбар и даем доступ к кнопке
        binding.btnLogin.isEnabled = true
        binding.pbAuthorizatonProgress.isVisible = false
    }

    override fun getHandler(): Handler {
        return Handler(Looper.getMainLooper())
    }
}