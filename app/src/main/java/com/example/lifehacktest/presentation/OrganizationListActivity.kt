package com.example.lifehacktest.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
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
        setContentView(binding.root)
        val adapter = OrganizationsAdapter()
        binding.rvOrganizationList.adapter = adapter
        viewModel = ViewModelProvider(this)[OrganizationViewModel::class.java]
        viewModel.organizationList.observe(this) {
            adapter.submitList(it)
        }
        adapter.onCardClickListener = object : OrganizationsAdapter.OnCardClickListener {
            override fun onCardClick(organization: Organization) {
                viewModel.loadOrganizationDetails(organization.id)
                if (isOnePaneMode())
                    launchDetailActivity(organization.id)
                else
                    launchDetailFragment(organization.id)
            }
        }
    }

    private fun isOnePaneMode() = binding.fragmentContainer == null

    private fun launchDetailActivity(id: String) {
        val intent = OrganizationDetailsActivity.newIntent(
            this@OrganizationListActivity,
            id
        )
        startActivity(intent)
    }

    private fun launchDetailFragment(id: String) {
        supportFragmentManager.popBackStack()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, OrganizationDetailsFragment.newInstance(id))
            .addToBackStack(null)
            .commit()
    }
}