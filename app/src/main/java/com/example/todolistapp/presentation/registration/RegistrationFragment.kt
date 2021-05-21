package com.example.todolistapp.presentation.registration

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.todolistapp.R
import com.example.todolistapp.Router
import com.example.todolistapp.data.repositories.Repository
import com.example.todolistapp.utils.BaseFragment
import com.example.todolistapp.utils.BasePresenter
import com.example.todolistapp.utils.BaseView
import com.example.todolistapp.utils.PresentersStorage
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.fragment_registration.*


class RegistrationFragment : BaseFragment(), RegistrationView {

    companion object {
        fun newInstance(): RegistrationFragment {
            return RegistrationFragment()
        }
    }

    private lateinit var presenter: RegistrationPresenter
    private var router: Router? = null


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
        if (context is Router) {
            router = context
        }
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
        router = null
    }

    override fun navigateToTasksScreen() {
        router?.navigateToTasksScreen()
    }

    override fun showProgressBar() {
        regProgressBar.visibility=View.VISIBLE
    }

    override fun hideProgressBar() {
        regProgressBar.visibility=View.INVISIBLE
    }

    private fun register() {
        presenter.registerUser(
            nameEditText.text.toString(),
            passwordEditText.text.toString(),
            emailEditText.text.toString(),
            ageEditText.text.toString()
        )
    }

}