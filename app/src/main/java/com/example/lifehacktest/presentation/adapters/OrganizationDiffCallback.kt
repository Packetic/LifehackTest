package com.example.lifehacktest.presentation.adapters

import androidx.recyclerview.widget.DiffUtil
import com.example.lifehacktest.domain.Organization

object OrganizationDiffCallback : DiffUtil.ItemCallback<Organization>() {
    override fun areItemsTheSame(
        oldItem: Organization,
        newItem: Organization
    ): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(
        oldItem: Organization,
        newItem: Organization
    ): Boolean {
        return oldItem == newItem
    }

}