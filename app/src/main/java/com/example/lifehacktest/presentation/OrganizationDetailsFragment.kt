package com.example.lifehacktest.presentation

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.lifehacktest.databinding.FragmentOrganizationDetailsBinding

class OrganizationDetailsFragment : Fragment() {

    private lateinit var viewModel: OrganizationViewModel

    private var _binding: FragmentOrganizationDetailsBinding? = null
    private val binding: FragmentOrganizationDetailsBinding
        get() = _binding ?: throw RuntimeException("FragmentOrganizationDetailsBinding == null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOrganizationDetailsBinding.inflate(
            inflater,
            container,
            false
        )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[OrganizationViewModel::class.java]
        val id = getIdArgument()
        viewModel.getOrganizationDetails(id).observe(viewLifecycleOwner) {

        }
    }

    private fun getIdArgument(): String = requireArguments().getString(EXTRA_ID, "")

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

    companion object {
        const val EXTRA_ID = "id"

        fun newInstance(id: String): Fragment {
            return OrganizationDetailsFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_ID, id)
                }
            }
        }
    }
}