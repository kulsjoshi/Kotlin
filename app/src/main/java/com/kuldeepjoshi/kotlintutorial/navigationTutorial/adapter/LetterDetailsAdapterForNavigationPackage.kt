package com.kuldeepjoshi.kotlintutorial.navigationTutorial.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.accessibility.AccessibilityNodeInfo
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.kuldeepjoshi.kotlintutorial.R
import com.kuldeepjoshi.kotlintutorial.navigationTutorial.fragment.WordsListFragment

class LetterDetailsAdapterForNavigationPackage(letterId: String, context: Context) :
    RecyclerView.Adapter<LetterDetailsAdapterForNavigationPackage.CustomLetterDetailsAdapter>() {

    private val filteredList: List<String>

    init {

        val words = context.resources.getStringArray(R.array.words).toList()

        filteredList = words
            .filter { it.startsWith(letterId, ignoreCase = true) }
            .shuffled()
            .take(5)
            .sorted()

    }

    class CustomLetterDetailsAdapter(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val button = itemView.findViewById<Button>(R.id.buttonLetter)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomLetterDetailsAdapter {
        val layout =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_letter, parent, false)
        layout.accessibilityDelegate = Accessibility
        return CustomLetterDetailsAdapter(layout)
    }

    override fun onBindViewHolder(holder: CustomLetterDetailsAdapter, position: Int) {
        val item = filteredList[position]
        holder.button.text = item
        holder.button.setOnClickListener {
            val queryUrl: Uri =
                Uri.parse("${WordsListFragment.SEARCH_PREFIX}${item}")

            val context = holder.itemView.context

            Intent(Intent.ACTION_VIEW, queryUrl).also {
                context.startActivity(it)
            }


        }
    }

    override fun getItemCount(): Int = filteredList.size

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
            val customString = host?.context?.getString(R.string.look_up_words)
            val customClick =
                AccessibilityNodeInfo.AccessibilityAction(
                    AccessibilityNodeInfo.ACTION_CLICK,
                    customString
                )
            info?.addAction(customClick)
        }
    }
}