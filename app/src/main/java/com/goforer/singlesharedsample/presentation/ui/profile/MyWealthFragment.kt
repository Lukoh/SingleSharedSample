/*
 * Copyright (C) 2021 Lukoh Nam, goForer
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 2 of the License,
 * or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License along with this program.
 * If not, see <http://www.gnu.org/licenses/>.
 */

package com.goforer.singlesharedsample.presentation.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.goforer.singlesharedsample.data.source.model.entity.share.WealthInfo
import com.goforer.singlesharedsample.databinding.FragmentMyWealthBinding
import com.goforer.singlesharedsample.presentation.ui.BaseFragment
import com.goforer.singlesharedsample.presentation.vm.wealth.SharedWealthViewModel
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
            fillWealth(it)
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