package com.kuldeepjoshi.kotlintutorial.cupCake.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.kuldeepjoshi.kotlintutorial.R
import com.kuldeepjoshi.kotlintutorial.cupCake.viewModel.OrderViewModel
import com.kuldeepjoshi.kotlintutorial.databinding.FragmentFlavorBinding

class FlavorFragment : Fragment() {

    // Binding object instance corresponding to the fragment_flavor.xml layout
    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment.
    private lateinit var binding: FragmentFlavorBinding

    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_flavor, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding?.apply {

            flavorFragment = this@FlavorFragment
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner

        }
    }

    /**
     * Navigate to the next screen to choose pickup date.
     */
    fun goToNextScreen() {
        findNavController().navigate(R.id.action_flavorFragment_to_pickupFragment)
    }

    /**
     * This fragment lifecycle method is called when the view hierarchy associated with the fragment
     * is being removed. As a result, clear out the binding object.
     */
    override fun onDestroyView() {
        super.onDestroyView()
//        binding = null
    }

    fun cancelOrder(){
        sharedViewModel.resetOrder();
        findNavController().navigate(R.id.action_flavorFragment_to_startFragment)
    }
}