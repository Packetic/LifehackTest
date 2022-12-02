package com.example.lifehacktest.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.lifehacktest.R
import com.example.lifehacktest.databinding.ActivityOrganizationDetailsBinding

class OrganizationDetailsActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityOrganizationDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        if (!intent.hasExtra(EXTRA_ID)) {
            finish()
            return
        }
        val fromSymbol = intent.getStringExtra(EXTRA_ID) ?: EMPTY_SYMBOL
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(
                    R.id.fragment_container,
                    OrganizationDetailsFragment.newInstance(fromSymbol)
                )
                .commit()
        }
    }

    companion object {
        private const val EXTRA_ID = "id"
        private const val EMPTY_SYMBOL = ""

        fun newIntent(context: Context, id: String): Intent {
            val intent = Intent(context, OrganizationDetailsActivity::class.java)
            intent.putExtra(EXTRA_ID, id)
            return intent
        }
    }
}