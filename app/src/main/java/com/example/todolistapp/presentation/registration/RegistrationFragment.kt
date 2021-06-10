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
            if (text != null) {
                if (text.length < 7) {
                    textInputLayoutPasswordRegistration.error =
                        "Пароль должен содержать не менее 7 символов"
                } else {
                    textInputLayoutPasswordRegistration.error =
                        null
                }
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
        textInputLayoutEmailAddress.error = "Пользователь уже зарегистрирован"
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
        if (nameIsEmpty()) {
            textInputLayoutName.error = "Поле не может быть пустым"
            return false
        } else {
            textInputLayoutName.error = null
            return true
        }
    }

    private fun nameIsEmpty(): Boolean {
        return nameEditText.text.toString().isEmpty()
    }

    private fun validateUserAge(): Boolean {
        if (ageIsEmpty()) {
            textInputLayoutAge.error = "Поле не может быть пустым"
            return false
        } else {
            textInputLayoutName.error = null
            return true
        }
    }

    private fun ageIsEmpty(): Boolean {
        return ageEditText.text.toString().isEmpty()
    }

    private fun validateEmail(): Boolean {
        if (emailIsEmpty()) {
            textInputLayoutEmailAddress.error = "Поле не может быть пустым"
            return false
        } else if (emailIsCorrect()) {
            textInputLayoutEmailAddress.error = "Некорректный Email"
            return false
        } else {
            textInputLayoutName.error = null
            return true
        }
    }

    private fun emailIsEmpty(): Boolean {
        return emailEditText.text.toString().isEmpty()
    }

    private fun emailIsCorrect(): Boolean {
        return !Patterns.EMAIL_ADDRESS.matcher(emailEditText.text.toString()).matches()
    }

    private fun validatePassword(): Boolean {
        if (passwordIsEmpty()) {
            textInputLayoutPasswordRegistration.error = "Поле не может быть пустым"
            return false
        } else {
            textInputLayoutName.error = null
            return true
        }
    }

    private fun passwordIsEmpty(): Boolean {
        return passwordEditText.text.toString().isEmpty()
    }

}