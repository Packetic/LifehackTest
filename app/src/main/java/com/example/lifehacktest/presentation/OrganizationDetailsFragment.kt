package com.example.lifehacktest.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.lifehacktest.databinding.FragmentOrganizationDetailsBinding
import com.squareup.picasso.Picasso

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
        viewModel.getOrganizationDetails(id)
        viewModel.getOrganizationDetails(id).observe(viewLifecycleOwner) {
            if (it != null) {
                with(binding) {
                    Picasso.get().load(it.img).into(ivLogoCoin)
                    tvDescName.text = it.name
                    tvDesc.text = it.description
                    tvPhone.text = it.phone
                    tvSite.text = it.www
                    Log.d("OrganizationDetailsFragment", id)
                }
            }
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