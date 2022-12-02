package com.example.lifehacktest.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.example.lifehacktest.R
import com.example.lifehacktest.databinding.ActivityOrganizationListBinding
import com.example.lifehacktest.domain.Organization
import com.example.lifehacktest.presentation.adapters.OrganizationsAdapter

class OrganizationListActivity : AppCompatActivity() {

    private lateinit var viewModel: OrganizationViewModel

    private val binding by lazy {
        ActivityOrganizationListBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_organization_list)
        val adapter = OrganizationsAdapter()
        adapter.onCardClickListener = object : OrganizationsAdapter.OnCardClickListener {
            override fun onCardClick(organization: Organization) {
                TODO("Not yet implemented")
            }
        }
        binding.rvCoinPriceList.adapter = adapter
        viewModel = ViewModelProvider(this)[OrganizationViewModel::class.java]
        viewModel.organizationList.observe(this) {
            adapter.submitList(it)
        }
    }
}