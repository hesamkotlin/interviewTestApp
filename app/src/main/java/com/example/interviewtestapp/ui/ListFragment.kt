package com.example.interviewtestapp.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.interviewtestapp.R
import com.example.interviewtestapp.databinding.FragmentListBinding
import com.example.interviewtestapp.shared.model.User
import com.example.interviewtestapp.ui.util.observe
import com.example.interviewtestapp.ui.util.showToast
import com.example.interviewtestapp.ui.viewmodel.ListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ListFragment : Fragment() {

    private lateinit var mBinding: FragmentListBinding
    private val viewModel: ListViewModel by viewModels()
    private val userAdapter = UserAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mBinding = FragmentListBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
            lifecycleOwner = this@ListFragment
        }
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        observe(viewModel.message) { toastError(it) }
        viewModel.getUSerList()
        observe(viewModel.list, ::initRecyclerView)
    }

    private fun initRecyclerView(users: List<User>) {
        mBinding.userRecyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            userAdapter.submitList(users)
            adapter = userAdapter
        }
    }

    private fun toastError(errorMessageId: Int) {
        showToast(getString(errorMessageId))
    }
}