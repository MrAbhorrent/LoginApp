package ru.gb.popularlibrary.mvp_login.ui.registration

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.widget.Toast
import androidx.annotation.MainThread
import androidx.core.view.isVisible
import ru.gb.popularlibrary.mvp_login.R
import ru.gb.popularlibrary.mvp_login.app
import ru.gb.popularlibrary.mvp_login.databinding.ActivityRegistrationBinding

class RegistrationActivity : AppCompatActivity(), RegistrationContract.View {

    private lateinit var binding: ActivityRegistrationBinding
    private var presenter: RegistrationContract.Presenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegistrationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        presenter = getInstancePresenter()
        presenter?.onAttach(this)

        binding.registrationButton.setOnClickListener {
            (presenter as RegistrationPresenter).onRegistration(
                binding.loginEditText.text.toString(),
                binding.emailEditText.text.toString(),
                binding.inputPasswordEditText.text.toString(),
                binding.confirmPasswordEditText.text.toString()
            )
        }
    }

    private fun getInstancePresenter(): RegistrationPresenter {
        val presenter = lastCustomNonConfigurationInstance as? RegistrationPresenter

        return presenter ?: RegistrationPresenter(app.registrationUsecase)
    }

    override fun onRetainCustomNonConfigurationInstance(): Any? {
        return presenter
    }

    override fun setSuccess() {
        with(binding) {
            loginEditText.isEnabled = false
            emailEditText.isEnabled = false
            inputPasswordEditText.isEnabled = false
            confirmPasswordEditText.isEnabled = false
            registrationButton.isEnabled = false
            root.setBackgroundColor(resources.getColor(R.color.successRegistration))
        }
    }

    override fun setError(error: String) {
        Toast.makeText(this, "ERROR: $error", Toast.LENGTH_SHORT).show()
    }

    @MainThread
    override fun showProgress() {
        // Процесс регистрации нового пользователя. Начало
        // Надо отображать прогрессбар и делать кнопку неактивной
        binding.registrationButton.isEnabled = false
        binding.registrationProgressProgressBar.isVisible = true
    }

    @MainThread
    override fun hideProgress() {
        // Процесс регистрации нового пользователя. Конец
        // Скрываем прогрессбар и даем доступ к кнопке
        binding.registrationButton.isEnabled = true
        binding.registrationProgressProgressBar.isVisible = false
    }

    override fun getHandler(): Handler {
        return Handler(Looper.getMainLooper())
    }

}