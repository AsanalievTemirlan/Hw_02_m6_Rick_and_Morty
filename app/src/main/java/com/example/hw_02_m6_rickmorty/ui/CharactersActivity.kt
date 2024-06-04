package com.example.hw_02_m6_rickmorty.ui

import CharactersAdapter
import android.os.Bundle
import android.view.View
import android.widget.AbsListView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hw_02_m6_rickmorty.R
import com.example.hw_02_m6_rickmorty.data.Repository
import com.example.hw_02_m6_rickmorty.databinding.ActivityCharactersBinding
import com.example.hw_02_m6_rickmorty.utils.Resource
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class CharactersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharactersBinding
    private lateinit var adapter: CharactersAdapter
    private val viewModel by lazy {
        ViewModelProvider(this)[CharacterViewModel::class.java]
    }
    var isLoading = false
    var isLastPage = false
    var isScrolling = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeData()
    }

    private fun setupRecyclerView() {
        adapter = CharactersAdapter()

        binding.rvRick.layoutManager = LinearLayoutManager(this)
        binding.rvRick.adapter = adapter

        // Настройка обработчика кликов на элементах адаптера
        adapter.setOnItemClickListener { character ->
            Toast.makeText(this, "Clicked: ${character.name}", Toast.LENGTH_SHORT).show()
        }
    }

    private fun observeData() {
        viewModel.getCharacter().observe(this) { resource ->
            when (resource) {
                is Resource.Loading -> {
                }
                is Resource.Success -> {


                }
                is Resource.Error -> {

                }
            }
        }
    }

    private fun setUpRecyclerView() {
        adapter = CharactersAdapter()
        binding.rvRick.apply {
            setHasFixedSize(true)
            adapter = adapter
            layoutManager = LinearLayoutManager(this@CharactersActivity)
            addOnScrollListener(this@CharactersActivity.scrollListener)
        }
    }

    private val scrollListener = object : RecyclerView.OnScrollListener() {
        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
            super.onScrollStateChanged(recyclerView, newState)
            if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                isScrolling = true
            }
        }

        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
            super.onScrolled(recyclerView, dx, dy)
            val layoutManager = recyclerView.layoutManager as LinearLayoutManager
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            val isNotLoadingAndNotLastPage = !isLoading && !isLastPage
            val isAtLastItem = firstVisibleItemPosition + visibleItemCount >= totalItemCount
            val isNotAtBeginning = firstVisibleItemPosition >= 0
            val isTotalMoreThanVisible = totalItemCount >= QUERY_PAGE_SIZE
            val shouldPaginate = isNotLoadingAndNotLastPage && isAtLastItem && isNotAtBeginning
                    && isTotalMoreThanVisible && isScrolling
            if (shouldPaginate) {
                viewModel.nextPage()
                isScrolling = false
            }
        }
    }

    private fun hideProgressBar() {
        binding.progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.progressBar.visibility = View.VISIBLE
    }

    companion object {
        private const val QUERY_PAGE_SIZE = 20
    }

}


