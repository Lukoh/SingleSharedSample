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
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.goforer.singlesharedsample.R
import com.goforer.singlesharedsample.data.source.model.entity.share.MyInfo
import com.goforer.singlesharedsample.databinding.FragmentMyInfoBinding
import com.goforer.singlesharedsample.presentation.ui.BaseFragment
import com.goforer.singlesharedsample.presentation.vm.info.SharedMyInfoViewModel
import javax.inject.Inject

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MyInfoFragment : BaseFragment<FragmentMyInfoBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentMyInfoBinding
        get() = FragmentMyInfoBinding::inflate

    private val args: MyInfoFragmentArgs by navArgs()

    @Inject
    internal lateinit var sharedMyInfoViewModel: SharedMyInfoViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Toast.makeText(context, args.description, Toast.LENGTH_LONG).show()
        observeMyInfo()
        binding.btNextBig.setOnClickListener {
            findNavController().navigate(R.id.action_MyInfoFragment_to_MyWealthFragment)
        }
    }

    private fun observeMyInfo() {
        sharedMyInfoViewModel.shared {
            it?.let {
                fillMyInfo(it)
            }
        }
    }

    private fun fillMyInfo(myInfo: MyInfo) {
        with(binding) {
            etName.setText(myInfo.name)
            etCellularPhone.setText(myInfo.phone)
            etEmail.setText(myInfo.email)
            etAddress.setText(myInfo.address)
            binding.btNextBig.isEnabled = myInfo.isFilled()
        }
    }
}