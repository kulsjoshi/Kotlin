package com.kuldeepjoshi.kotlintutorial.navigationTutorial.adapter

import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.kuldeepjoshi.kotlintutorial.R
import com.kuldeepjoshi.kotlintutorial.navigationTutorial.fragment.LetterListFragmentDirections

class LetterAdapterForNavigationModule :
    RecyclerView.Adapter<LetterAdapterForNavigationModule.CustomViewHolder>() {

    private val dataSet = ('A').rangeTo('Z').toList()


    class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val button = itemView.findViewById<Button>(R.id.buttonLetter)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val layout =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_letter, parent, false)
        layout.accessibilityDelegate = Accessibility
        return CustomViewHolder(layout)
    }

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        holder.button.text = dataSet[position].toString()

        val mContext = holder.itemView.context

        holder.button.setOnClickListener {

            val action =
                LetterListFragmentDirections.actionLetterListFragmentToWordsListFragment(letter = holder.button.text.toString())

            holder.itemView.findNavController().navigate(action)

        }
    }

    override fun getItemCount(): Int {
        return dataSet.size
    }

    // Setup custom accessibility delegate to set the text read with
    // an accessibility service
    companion object Accessibility : View.AccessibilityDelegate() {
        @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
        override fun onInitializeAccessibilityNodeInfo(
            host: View?,
            info: AccessibilityNodeInfo?
        ) {
            super.onInitializeAccessibilityNodeInfo(host, info)
            // With `null` as the second argument to [AccessibilityAction], the
            // accessibility service announces "double tap to activate".
            // If a custom string is provided,
            // it announces "double tap to <custom string>".
            val customString = host?.context?.getString(R.string.look_up_word)
            val customClick =
                AccessibilityNodeInfo.AccessibilityAction(
                    AccessibilityNodeInfo.ACTION_CLICK,
                    customString
                )
            info?.addAction(customClick)
        }
    }
}