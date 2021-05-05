package com.kuldeepjoshi.kotlintutorial.cupCake.ui

import android.content.Intent
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
import com.kuldeepjoshi.kotlintutorial.databinding.FragmentSummaryBinding

class SummaryFragment : Fragment() {

    // Binding object instance corresponding to the fragment_summary.xml layout
    // This property is non-null between the onCreateView() and onDestroyView() lifecycle callbacks,
    // when the view hierarchy is attached to the fragment.
    private lateinit var binding: FragmentSummaryBinding

    private val sharedViewModel: OrderViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_summary, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.apply {

            summeryFragment = this@SummaryFragment
            viewModel = sharedViewModel
            lifecycleOwner = viewLifecycleOwner

        }
    }

    /**
     * Submit the order by sharing out the order details to another app via an implicit intent.
     */
    fun sendOrder() {

        val numberOfCupCakes = sharedViewModel.quantity.value ?: 0

        var orderSummary = getString(
            R.string.order_details,
            resources.getQuantityString(R.plurals.cupcakes, numberOfCupCakes, numberOfCupCakes),
            sharedViewModel.flavor.value.toString(),
            sharedViewModel.date.value.toString(),
            sharedViewModel.price.value.toString()
        )

        Intent(Intent.ACTION_SEND).also {

            it.type = "text/plain"
            it.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.new_cupcake_order))
            it.putExtra(Intent.EXTRA_TEXT, orderSummary)

            if(activity?.packageManager?.resolveActivity(it,0) != null){
                startActivity(it)
            }
        }
    }

    /**
     * This fragment lifecycle method is called when the view hierarchy associated with the fragment
     * is being removed. As a result, clear out the binding object.
     */
    override fun onDestroyView() {
        super.onDestroyView()
    }

    fun cancelOrder(){
        sharedViewModel.resetOrder();
        findNavController().navigate(R.id.action_summaryFragment_to_startFragment)
    }
}
