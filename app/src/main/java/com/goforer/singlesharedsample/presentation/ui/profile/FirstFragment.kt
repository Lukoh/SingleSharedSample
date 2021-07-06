package com.goforer.singlesharedsample.presentation.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.goforer.singlesharedsample.data.source.model.entity.share.MyInfo
import com.goforer.singlesharedsample.data.source.model.entity.share.WealthInfo
import com.goforer.singlesharedsample.databinding.FragmentFirstBinding
import com.goforer.singlesharedsample.presentation.ui.BaseFragment
import com.goforer.singlesharedsample.presentation.vm.SharedMyInfoViewModel
import com.goforer.singlesharedsample.presentation.vm.SharedWealthViewModel
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

            val description =  "SingleSharedSample"
            val direction = FirstFragmentDirections.actionFirstFragmentToMyInfoFragment(description, 100)

            findNavController().navigate(direction)
        }
    }
}