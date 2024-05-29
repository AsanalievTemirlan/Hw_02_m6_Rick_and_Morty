package com.example.hw_02_m6_rickmorty.ui

import android.os.Bundle
import android.widget.ExpandableListView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContentProviderCompat.requireContext
import com.example.hw_02_m6_rickmorty.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailBinding
    private lateinit var expandableListView: ExpandableListView
    private lateinit var expandableListAdapter: CustomExpandableListAdapter
    private lateinit var listGroup: List<String>
    private lateinit var listItem: HashMap<String, List<Episode>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
        expandableListView = binding.expandableListView
        initListData()
        expandableListAdapter = CustomExpandableListAdapter(this, listGroup, listItem)
        expandableListView.setAdapter(expandableListAdapter)
    }

    private fun initListData() {
        listGroup = listOf("Season 1","Season 2","Season 2","Season 2","Season 2","Season 2","Season 2")

        val episodeList = listOf(
            Episode("Pilot", "December 13, 2013"),
            Episode("Episode 2", "December 20, 2013"),
            Episode("Episode 2", "December 20, 2013"),
            Episode("Episode 2", "December 20, 2013"),
            Episode("Episode 2", "December 20, 2013"),
            Episode("Episode 2", "December 20, 2013")
        )

        listItem = hashMapOf(listGroup[0] to episodeList)
    }
}