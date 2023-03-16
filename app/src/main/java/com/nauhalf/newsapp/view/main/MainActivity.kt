package com.nauhalf.newsapp.view.main

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.nauhalf.newsapp.R
import com.nauhalf.newsapp.base.activity.BaseActivity
import com.nauhalf.newsapp.databinding.ActivityMainBinding
import com.nauhalf.newsapp.util.LinearSpacingItemDecoration
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>() {

    private val viewModel by viewModels<MainViewModel>()
    private val newsAdapter by lazy {
        NewsAdapter()
    }

    override fun onViewBindingInflate(layoutInflater: LayoutInflater): ActivityMainBinding {
        return ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setUp()
        subscribeData()
    }

    private fun subscribeData() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.RESUMED) {
                viewModel.pager.collectLatest {
                    newsAdapter.submitData(it)
                }
            }
        }
    }

    private fun setUp() {
        with(binding) {
            rvNews.adapter = newsAdapter
            if (rvNews.itemDecorationCount == 0) {
                rvNews.addItemDecoration(
                    LinearSpacingItemDecoration(
                        spacing = resources.getDimensionPixelOffset(R.dimen.dp_8),
                        orientation = LinearLayoutManager.VERTICAL
                    )
                )
            }
        }
    }
}
