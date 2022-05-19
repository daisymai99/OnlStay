package com.daisymai99.onlstay.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.daisymai99.onlstay.ListRoom
import com.daisymai99.onlstay.MainActivity
import com.daisymai99.onlstay.databinding.ItemPlaceBinding
import java.util.*
import kotlin.collections.ArrayList

class SearchAdapter(var data : ArrayList<String>): RecyclerView.Adapter<SearchAdapter.ViewHolder>(),Filterable {

    var newList = ArrayList<String>()

    init {
        newList = data
    }

    inner class ViewHolder(val binding: ItemPlaceBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemPlaceBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        //val myItem = holder.itemView.resources.getStringArray(R.array.list_city)

        with(holder) {
            binding.txtItemPlace.text = newList[position]
            binding.txtItemPlace.setOnClickListener {
                v -> v.context.startActivity(Intent(v.context,ListRoom::class.java))
            }
        }

    }
    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charSearch = constraint.toString()
                if (charSearch.isEmpty()) {
                    newList = data
                } else {
                    val resultList = ArrayList<String>()
                    for (row in data) {
                        if (row.lowercase(Locale.ROOT)
                                .contains(charSearch.lowercase(Locale.ROOT))
                        ) {
                            resultList.add(row)
                        }
                    }
                    newList = resultList
                }
                val filterResults = FilterResults()
                filterResults.values = newList
                return filterResults
            }

            @Suppress("UNCHECKED_CAST")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                newList = results?.values as ArrayList<String>
                notifyDataSetChanged()
            }
        }


    }
    override fun getItemCount(): Int {
        return newList.size
    }

}