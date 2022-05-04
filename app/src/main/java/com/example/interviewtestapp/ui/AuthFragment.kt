package com.example.interviewtestapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.interviewtestapp.R
import com.example.interviewtestapp.databinding.FragmentAuthBinding
import com.example.interviewtestapp.domain.UserRepository
import com.example.interviewtestapp.shared.model.UserInfo
import com.example.interviewtestapp.ui.util.observe
import com.example.interviewtestapp.ui.util.showToast
import com.example.interviewtestapp.ui.viewmodel.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class AuthFragment : Fragment() {


    private val viewModel: AuthViewModel by viewModels()
    private lateinit var mBinding: FragmentAuthBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentAuthBinding.inflate(inflater, container, false)
        mBinding.viewmodel = viewModel
        mBinding.lifecycleOwner = this
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observe(viewModel.navigateToMap) { navigateToMap(it) }
        observe(viewModel.userInfoFailure) { toastError(it) }
        initViews()
    }

    private fun initViews() {
        mBinding.radiogroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.rb_male -> viewModel.onMaleSelected()
                R.id.rb_female -> viewModel.onFemaleSelected()
            }
        }
    }

    private fun toastError(errorMessageId: Int) {
        showToast(getString(errorMessageId))
    }

    private fun navigateToMap(userInfo: UserInfo) {
        val navDirection = AuthFragmentDirections.navigateToMapFragment(userInfo)
        findNavController().navigate(navDirection)
    }

}

