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
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.goforer.singlesharedsample.R
import com.goforer.singlesharedsample.data.source.model.entity.share.MyInfo
import com.goforer.singlesharedsample.data.source.model.entity.share.WealthInfo
import com.goforer.singlesharedsample.databinding.FragmentFirstBinding
import com.goforer.singlesharedsample.presentation.ui.BaseFragment
import com.goforer.singlesharedsample.presentation.vm.info.SharedMyInfoViewModel
import com.goforer.singlesharedsample.presentation.vm.wealth.SharedWealthViewModel
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : BaseFragment<FragmentFirstBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentFirstBinding
        get() = FragmentFirstBinding::inflate

    @Inject
    internal lateinit var sharedMyInfoViewModel: SharedMyInfoViewModel

    @Inject
    internal lateinit var sharedWealthViewModel: SharedWealthViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            sharedMyInfoViewModel.share(
                MyInfo("Lukoh", "+82107777777", "lukoh.nam@gmail.com", "REMIAN ONE BAILY")
            )

            sharedWealthViewModel.share(
                WealthInfo("Stock", "Samsung", "1000000", "USD")
            )

            val title =  getString(R.string.personal_info)
            val direction = FirstFragmentDirections.actionFirstFragmentToMyInfoFragment(title, 100)

            findNavController().navigate(direction)
        }
    }
}