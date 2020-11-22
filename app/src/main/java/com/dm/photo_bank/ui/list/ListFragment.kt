package com.dm.photo_bank.ui.list

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.dm.photo_bank.R
import com.dm.photo_bank.core.Resource
import kotlinx.android.synthetic.main.list_fragment.*
import org.koin.android.viewmodel.ext.android.viewModel


class ListFragment: Fragment(R.layout.list_fragment) {

    private val viewModel:ListViewModel by viewModel()
    private val adapter = ListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserver()
    }

    private fun initObserver() {
        viewModel.listLiveData.observe(viewLifecycleOwner){
            when (it) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    showLoading(false)
                    adapter.setData(it.data)
                }
            }
        }
    }

    private fun initView() {
        rv.layoutManager = LinearLayoutManager(context)
        rv.adapter = adapter
        btSearch.setOnClickListener {
            viewModel.searchPhoto(etSearch.text.toString())
        }
    }

    private fun showLoading(isLoading: Boolean) {
        progressBar.isVisible = isLoading
    }
}