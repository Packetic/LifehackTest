package com.example.lifehacktest.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import com.example.lifehacktest.databinding.ItemOrganizationPreviewBinding
import com.example.lifehacktest.domain.Organization
import com.squareup.picasso.Picasso

class OrganizationsAdapter
    : ListAdapter<Organization, OrganizationViewHolder>(OrganizationDiffCallback) {

    var onCardClickListener: OnCardClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrganizationViewHolder {
        val binding = ItemOrganizationPreviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return OrganizationViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OrganizationViewHolder, position: Int) {
        val organization = getItem(position)
        with(holder.binding) {
            with(organization) {
                Picasso.get().load(img).into(ivOrganizationLogo)
                tvOrganizationName.text = name
                root.setOnClickListener {
                    onCoinClickListener?.onCardClick(this)
                }
            }
        }
    }

    interface OnCardClickListener {
        fun onCardClick(organization: Organization)
    }
}