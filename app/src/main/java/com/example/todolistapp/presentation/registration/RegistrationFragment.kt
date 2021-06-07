package com.example.todolistapp.presentation.registration

import android.content.Context
import android.os.Bundle
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
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

    override fun onAttach(context: Context) {
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_registration, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        continueButton.setOnClickListener {
            register()
        }
        errorTextView.setOnClickListener { errorTextView.visibility = View.GONE }
    }

    override fun onStart() {
        super.onStart()
        presenter.bindView(this)
    }

    override fun onStop() {
        super.onStop()
        presenter.unbindView()

    }

    override fun onDetach() {
        super.onDetach()
    }

    override fun showProgressBar() {
        regProgressBar.visibility = View.VISIBLE
    }

    override fun hideProgressBar() {
        regProgressBar.visibility = View.GONE
    }

    override fun showError() {
        errorTextView.visibility = View.VISIBLE
    }


    private fun register() {
        if (emailEditText.text.toString()
                .isNotEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailEditText.text.toString())
                .matches()
        ) {
            presenter.registerUser(
                nameEditText.text.toString(),
                passwordEditText.text.toString(),
                emailEditText.text.toString(),
                ageEditText.text.toString()
            )
        } else {
            Toast.makeText(requireContext(), "Invalid Email Address!", Toast.LENGTH_SHORT).show()
        }
    }

}