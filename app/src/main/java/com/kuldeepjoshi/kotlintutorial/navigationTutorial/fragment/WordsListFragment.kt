package com.kuldeepjoshi.kotlintutorial.navigationTutorial.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kuldeepjoshi.kotlintutorial.databinding.FragmentWordsListBinding
import com.kuldeepjoshi.kotlintutorial.navigationTutorial.adapter.LetterDetailsAdapterForNavigationPackage

/**
 * A simple [Fragment] subclass.
 * Use the [WordsListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class WordsListFragment : Fragment() {

    private var _binding: FragmentWordsListBinding? = null

    private val binding get() = _binding!!

    private lateinit var letterId: String

    companion object {
        const val LETTER = "letter"
        const val SEARCH_PREFIX = "https://www.google.com/search?q="
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            letterId = it.getString(LETTER).toString()
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentWordsListBinding.inflate(inflater, container, false)
        val view = binding.root
        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initialization()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initialization() {

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = LetterDetailsAdapterForNavigationPackage(letterId, this.requireContext())

    }

}