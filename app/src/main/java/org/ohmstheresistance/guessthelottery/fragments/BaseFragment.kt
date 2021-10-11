package org.ohmstheresistance.guessthelottery.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import androidx.viewbinding.ViewBinding
import org.ohmstheresistance.guessthelottery.R


abstract class BaseFragment<viewModel: ViewModel, viewBinding: ViewBinding> : Fragment() {

    protected lateinit var binding: viewBinding
    protected lateinit var vm: viewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = getFragmentBinding(inflater, container)

        return binding.root

    }

    abstract fun getViewModel(): Class<viewModel>

    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): viewBinding
}