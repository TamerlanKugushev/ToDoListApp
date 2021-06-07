package com.example.todolistapp.presentation.registration

import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import com.example.todolistapp.R
import com.example.todolistapp.utils.BaseFragment
import com.example.todolistapp.utils.PresentersStorage
import kotlinx.android.synthetic.main.fragment_registration.*


class RegistrationFragment : BaseFragment(), RegistrationView {

    companion object {
        fun newInstance(): RegistrationFragment {
            return RegistrationFragment()
        }
    }

    private lateinit var presenter: RegistrationPresenter

    override fun attachPresenter() {
        val presenter = PresentersStorage.getPresenter(viewId)
        if (presenter !is RegistrationPresenter) {
            this.presenter = RegistrationPresenter()
            return
        }
        this.presenter = presenter
    }

    override fun getPresenter(): RegistrationPresenter {
        return presenter
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        continueButton.setOnClickListener {
            register()
        }

        passwordEditText.doOnTextChanged { text, start, before, count ->
            if (text!!.length < 6) {
                textInputLayoutPasswordRegistration.error =
                    "Пароль должен содержать не менее 7 символов"
            } else if (text.length > 6) {
                textInputLayoutPasswordRegistration.error =
                    null
            }
        }
    }

    override fun onStart() {
        super.onStart()
        presenter.bindView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.unbindView()
    }

    override fun showProgressBar() {
        regProgressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        regProgressBar.visibility = View.GONE
    }

    override fun showError() {
        textInputLayoutEmailAddress.error ="Пользователь уже зарегистрирован"
    }

    private fun register() {
        if (validateUserName() && validateUserAge() && validateEmail() && validatePassword())
            presenter.registerUser(
                nameEditText.text.toString(),
                passwordEditText.text.toString(),
                emailEditText.text.toString(),
                ageEditText.text.toString()
            )
    }

    private fun validateUserName(): Boolean {
        return if (nameEditText.text.toString().isEmpty()) {
            textInputLayoutName.error = "Поле не может быть пустым"
            false
        } else {
            textInputLayoutName.error = null
            true
        }
    }

    private fun validateUserAge(): Boolean {
        return if (ageEditText.text.toString().isEmpty()) {
            textInputLayoutAge.error = "Поле не может быть пустым"
            false
        } else {
            textInputLayoutName.error = null
            true
        }
    }

    private fun validateEmail(): Boolean {
        return if (emailEditText.text.toString().isEmpty()) {
            textInputLayoutEmailAddress.error = "Поле не может быть пустым"
            false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(emailEditText.text.toString())
                .matches()
        ) {
            textInputLayoutEmailAddress.error = "Ваша электронная почта недействительна"
            false
        } else {
            textInputLayoutName.error = null
            true
        }
    }

    private fun validatePassword(): Boolean {
        return if (passwordEditText.text.toString().isEmpty()) {
            textInputLayoutPasswordRegistration.error = "Поле не может быть пустым"
            false
        } else {
            textInputLayoutName.error = null
            true
        }
    }

}