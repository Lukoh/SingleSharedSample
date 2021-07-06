package com.goforer.singlesharedsample.presentation.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.goforer.singlesharedsample.data.source.model.entity.share.WealthInfo
import com.goforer.singlesharedsample.databinding.FragmentMyWealthBinding
import com.goforer.singlesharedsample.presentation.ui.BaseFragment
import com.goforer.singlesharedsample.presentation.vm.SharedWealthViewModel
import javax.inject.Inject

class MyWealthFragment : BaseFragment<FragmentMyWealthBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMyWealthBinding
        get() = FragmentMyWealthBinding::inflate

    @Inject
    internal lateinit var sharedWealthViewModel: SharedWealthViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        observeMyWealth()
        binding.btNextBig.setOnClickListener {
        }
    }

    private fun observeMyWealth() {
        sharedWealthViewModel.shared {
            it?.let {
                fillWealth(it)
            }
        }
    }

    private fun fillWealth(myWealthInfo: WealthInfo) {
        with(binding) {
            etWealthType.setText(myWealthInfo.type)
            etItem.setText(myWealthInfo.item)
            etAmount.setText(myWealthInfo.amount)
            etCurrency.setText(myWealthInfo.currency)
        }
    }
}