package com.sample.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.liveData
import com.sample.base.BaseFragment
import com.sample.base.BaseViewModel
import com.sample.databinding.FragmentVersionBinding

class VersionFragment : BaseFragment() {
    private var binding: FragmentVersionBinding? = null
    private lateinit var viewModel: VersionViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        binding = FragmentVersionBinding.inflate(inflater, container, false)
        viewModel = ViewModelProvider(this).get(VersionViewModel::class.java)

        with(binding!!) {
            lifecycleOwner = viewLifecycleOwner
            viewModel = this@VersionFragment.viewModel

        }
        return binding?.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}

class VersionViewModel : BaseViewModel() {
    val version = liveData { emit("1.0.1") }
}
