package com.example.hw_02_m6_rickmorty.ui

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.example.hw_02_m6_rickmorty.R

class CustomExpandableListAdapter(
    private val context: Context,
    private val listGroup: List<String>,
    private val listItem : HashMap<String, List<Episode>>
): BaseExpandableListAdapter() {
    override fun getGroupCount(): Int {
        return listGroup.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return listItem[listGroup[groupPosition]]!!.size
    }

    override fun getGroup(groupPosition: Int): Any {
        return listGroup[groupPosition]
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return listItem[listGroup[groupPosition]]!![childPosition]
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun hasStableIds(): Boolean {
        return false
    }

    @SuppressLint("InflateParams")
    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val groupTitle = getGroup(groupPosition) as String
        var view = convertView
        if (view == null) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.item_group, null)
        }
        val textView = view!!.findViewById<TextView>(R.id.lblListHeader)
        textView.text = groupTitle
        return view
    }

    @SuppressLint("InflateParams")
    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?
    ): View {
        val episode = getChild(groupPosition, childPosition) as Episode
        var view = convertView
        if (view == null) {
            val inflater =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.item_list, null)
        }
        val episodeName = view!!.findViewById<TextView>(R.id.episodeName)
        val episodeAirDate = view.findViewById<TextView>(R.id.episodeAirDate)
        episodeName.text = episode.name
        episodeAirDate.text = episode.airDate
        return view
    }


    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }
}