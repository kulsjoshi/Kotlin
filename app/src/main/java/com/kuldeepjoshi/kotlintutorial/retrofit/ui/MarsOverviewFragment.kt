package com.kuldeepjoshi.kotlintutorial.retrofit.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.kuldeepjoshi.kotlintutorial.databinding.FragmentMarsOverviewBinding
import com.kuldeepjoshi.kotlintutorial.retrofit.OverviewViewModel

/**
 * A simple [Fragment] subclass.
 * Use the [MarsOverviewFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MarsOverviewFragment : Fragment() {

    private val viewModel: OverviewViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentMarsOverviewBinding.inflate(inflater)

        //Allows DataBinding to Observe LiveData with the lifecycle of this fragment
        binding.lifecycleOwner = this;

        //Giving the binding access to the OverviewViewModel
        binding.viewModel = viewModel

        return binding.root
    }

}