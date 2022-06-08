package com.daisymai99.onlstay.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.daisymai99.onlstay.R
import com.daisymai99.onlstay.adapter.Circle_Recycle_Adapter
import com.daisymai99.onlstay.adapter.SearchAdapter
import com.daisymai99.onlstay.databinding.ActivityListPlaceBinding
import kotlin.collections.ArrayList

class List_Place : AppCompatActivity() {
    lateinit var binding: ActivityListPlaceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListPlaceBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // recycler of Avata
        val adapterAva = Circle_Recycle_Adapter()
        val layoutManager: RecyclerView.LayoutManager = LinearLayoutManager(getApplicationContext(),LinearLayoutManager.HORIZONTAL,false)
        binding.rclAva.setHasFixedSize(true)
        binding.rclAva.layoutManager = layoutManager
        binding.rclAva.adapter = adapterAva

        // recycler of City
        binding.searchView.setIconifiedByDefault(false)
        binding.searchView.queryHint= "Thử tìm kiếm Hà Nội"

            val dataSearch = arrayListOf<String>(*resources.getStringArray(R.array.list_city))
        val adapter = SearchAdapter(dataSearch)
        val layout = LinearLayoutManager(this)
        binding.rclListCity.layoutManager= layout
        binding.rclListCity.adapter= adapter

        var tempListCity = ArrayList<String>()
        tempListCity.addAll(dataSearch)

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(p0: String?): Boolean {
                val searchText = dataSearch.contains(p0)
                return  false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
               /* tempListCity.clear()
                val searchText = p0!!.toLowerCase(Locale.getDefault())
                if (searchText.isNotEmpty()){
                    dataSearch.forEach {
                        if (it.contains(searchText)){
                            tempListCity.add(it)
                        }
                    }
                    adapter.notifyDataSetChanged()
                }else{
                    tempListCity.clear()
                    tempListCity.addAll(dataSearch)
                    adapter!!.notifyDataSetChanged()

                }*/

                adapter.filter.filter(p0)
                return false
           }

        })

    }
}