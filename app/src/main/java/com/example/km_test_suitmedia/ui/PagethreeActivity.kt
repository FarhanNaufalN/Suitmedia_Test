package com.example.km_test_suitmedia.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.km_test_suitmedia.core.setup.API
import com.example.km_test_suitmedia.core.setup.Config
import com.example.km_test_suitmedia.databinding.ActivityPagethreeBinding
import com.example.km_test_suitmedia.ui.adapter.UserAdapter
import com.example.km_test_suitmedia.ui.paging.UserPagingSource
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class PagethreeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityPagethreeBinding
    private var name: String? = null // Changed to nullable type
    private lateinit var adapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityPagethreeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        name = intent.getStringExtra("name")
        if (name.isNullOrEmpty()) {
            Log.w("PagethreeActivity", "Received name is null or empty")
        } else {
            Log.d("PagethreeActivity", "Received name: $name")
        }

        setupRecyclerView()
        setupPaging()

        binding.swipeRefresh.setOnRefreshListener {
            adapter.refresh() // Refresh the paging data
        }
    }

    private fun setupRecyclerView() {
        binding.rvUser.layoutManager = LinearLayoutManager(this)
        adapter = UserAdapter(name ?: "") // Provide a default empty string if name is null
        binding.rvUser.adapter = adapter
    }

    private fun setupPaging() {
        val api = Config.getRetrofitData().create(API::class.java)
        val pager = Pager(
            config = PagingConfig(pageSize = 20, enablePlaceholders = false),
            pagingSourceFactory = { UserPagingSource(api) }
        )

        lifecycleScope.launch {
            pager.flow.collectLatest { pagingData ->
                Log.d("PagingDebug", "Collecting paging data")
                adapter.submitData(pagingData)
                binding.swipeRefresh.isRefreshing = false
            }
        }
    }

    fun onBackButtonClicked(view: View) {
        val intent = Intent(this, PagetwoActivity::class.java)
        intent.putExtra("name", name)
        startActivity(intent)
    }
}
